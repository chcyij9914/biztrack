package com.erp.biztrack.reminder.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.erp.biztrack.reminder.model.dto.Reminder;
import com.erp.biztrack.reminder.model.service.ReminderService;
import com.erp.biztrack.utils.MailUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/reminder")
public class ReminderController {

    private static final Logger logger = LoggerFactory.getLogger(ReminderController.class);

    @Autowired
    private ReminderService reminderService;

    // 리마인더 목록 조회
    @RequestMapping(value = "/ReminderList.do", method = RequestMethod.GET)
    public String showReminderList(Model model) {
        List<Reminder> reminderList = reminderService.selectAllReminders();
        model.addAttribute("reminderList", reminderList);
        return "reminder/reminderList";
    }

    // 메일 작성 폼 호출 (선택한 거래처 정보 전달)
    @RequestMapping(value = "/sendForm.do", method = RequestMethod.GET)
    public String openMailForm(
            @RequestParam("clientEmail") String clientEmail,
            @RequestParam("clientName") String clientName,
            @RequestParam("reminderId") String reminderId,
            Model model) {

        model.addAttribute("clientEmail", clientEmail);
        model.addAttribute("clientName", clientName);
        model.addAttribute("reminderId", reminderId);
        return "reminder/reminderMail";
    }

    // 메일 전송 처리
    @RequestMapping(value = "/sendEmail.do", method = RequestMethod.POST)
    public String sendReminderEmail(@RequestParam("to") String to,
                                    @RequestParam("text") String text,
                                    @RequestParam("reminderId") String reminderId,
                                    @RequestParam(value = "file", required = false) MultipartFile file,
                                    HttpServletRequest request,
                                    Model model) {

        String from = "admin@biztrack.com";
        String subject = "[BizTrack] 리마인더 안내";

        // 1. 전달값 확인 로그
        logger.info("메일 수신자(to): {}", to);
        logger.info("메일 내용(text): {}", text);
        logger.info("리마인더 ID(reminderId): {}", reminderId);

        // 2. 메일 전송
        boolean mailSendResult = MailUtil.sendViaMailgun(to, subject, text, from, file);

        // 3. DB 저장 (reminderId 유효할 경우만)
        if (reminderId != null && !reminderId.trim().isEmpty()) {
            Reminder reminder = new Reminder();
            reminder.setReminderId(reminderId);
            reminder.setMailContent(text);
            reminderService.updateMailContent(reminder);
            logger.info("메일 내용이 DB에 저장되었습니다.");
        } else {
            logger.warn("reminderId가 null 또는 빈 문자열이어서 저장되지 않았습니다.");
        }

        // 4. 결과 처리
        model.addAttribute("result", mailSendResult ? "성공" : "실패");
        return "reminder/result";
    }
}
