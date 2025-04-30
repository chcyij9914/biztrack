package com.erp.biztrack.training.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.training.model.dto.Training;

@Repository
public class TrainingDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int selectListCount() {
		return sqlSessionTemplate.selectOne("trainingMapper.selectListCount");
	}

    //  페이징 적용된 교육 목록 조회
    public ArrayList<Training> selectList(Paging paging) {
        List<Training> list = sqlSessionTemplate.selectList("trainingMapper.selectList", paging);
        return (ArrayList<Training>) list;
    }

    //  특정 교육 상세 조회
    public Training selectTraining(String trainingId) {
        return sqlSessionTemplate.selectOne("trainingMapper.selectTraining", trainingId);
    }

    // 새 교육 등록
    public int insertTraining(Training training) {
        return sqlSessionTemplate.insert("trainingMapper.insertTraining", training);
    }

    //  교육 제목으로 검색한 목록 조회
    public ArrayList<Training> selectSearchTitle(Search search) {
        List<Training> list = sqlSessionTemplate.selectList("trainingMapper.selectSearchTitle", search);
        return (ArrayList<Training>) list;
    }

    //  교육 내용으로 검색한 목록 조회
    public ArrayList<Training> selectSearchContent(Search search) {
        List<Training> list = sqlSessionTemplate.selectList("trainingMapper.selectSearchContent", search);
        return (ArrayList<Training>) list;
    }

    //  교육 날짜로 검색한 목록 조회
    public ArrayList<Training> selectSearchDate(Search search) {
        List<Training> list = sqlSessionTemplate.selectList("trainingMapper.selectSearchDate", search);
        return (ArrayList<Training>) list;
    }

    //  Ajax 테스트용 : 가장 마지막 등록된 교육 1개 조회
    public Training selectList() {
        return sqlSessionTemplate.selectOne("trainingMapper.selectLast");
    }

	public int deleteTraining(String trainingId) {
		return 0;
	}

	public int updateTraining(Training training) {
		return 0;
	}
	
}
  
