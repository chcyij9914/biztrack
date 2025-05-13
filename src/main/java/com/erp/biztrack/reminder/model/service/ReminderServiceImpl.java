package com.erp.biztrack.reminder.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.SolapiReminder;
import com.erp.biztrack.reminder.model.dao.ReminderDao;
import com.erp.biztrack.reminder.model.dto.Reminder;

@Service("reminderService")
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderDao reminderDao;

    @Override
    public ArrayList<Reminder> selectReminderList() {
        return reminderDao.selectReminderList();
    }
    
    @Override
	public int updateReminderSmsContent(Reminder reminder) {
		return reminderDao.updateReminderSmsContent(reminder);
	}
   
    @Override
    public String sendSms(String reminderId, String phone, String content) {
    
            String apiKey ="NCSHBVKWDDPZL1K8";
            String apiSecret ="F1YR1EKGFTPTWMMDN1LBNJEGMVMPSFGD";
            String sender ="01086527442"; 

            String result = SolapiReminder.sendSms(apiKey, apiSecret, sender, phone, content);

            if (result.contains("성공")) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(reminderId);
                reminder.setSmsContent(content);
                reminderDao.updateReminderSmsContent(reminder);
            }

            return result;
    }
}
