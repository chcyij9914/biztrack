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
public class TrainingRegistrationServiceImpl implements TrainingRegistrationService {

	@Autowired
	private TrainingRegistrationDao trainingRegistrationDao;

	@Override
	public List<TrainingRegistration> selectByTrainingId(String trainingId) {
		return trainingRegistrationDao.selectByTrainingId(trainingId);
	}

	@Override
	public List<Map<String, Object>> getTrainingStatusList() {
		return trainingRegistrationDao.getTrainingStatusList();
	}
	
	  @Override
	    public List getAllCourseWithStatus() {
	        return trainingRegistrationDao.selectAllCourseWithStatus();
	    }

	  @Override
	  public List<Map<String, Object>> getAllRegistrations() {
	      return trainingRegistrationDao.selectAllCourseWithStatus();
	  }

	@Override
	public Training getTrainingById(String trainingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertTrainingRegistration(TrainingRegistration dto) {
		// TODO Auto-generated method stub
		
	}

	

}
