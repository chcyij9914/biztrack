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
	
	// 리마인더 목록 조회
    public ArrayList<Reminder> selectReminderList(SqlSessionTemplate session) {
        List<Reminder> list = session.selectList("reminderMapper.selectReminderList");
        return (ArrayList<Reminder>) list;
    }
}
