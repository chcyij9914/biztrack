package com.erp.biztrack.schedule.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.schedule.model.dto.Schedule;

@Repository("scheduleDao")
public class ScheduleDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public String selectNextScheduleId() {
	    return sqlSessionTemplate.selectOne("scheduleMapper.selectNextScheduleId");
	}

	// 일정 전체 목록
	public ArrayList<Schedule> selectAllSchedule() {
		List<Schedule> list = sqlSessionTemplate.selectList("scheduleMapper.selectAllSchedule");
		return (ArrayList<Schedule>) list;
	}
	
	// 로그인한 사용자 일정만 조회
	public ArrayList<Schedule> selectScheduleByEmpId(String empId) {
	    List<Schedule> list = sqlSessionTemplate.selectList("scheduleMapper.selectScheduleByEmpId", empId);
	    return (ArrayList<Schedule>) list;
	}
	
	// 일정 등록
	public int insertSchedule(Schedule schedule) {
		return sqlSessionTemplate.insert("scheduleMapper.insertSchedule", schedule);
	}
	
	// 일정 수정
	public int updateSchedule(Schedule schedule) {
	    return sqlSessionTemplate.update("scheduleMapper.updateSchedule", schedule);
	}
	
	// 일정 수정 폼
	public Schedule selectOneSchedule(String scId) {
	    return sqlSessionTemplate.selectOne("scheduleMapper.selectOneSchedule", scId);
	}
	
	// 일정 삭제
	public int deleteSchedule(String scId) {
		return sqlSessionTemplate.delete("scheduleMapper.deleteSchedule", scId);
	}
}
