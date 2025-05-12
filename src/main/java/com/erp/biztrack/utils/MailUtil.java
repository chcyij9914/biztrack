package com.erp.biztrack.utils;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MailUtil {

	private static JavaMailSender mailSender;

	@Autowired
	public MailUtil(JavaMailSender mailSender) {
		MailUtil.mailSender = mailSender;
	}

	public static void sendMail(String to, String empId, String tempPwd) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom("kknh97@naver.com");
		message.setSubject("[BizTrack] 로그인 정보 안내");
		message.setText("안녕하세요.\n\n" + "아래는 귀하의 BizTrack 로그인 정보입니다:\n\n" + "🔹 사원번호(ID): " + empId + "\n"
				+ "🔹 초기 비밀번호: " + tempPwd + "\n\n" + "로그인 후 반드시 비밀번호를 변경해주세요.");

		System.out.println("📨 메일 발송 대상: " + to);
		System.out.println("📬 발급된 초기 비밀번호: " + tempPwd);

		mailSender.send(message);
	}

	public static void sendAuthCodeMail(String to, String code) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom("kknh97@naver.com");
		message.setSubject("[BizTrack] 인증번호 안내");
		message.setText("요청하신 비밀번호 찾기 인증번호는 다음과 같습니다:\n\n" + "🔐 인증번호: " + code + "\n\n" + "5분 이내에 입력해 주세요.");

		mailSender.send(message);
	}

	public static boolean sendViaMailgun(String to, String subject, String text, String fromEmail, MultipartFile file) {
		try {
			if (file != null && !file.isEmpty()) {
				long size = file.getSize();
				if (size > 20 * 1024 * 1024) {
					System.out.println("❌ 첨부파일 크기 제한 초과: " + size);
					return false;
				}
			}

			String apiKey = "#";
			String domain = "sandbox2762044ef855458e95d97b4e3e4d0233.mailgun.org";
			String auth = "api:" + apiKey;
			String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

			URL url = new URL("https://api.mailgun.net/v3/" + domain + "/messages");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----boundary");

			String LINE = "\r\n";

			try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
				out.write(("------boundary" + LINE).getBytes(StandardCharsets.UTF_8));
				out.write(("Content-Disposition: form-data; name=\"from\"" + LINE + LINE)
						.getBytes(StandardCharsets.UTF_8));
				out.write(("BizTrack <mailgun@" + domain + ">" + LINE).getBytes(StandardCharsets.UTF_8));

				out.write(("------boundary" + LINE).getBytes(StandardCharsets.UTF_8));
				out.write(
						("Content-Disposition: form-data; name=\"to\"" + LINE + LINE).getBytes(StandardCharsets.UTF_8));
				out.write((to + LINE).getBytes(StandardCharsets.UTF_8));

				out.write(("------boundary" + LINE).getBytes(StandardCharsets.UTF_8));
				out.write(("Content-Disposition: form-data; name=\"subject\"" + LINE + LINE)
						.getBytes(StandardCharsets.UTF_8));
				out.write((subject + LINE).getBytes(StandardCharsets.UTF_8));

				out.write(("------boundary" + LINE).getBytes(StandardCharsets.UTF_8));
				out.write(("Content-Disposition: form-data; name=\"text\"" + LINE + LINE)
						.getBytes(StandardCharsets.UTF_8));
				out.write(("보낸 사람: " + (fromEmail != null ? fromEmail : "unknown@biztrack.com") + LINE + LINE + text
						+ LINE).getBytes(StandardCharsets.UTF_8));

				if (file != null && !file.isEmpty()) {
					out.write(("------boundary" + LINE).getBytes(StandardCharsets.UTF_8));
					out.write(("Content-Disposition: form-data; name=\"attachment\"; filename=\""
							+ file.getOriginalFilename() + "\"" + LINE).getBytes(StandardCharsets.UTF_8));
					out.write(
							("Content-Type: " + file.getContentType() + LINE + LINE).getBytes(StandardCharsets.UTF_8));
					out.write(file.getBytes());
					out.write(LINE.getBytes(StandardCharsets.UTF_8));
				}

				out.write(("------boundary--" + LINE).getBytes(StandardCharsets.UTF_8));
				out.flush();
			}

			int responseCode = conn.getResponseCode();
			System.out.println("📨 Mailgun 응답코드: " + responseCode);
			return responseCode == 200;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
