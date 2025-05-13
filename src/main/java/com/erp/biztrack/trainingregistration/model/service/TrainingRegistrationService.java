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

	    List<Map<String, Object>> getAllRegistrations();
		

		static Training selectTraining(String trainingId) {
			return null;
		}
		
		List<Map<String, Object>> getTrainingStatusList();

		List<TrainingRegistration> getAllCourseWithStatus();

		Training getTrainingById(String trainingId);
		void insertTrainingRegistration(TrainingRegistration dto);
		

	}



