package com.erp.biztrack.schedule.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.schedule.model.dto.Schedule;

public interface ScheduleService {

	String selectNextScheduleId();
	ArrayList<Schedule> selectAllSchedule();	// 일정목록
	ArrayList<Schedule> selectScheduleByEmpId(String empId); // 로그인한 사용자 일정만 조회
	int insertSchedule(Schedule schedule);		// 일정등록
	int updateSchedule(Schedule schedule); 	// 일정 수정
	Schedule selectOneSchedule(String scId);	// 수정 폼
	int deleteSchedule(String scId);				// 일정 삭제
	
	List<Schedule> searchByTitle(Schedule param);	// 제목 검색
    List<Schedule> searchByType(Schedule param);	// 유형 검색
    List<Schedule> searchByDate(Schedule param);	// 날짜 검색
}
