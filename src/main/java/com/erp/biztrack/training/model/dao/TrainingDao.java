package com.erp.biztrack.training.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.trainingregistration.model.dto.TrainingRegistration;

@Repository
public class TrainingDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "trainingMapper.";
	
	public int selectListCount() {
		return sqlSessionTemplate.selectOne("trainingMapper.selectListCount");
	}

    //  페이징 적용된 교육 목록 조회
    public ArrayList<Training> selectList(Paging paging) {
        List<Training> list = sqlSessionTemplate.selectList("trainingMapper.selectList", paging);
        return (ArrayList<Training>) list;
    }
    
    public List<Training> selectAll() {
        return sqlSessionTemplate.selectList("trainingMapper.selectAll");
    }

    //  특정 교육 상세 조회
    public Training selectTraining(String trainingId) {
        return sqlSessionTemplate.selectOne("trainingMapper.selectTraining", trainingId);
    }

    // 새 교육 등록
    public int insertTraining(Training training) {
        return sqlSessionTemplate.insert("trainingMapper.insertTraining", training);
    }
    
    // 새 교육 수정
    public int updateTraining(Training training) {
        return sqlSessionTemplate.update("trainingMapper.updateTraining", training);
    }
    
    // 교육 삭제 	
	  public int deleteTraining(String trainingId) { 
		  return sqlSessionTemplate.delete("trainingMapper.deleteTraining", trainingId); 
	  }
	 

    //  교육 제목으로 검색한 목록 조회
    public ArrayList<Training> selectSearchTitle(Search search) {
       List<Training> list = sqlSessionTemplate.selectList("trainingMapper.selectSearchTitle", search);
        return (ArrayList<Training>) list;
    }

    //  교육 내용으로 검색한 목록 조회
    public ArrayList<Training> selectSearchcourseContent(Search search) {
        List<Training> list = sqlSessionTemplate.selectList("trainingMapper.selectSearchcourseContent", search);
        return (ArrayList<Training>) list;
    }

    //  교육 강사명으로 검색한 목록 조회
    public ArrayList<Training> selectSearchInstructor(Search search) {
        List<Training> list = sqlSessionTemplate.selectList("trainingMapper.selectSearchInstructor", search);
        return (ArrayList<Training>) list;
    }

	public int selectSearchTitleCount(String keyword) {
		return sqlSessionTemplate.selectOne("trainingMapper.selectSearchTitleCount", keyword);
	}

	public int selectSearchcourseContentCount(String keyword) {
		  return sqlSessionTemplate.selectOne("trainingMapper.selectSearchcourseContentCount", keyword);
	}

	public int selectSearchInstructorCount(String keyword) {
		 return sqlSessionTemplate.selectOne("trainingMapper.selectSearchInstructorCount", keyword);
	}

	
	public int insertRegistration(TrainingRegistration registration) {
	    return sqlSessionTemplate.insert("trainingMapper.insertRegistration", registration);
	}

	public Object insertTrainingRegistration(Map<String, String> param) {
		return sqlSessionTemplate.insert("trainingMapper.insertTrainingRegistration", param);
	}
	
	public void insertApplicant(Training training) {
		sqlSessionTemplate.insert(NAMESPACE + "insertApplicant", training);
    }

	public Training getTrainingById(String trainingId) {
		return sqlSessionTemplate.selectOne("trainingMapper.selectTrainingById", trainingId);
	}

	/*
	 * public Training selectApplicant(String trainingId, String name, String birth,
	 * String phone) { Map<String, Object> map = new HashMap<>();
	 * map.put("trainingId", trainingId); map.put("name", name); map.put("birth",
	 * birth); map.put("phone", phone); return
	 * sqlSessionTemplate.selectOne(NAMESPACE + "selectApplicant", map); }
	 */

	
}
  
