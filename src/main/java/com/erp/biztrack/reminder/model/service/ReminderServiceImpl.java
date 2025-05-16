package com.erp.biztrack.reminder.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.reminder.model.dao.ReminderDao;
import com.erp.biztrack.reminder.model.dto.Reminder;

@Service("reminderService")
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderDao reminderDao;

    @Override
	public List<Reminder> selectAllReminders() {
    	return reminderDao.selectAllReminders();
	}

    @Override
    public int updateMailContent(Reminder reminder) {
        return reminderDao.updateMailContent(reminder);
    }

    @Override
    public String selectClientEmail(String clientName) {
        return reminderDao.selectClientEmail(clientName);
    }
}
