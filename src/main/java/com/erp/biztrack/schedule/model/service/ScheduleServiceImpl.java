package com.erp.biztrack.schedule.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.schedule.model.dao.ScheduleDao;
import com.erp.biztrack.schedule.model.dto.Schedule;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleDao scheduleDao;
	
	@Override
	public String selectNextScheduleId() {
		return scheduleDao.selectNextScheduleId();
	}

	@Override
	public ArrayList<Schedule> selectAllSchedule() {
		return scheduleDao.selectAllSchedule();
	}
	
	@Override
	public ArrayList<Schedule> selectScheduleByEmpId(String empId) {
		return scheduleDao.selectScheduleByEmpId(empId);
	}

	@Override
	public int insertSchedule(Schedule schedule) {
		return scheduleDao.insertSchedule(schedule);
	}

	@Override
	public int updateSchedule(Schedule schedule) {
		return scheduleDao.updateSchedule(schedule);
	}

	@Override
	public Schedule selectOneSchedule(String scId) {
		return scheduleDao.selectOneSchedule(scId);
	}

	@Override
	public int deleteSchedule(String scId) {
		return scheduleDao.deleteSchedule(scId);
	}

	@Override
	public List<Schedule> searchByTitle(Schedule param) {
	    return scheduleDao.searchByTitle(param);
	}

	@Override
	public List<Schedule> searchByType(Schedule param) {
	    return scheduleDao.searchByType(param);
	}

	@Override
	public List<Schedule> searchByDate(Schedule param) {
	    return scheduleDao.searchByDate(param);
	}
	
}
