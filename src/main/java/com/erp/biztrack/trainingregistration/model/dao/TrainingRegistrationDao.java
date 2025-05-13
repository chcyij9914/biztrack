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

		/*
		 * // 수강신청 전체 목록 갯수 public int selectListCount() { return
		 * sqlSessionTemplate.selectOne("trainingregistrationMapper.selectListCount"); }
		 * 
		 * // 페이징 적용된 전체목록 public ArrayList<TrainingRegistration>
		 * selectJoinedRegistrations() { List<TrainingRegistration> list =
		 * sqlSessionTemplate.selectList(
		 * "trainingregistrationMapper.selectJoinedTrainingRegistrations"); return
		 * (ArrayList<TrainingRegistration>) list; }
		 * 
		 * 
		 * // 수강신청 등록 public int insertRegistration(Map<String, Object> paramMap) {
		 * return sqlSessionTemplate.insert(NAMESPACE + "insertRegistration", paramMap);
		 * }
		 * 
		 * public ArrayList<Training> selectAllTrainings() { List<Training> list =
		 * sqlSessionTemplate.selectList("trainingregistrationMapper.selectAllTrainings"
		 * ); return (ArrayList<Training>) list; }
		 */
	  public List<TrainingRegistration> selectByTrainingId(String trainingId) {
	        return sqlSessionTemplate.selectList(namespace + "selectByTrainingId", trainingId);
	    }


}
