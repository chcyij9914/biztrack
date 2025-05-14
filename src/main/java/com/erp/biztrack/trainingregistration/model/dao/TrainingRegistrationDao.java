package com.erp.biztrack.trainingregistration.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.trainingregistration.model.dto.TrainingRegistration;

@Repository 
public class TrainingRegistrationDao {
	
	  @Autowired 
	  private SqlSessionTemplate sqlSessionTemplate;
	  private final String namespace = "trainingregistrationMapper.";

	  public List<TrainingRegistration> selectByTrainingId(String trainingId) {
	        return sqlSessionTemplate.selectList(namespace + "selectByTrainingId", trainingId);
	    }

	  public List<Map<String, Object>> getTrainingStatusList() {
	        return sqlSessionTemplate.selectList("trainingregistrationMapper.selectTrainingStatusList");
	    }

	public List<Map<String, Object>> selectAllCourseWithStatus() {
		return sqlSessionTemplate.selectList("trainingregistrationMapper.selectAllCourseWithStatus");

	}
	
	/*
	 * public List<TrainingRegistration> selectMyTrainingList(String email) { return
	 * sqlSessionTemplate.selectList(
	 * "trainingregistrationMapper.selectMyTrainingList", email); }
	 * 
	 */
}
