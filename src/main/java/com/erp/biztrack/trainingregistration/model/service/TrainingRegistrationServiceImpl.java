package com.erp.biztrack.trainingregistration.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.trainingregistration.model.dao.TrainingRegistrationDao;
import com.erp.biztrack.trainingregistration.model.dto.TrainingRegistration;

@Service
public class TrainingRegistrationServiceImpl implements TrainingRegistrationService{
	
	
	  @Autowired 
	  private TrainingRegistrationDao trainingregistrationDao;
	  
	  @Override
	    public List<TrainingRegistration> selectByTrainingId(String trainingId) {
	        return trainingregistrationDao.selectByTrainingId(trainingId);
	    }
	  
	  // 수강신청 전체 목록 갯수  
		/*
		 * @Override public int selectListCount() { return
		 * trainingregistrationDao.selectListCount(); }
		 * 
		 * // 페이징 적용된 전체목록
		 * 
		 * @Override public ArrayList<TrainingRegistration> selectJoinedRegistrations()
		 * { return trainingregistrationDao.selectJoinedRegistrations(); }
		 * 
		 * // 전체 수강신청 글 업데이트
		 * 
		 * @Override public int insertRegistration(Map<String, Object> paramMap) {
		 * return trainingregistrationDao.insertRegistration(paramMap);
		 * 
		 * }
		 * 
		 * @Override public ArrayList<Training> selectAllTrainings() { return
		 * trainingregistrationDao.selectAllTrainings(); }
		 */
}
