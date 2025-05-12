package com.erp.biztrack.evaluation.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.evaluation.model.dto.Evaluation;


@Repository
public class EvaluationDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    // 로그인한 팀장 또는 부서장의 사번으로 평가 목록 조회
    public List<Evaluation> selectEvaluationListByEmpId(String evaluatorId) {
        return sqlSessionTemplate.selectList("evaluationMapper.selectEvaluationListByEmpId", evaluatorId);
    }
   // 평가 ID로 상세 평가 조회
    public Evaluation selectEvaluationById(String evaluationId) {
        return sqlSessionTemplate.selectOne("evaluationMapper.selectEvaluationById", evaluationId);
    }

    // 평가 이력 조회 (사번, 연도, 분기)
    public List<Evaluation> selectHistoryByQuarter(String empId, int year, String quarter) {
        java.util.Map<String, Object> param = new java.util.HashMap<>();
        param.put("empId", empId);
        param.put("year", year);
        param.put("quarter", quarter);
        return sqlSessionTemplate.selectList("evaluationMapper.selectHistoryByQuarter", param);
    }
	public void insertEvaluation(Evaluation evaluation) {
		// TODO Auto-generated method stub
		
	}

}


