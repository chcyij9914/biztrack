package com.erp.biztrack.reminder.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.reminder.model.dto.Reminder;

@Repository
public class ReminderDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 리마인더 전체 목록 조회
    public ArrayList<Reminder> selectAllReminders() {
        return (ArrayList) sqlSession.selectList("reminderMapper.selectAllReminders");
    }

    // 메일 내용 저장 (mailContent 업데이트)
    public int updateMailContent(Reminder reminder) {
        return sqlSession.update("reminderMapper.updateMailContent", reminder);
    }

    // 거래처 이메일 조회 (필요한 경우)
    public String selectClientEmail(String clientName) {
        return sqlSession.selectOne("reminderMapper.selectClientEmail", clientName);
    }
}
