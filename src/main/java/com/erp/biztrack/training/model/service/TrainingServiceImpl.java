package com.erp.biztrack.training.model.service;

import java.util.ArrayList;
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

	@Override
    public void registerTraining(Map<String, String> paramMap) throws Exception {
        trainingDao.insertTrainingRegistration(paramMap);
	}

	@Override
	public void insertTrainingRegistration(Map<String, String> param) {
		 trainingDao.insertTrainingRegistration(param);
		
	}

	@Override
	public Training getTrainingById(String trainingId) {
	    return trainingDao.getTrainingById(trainingId);
	}

	@Override
	public List<Map<String, Object>> getAllRegistrations() {
		return null;
	}

	@Override
	public List<Map<String, Object>> getTrainingStatusList() {
		return null;
	}

	@Override
	public void saveCompletedTraining(String userId, String trainingId) {
	}
}