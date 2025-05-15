package com.erp.biztrack.evaluation.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.evaluation.model.dto.Evaluation;
import com.erp.biztrack.evaluation.model.dto.EvaluationFilterDto;

@Repository
public class EvaluationDao {

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Evaluation> selectEvaluationListByEmpId(String evaluatorId) {
        return sqlSessionTemplate.selectList("evaluationMapper.selectEvaluationListByEmpId", evaluatorId);
    }

    public Evaluation selectEvaluationById(String evaluationId) {
        return sqlSessionTemplate.selectOne("evaluationMapper.selectEvaluationById", evaluationId);
    }

    public List<Evaluation> selectHistoryByQuarter(String empId, int year, String quarter) {
        Map<String, Object> param = new HashMap<>();
        param.put("empId", empId);
        param.put("year", year);
        param.put("quarter", quarter);
        return sqlSessionTemplate.selectList("evaluationMapper.selectHistoryByQuarter", param);
    }

    public List<Evaluation> selectHalfOrFullYearAverage(String empId, int year, String period) {
        Map<String, Object> param = new HashMap<>();
        param.put("empId", empId);
        param.put("year", year);
        param.put("period", period);
        return sqlSessionTemplate.selectList("evaluationMapper.selectHalfOrFullYearAverage", param);
    }

    public void insertEvaluation(Evaluation evaluation) {
        sqlSessionTemplate.insert("evaluationMapper.insertEvaluation", evaluation);
    }

    public List<Evaluation> getEvaluationsByFilter(EvaluationFilterDto filter) {
        return sqlSessionTemplate.selectList("evaluationMapper.getEvaluationsByFilter", filter);
    }
    
    public List<Evaluation> selectEvaluationByAuthority(EvaluationFilterDto filter) {
        return sqlSessionTemplate.selectList("evaluationMapper.selectEvaluationByAuthority", filter);
    }
    
    public List<Employee> getEvaluableEmployees(Map<String, Object> param) {
        return sqlSessionTemplate.selectList("evaluationMapper.getEvaluableEmployees", param);
    }

}