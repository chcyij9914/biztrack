package com.erp.biztrack.reminder.model.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.reminder.model.dao.ReminderDao;
import com.erp.biztrack.reminder.model.dto.Reminder;

@Service("reminderService")
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderDao reminderDao;

    @Override
    public ArrayList<Reminder> selectReminderList() {
        return reminderDao.selectReminderList();
    }
    
    @Override
	public int updateReminderSmsContent(Reminder reminder) {
		return reminderDao.updateReminderSmsContent(reminder);
	}
   
    @Override
    public String sendSms(String reminderId, String phone, String content) {
        try {
            // ✅ 직접 입력할 값들
            String apiKey = "#";
            String apiSecret = "#";
            String sender = "#";  

         // 📆 [2] date, salt 생성
            String date = ZonedDateTime.now(ZoneOffset.UTC)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
            String salt = java.util.UUID.randomUUID().toString();

            // 🔏 [3] signature 생성
            String method = "POST";
            String path = "/messages/v4/send";
            String message = method + " " + path + "\n" + date + "\n" + salt;

            // ✅ 디버깅 출력
            System.out.println("🔐 message to sign:\n" + message);

            SecretKeySpec keySpec = new SecretKeySpec(apiSecret.trim().getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
            byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String signature = Base64.getEncoder().encodeToString(rawHmac);

            // ✅ 시그니처 출력
            System.out.println("🔏 signature:\n" + signature);

            // 🔐 [4] Authorization 헤더 구성
            String authorization = String.format("HMAC-SHA256 apiKey=%s, date=%s, salt=%s, signature=%s", 
                                                 apiKey, date, salt, signature);

            // ✅ Authorization 헤더 출력
            System.out.println("🧾 Authorization:\n" + authorization);

            // 📦 [5] 메시지 JSON 바디 구성
            JSONObject messageObj = new JSONObject();
            messageObj.put("to", phone.replaceAll("-", ""));
            messageObj.put("from", sender);
            messageObj.put("text", content);

            JSONObject body = new JSONObject();
            body.put("messages", new JSONArray().put(messageObj));

            // 🌐 [6] HTTP 요청
            URL url = new URL("https://api.solapi.com/messages/v4/send");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", authorization);
            conn.setDoOutput(true);

            System.out.println("📡 실제 요청 URL: " + conn.getURL());
            
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 202) {
                // 🔄 문자 전송 성공 시 DB에 SMS 내용 업데이트
                Reminder reminder = new Reminder();
                reminder.setReminderId(reminderId);
                reminder.setSmsContent(content);
                reminderDao.updateReminderSmsContent(reminder);
                return "문자 전송 성공";
            } else {
                try (InputStream is = conn.getErrorStream();
                     Scanner scanner = new Scanner(is, "UTF-8")) {
                    String error = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                    return "문자 전송 실패: " + responseCode + " / " + error;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "예외 발생: " + e.getMessage();
        }
    }
}
