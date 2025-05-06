package com.erp.biztrack.reminder.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("reminderList", reminderList);
        return "reminder/reminderList"; 
    }
}
