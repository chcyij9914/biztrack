package com.erp.biztrack.trainingregistration.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.trainingregistration.model.dto.TrainingRegistration;

public interface TrainingRegistrationService{
	
	  // 전체 수강신청 글 갯수 조회 
	    List<TrainingRegistration> selectByTrainingId(String trainingId);
	}

	
		/*
		 * int selectListCount();
		 * 
		 * ArrayList<TrainingRegistration> selectJoinedRegistrations();
		 * 
		 * int insertRegistration(Map<String, Object> paramMap);
		 * 
		 * ArrayList<Training> selectAllTrainings();
		 */
	 

