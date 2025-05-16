package com.erp.biztrack.training.model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.trainingregistration.model.dto.TrainingRegistration;

public interface TrainingService {

	
	// 전체 교육 글 갯수 조회
	int selectListCount();
	// 페이징 적용된 교육 목록 조회
	ArrayList<Training> selectList(Paging paging);

	// 특정 교육 상세 조회
	Training selectTraining(String trainingId);

	// ------------------------ dml ----------------------------------------

	// 새 교육 등록
	int insertTraining(Training training);
	
	// 교육 글 삭제	
	 int deleteTraining(String trainingId); 
	 
	// 교육 글 수정
	int updateTraining(Training training);

	// ---------------------- 검색 관련 ---------------------------

	// 교육 제목 검색 목록
	ArrayList<Training> selectSearchTitle(Search search);
	int selectSearchTitleCount(String keyword);

	// 교육 내용 검색 목록
	int selectSearchcourseContentCount(String keyword);
	ArrayList<Training> selectSearchcourseContent(Search search);

	// 교육 강사명 검색 목록
	int selectSearchInstructorCount(String keyword);
	ArrayList<Training> selectSearchInstructor(Search search);
	ArrayList<Training> selectAll(Paging paging);

	
	int insertRegistration(TrainingRegistration registration);

	void registerTraining(Map<String, String> paramMap) throws Exception;
	/* void insertTrainingRegistration(Map<String, String> param); */
	
	List<Map<String, Object>> getAllRegistrations();
	List<Map<String, Object>> getTrainingStatusList();
	
	Training getTrainingById(String trainingId);
	void saveCompletedTraining(String userId, String trainingId);
	
	List<Training> getMyTrainings(String employeeId);
	List<TrainingRegistration> getMyTrainingList(String email);
	
	public ArrayList<Training> getTrainingsByRegistrant(String email); 
	List<Training> getTrainingsByEmail(String loginEmail);
	List<Map<String, Object>> getMyTrainingListByEmail(String email);
	
	int getEnrollmentCount(String trainingId);
	List<Training> getTrainingListByEmpId(String empId);
	
	/* int insertTrainingRegistration(Map<String, Object> paramMap); */
	
	int insertTrainingRegistration(Map<String, Object> data);
	boolean insertTrainingRegistration(TrainingRegistration reg);
	
	int getCurrentEnrollment(String trainingId);
	int getTrainingCapacity(String trainingId);
	Date getTrainingStartDate(String trainingId);
	Date getTrainingEndDate(String trainingId);
	

	

	
	  
}

