package com.erp.biztrack.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

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
        message.setText(
            "안녕하세요.\n\n" +
            "아래는 귀하의 BizTrack 로그인 정보입니다:\n\n" +
            "🔹 사원번호(ID): " + empId + "\n" +
            "🔹 초기 비밀번호: " + tempPwd + "\n\n" +
            "로그인 후 반드시 비밀번호를 변경해주세요."
        );

        System.out.println("📨 메일 발송 대상: " + to);
        System.out.println("📬 발급된 초기 비밀번호: " + tempPwd);

        mailSender.send(message);
    }
    
    public static void sendAuthCodeMail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("kknh97@naver.com");
        message.setSubject("[BizTrack] 인증번호 안내");
        message.setText("요청하신 비밀번호 찾기 인증번호는 다음과 같습니다:\n\n" +
                        "🔐 인증번호: " + code + "\n\n" +
                        "5분 이내에 입력해 주세요.");

        mailSender.send(message);
    }
}
