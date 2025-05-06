package com.erp.biztrack.reminder.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;

import com.erp.biztrack.reminder.model.dto.Reminder;

public interface ReminderService {
	
	 ArrayList<Reminder> selectReminderList();
}
