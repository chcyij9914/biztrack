package com.erp.biztrack.common;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Scanner;
import java.util.UUID;

public class SolapiReminder {

    public static String sendSms(String apiKey, String apiSecret, String sender, String phone, String content) {
        try {
            // 1. 날짜 및 salt 생성
            String date = ZonedDateTime.now(ZoneOffset.UTC)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
            String salt = UUID.randomUUID().toString();

            // 2. 서명용 문자열 구성
            String method = "POST";
            String path = "/messages/v4/send";
            String message = method + " " + path + "\n" + date + "\n" + salt;

            // 3. 서명 생성 (HMAC-SHA256)
            SecretKeySpec keySpec = new SecretKeySpec(apiSecret.replaceAll("\\r|\\n", "").trim().getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
            byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String signature = Base64.getEncoder().encodeToString(rawHmac);

            // 4. Authorization 헤더 구성
            String authorization = String.format(
                    "HMAC-SHA256 apiKey=%s, date=%s, salt=%s, signature=%s",
                    apiKey, date, salt, signature
            );

            // 디버깅 로그 출력
            System.out.println("apiKey: " + apiKey);
            System.out.println("apiSecret 길이: " + apiSecret.length());
            System.out.println("date: " + date);
            System.out.println("salt: " + salt);
            System.out.println("message to sign:\n" + message);
            System.out.println("signature: " + signature);
            System.out.println("Authorization: " + authorization);

            // 5. 메시지 JSON 구성
            JSONObject messageObj = new JSONObject();
            messageObj.put("to", phone.replaceAll("-", ""));
            messageObj.put("from", sender);
            messageObj.put("text", content);

            JSONObject body = new JSONObject();
            body.put("messages", new JSONArray().put(messageObj));

            // 6. Solapi API 호출
            URL url = new URL("https://api.solapi.com/messages/v4/send");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", authorization);
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes(StandardCharsets.UTF_8));
            }

            // 7. 응답 결과 처리
            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 202) {
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
