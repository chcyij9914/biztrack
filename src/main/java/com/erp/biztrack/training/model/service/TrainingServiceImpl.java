package com.erp.biztrack.training.model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.training.model.dao.TrainingDao;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.trainingregistration.model.dto.TrainingRegistration;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingDao trainingDao;

	// 전체 목록 갯수
	@Override
	public int selectListCount() {
		return trainingDao.selectListCount();
	}

	// 페이징 적용된 전체목록
	@Override
	public ArrayList<Training> selectList(Paging paging) {
		return trainingDao.selectList(paging);
	}

	@Override
	public ArrayList<Training> selectAll(Paging paging) {
		return trainingDao.selectList(paging);
	}

	// 제목 검색 목록 조회
	@Override
	public ArrayList<Training> selectSearchTitle(Search search) {
		return trainingDao.selectSearchTitle(search);
	}

	// 내용검색 목록 조회
	@Override
	public ArrayList<Training> selectSearchcourseContent(Search search) {
		return trainingDao.selectSearchcourseContent(search);
	}

	// 강사명 검색 목록 조회
	@Override
	public ArrayList<Training> selectSearchInstructor(Search search) {
		return trainingDao.selectSearchInstructor(search);
	}

	// 상세보기용
	@Override
	public Training selectTraining(String trainingId) {
		return trainingDao.selectTraining(trainingId);
	}

	// 교육 등록
	@Override
	public int insertTraining(Training training) {
		return trainingDao.insertTraining(training);
	}

	// 교육 삭제
	@Override
	public int deleteTraining(String trainingId) {
		return trainingDao.deleteTraining(trainingId);
	}

	// 교육 수정
	@Override
	public int updateTraining(Training training) {
		return trainingDao.updateTraining(training);
	}

	// 제목 검색 목록 총갯수
	@Override
	public int selectSearchTitleCount(String keyword) {
		return trainingDao.selectSearchTitleCount(keyword);
	}

	// 내용 검색 목록 총 갯수
	@Override
	public int selectSearchcourseContentCount(String keyword) {
		return trainingDao.selectSearchcourseContentCount(keyword);
	}

	// 강사명 검색 목록 총 갯수
	@Override
	public int selectSearchInstructorCount(String keyword) {
		return trainingDao.selectSearchInstructorCount(keyword);
	}

	@Override
	public int insertRegistration(TrainingRegistration registration) {
		return trainingDao.insertRegistration(registration);
	}

	/*
	 * @Override public void registerTraining(Map<String, String> paramMap) throws
	 * Exception { trainingDao.insertTrainingRegistration(paramMap); }
	 * 
	 * @Override public void insertTrainingRegistration(Map<String, String> param) {
	 * trainingDao.insertTrainingRegistration(param);
	 * 
	 * }
	 */

	/*
	 * @Override public int insertTrainingRegistration(Map<String, Object> paramMap)
	 * { return trainingDao.insertTrainingRegistration(paramMap); }
	 */

	@Override
	public Training getTrainingById(String trainingId) {
		return trainingDao.getTrainingById(trainingId);
	}

	@Override
	public ArrayList<Training> getTrainingsByRegistrant(String email) {
		return trainingDao.selectTrainingsByRegistrant(email);
	}

	@Override
	public List<Training> getTrainingsByEmail(String loginEmail) {
		return trainingDao.selectTrainingsByEmail(loginEmail);
	}

	@Override
	public List<Training> getMyTrainings(String employeeId) {
		return trainingDao.selectTrainingsByRegistrant(employeeId);
	}

	@Override
	public List<TrainingRegistration> getMyTrainingList(String email) {
		return trainingDao.selectMyTrainingList(email);
	}

	@Override
	public List<Map<String, Object>> getMyTrainingListByEmail(String email) {
		return trainingDao.getTrainingListByEmail(email);
	}

	@Override
	public List<Map<String, Object>> getAllRegistrations() {
		return null;
		/* return trainingDao.getAllRegistrations(Registrations) */
	}

	@Override
	public List<Map<String, Object>> getTrainingStatusList() {
		return null;
		/* return trainingDao. getTrainingStatusList(StatusList); */
	}

	@Override
	public void saveCompletedTraining(String userId, String trainingId) {
	}

	@Override
	public int getEnrollmentCount(String trainingId) {
		  return trainingDao.getEnrollmentCount(trainingId);
	}

	@Override
	public List<Training> getTrainingListByEmpId(String empId) {
		return trainingDao.getTrainingListByEmpId(empId);
	}

	@Override
	public void registerTraining(Map<String, String> paramMap) throws Exception {
		
	}
	
//	@Override
//	public boolean insertTrainingRegistration(TrainingRegistration reg) {
//	    Map<String, Object> param = new HashMap<>();
//	    param.put("registrationId", reg.getRegistrationId());
//	    param.put("trainingId", reg.getTrainingId());
//
//	    int count = trainingDao.checkDuplicateRegistration(param);
//
//	    if (count > 0) {
//	        // 이미 신청했으므로 삽입하지 않음
//	        return false;
//	    }
//
//	    // 중복 아니면 등록
//	    return trainingDao.insertTrainingRegistration(reg) > 0;
//	}
	
	@Override
	public int insertTrainingRegistration(Map<String, Object> data) {
	    return trainingDao.insertTrainingRegistration(data);
	}

	@Override
	public boolean insertTrainingRegistration(TrainingRegistration reg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCurrentEnrollment(String trainingId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTrainingCapacity(String trainingId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date getTrainingStartDate(String trainingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getTrainingEndDate(String trainingId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}