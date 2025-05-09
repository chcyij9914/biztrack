package com.erp.biztrack.reminder.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.biztrack.reminder.model.dto.Reminder;
import com.erp.biztrack.reminder.model.service.ReminderService;

@Controller
@RequestMapping("/reminder")
public class ReminderController {
	private static final Logger logger = LoggerFactory.getLogger(ReminderController.class);

	@Autowired
	private ReminderService reminderService;

	@GetMapping("/ReminderList.do")
	public String reminderList(Model model) {
		List<Reminder> reminderList = reminderService.selectReminderList();
		System.out.println("Reminder 리스트 개수: " + reminderList.size());
		for (Reminder r : reminderList) {
			System.out.println(r);
		}
		model.addAttribute("reminderList", reminderList);
		return "reminder/reminderList";
	}

	@PostMapping("/SendSms.do")
	@ResponseBody
	public ResponseEntity<String> sendSms(
			@RequestParam("reminderId") String reminderId,
			@RequestParam("phone") String phone,
			@RequestParam("content") String content) {
		System.out.println(">>> 리마인더ID: " + reminderId);
		System.out.println(">>> 수신번호: " + phone);
		System.out.println(">>> 문자내용: " + content);
		String result = reminderService.sendSms(reminderId, phone, content);
		return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(result);
	}
}
