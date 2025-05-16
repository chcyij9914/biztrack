package com.erp.biztrack.reminder.model.service;

import java.util.List;

import com.erp.biztrack.reminder.model.dto.Reminder;

public interface ReminderService {
	 // 리마인더 전체 조회
    List<Reminder> selectAllReminders();

    // 메일 전송 후 내용 저장
    int updateMailContent(Reminder reminder);

    // 거래처 이메일 조회
    String selectClientEmail(String clientName);
}

