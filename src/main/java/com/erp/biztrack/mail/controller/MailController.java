package com.erp.biztrack.mail.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.utils.MailUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mail")
public class MailController {

    @GetMapping("form.do")
    public String showForm() {
        return "mail/mail"; // WEB-INF/views/mail/mail.jsp
    }

    @PostMapping("send.do")
    public String sendMail(@RequestParam String to,
                           @RequestParam String subject,
                           @RequestParam String text,
                           @RequestParam(required = false) MultipartFile file,
                           HttpSession session,
                           Model model) {
        LoginDto loginUser = (LoginDto) session.getAttribute("loginInfo");
        String fromEmail = (loginUser != null) ? loginUser.getEmail() : null;

        boolean isSuccess = MailUtil.sendViaMailgun(to, subject, text, fromEmail, file);

        model.addAttribute("result", isSuccess ? "success" : "fail");
        return "mail/result";
    }
}
