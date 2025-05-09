package com.erp.biztrack.reminder.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.reminder.model.dto.Reminder;

@Repository("reminderDao")
public class ReminderDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public ArrayList<Reminder> selectReminderList() {
        List<Reminder> list = sqlSessionTemplate.selectList("reminderMapper.selectReminderList");
        return (ArrayList<Reminder>) list;
    }
    
    public int updateReminderSmsContent(Reminder reminder) {
        return sqlSessionTemplate.update("reminderMapper.updateReminderSmsContent", reminder);
    }
}
