package com.erp.biztrack.reminder.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.reminder.model.dao.ReminderDao;
import com.erp.biztrack.reminder.model.dto.Reminder;

@Service("reminderService")
public class ReminderServiceImpl implements ReminderService{
	
	@Autowired
	private ReminderDao reminderDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public ArrayList<Reminder> selectReminderList() {
		return reminderDao.selectReminderList(sqlSession);
	}
	
}
