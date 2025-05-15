package com.erp.biztrack.evaluation.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.evaluation.model.dao.EvaluationDao;
import com.erp.biztrack.evaluation.model.dto.Evaluation;
import com.erp.biztrack.evaluation.model.dto.EvaluationFilterDto;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationDao evaluationDao;

    @Override
    public List<Evaluation> selectEvaluationListByEmpId(String evaluatorId) {
        return evaluationDao.selectEvaluationListByEmpId(evaluatorId);
    }

    @Override
    public Evaluation selectEvaluationById(String evaluationId) {
        return evaluationDao.selectEvaluationById(evaluationId);
    }

    @Override
    public List<Evaluation> selectHistoryByQuarter(String empId, int year, String quarter) {
        return evaluationDao.selectHistoryByQuarter(empId, year, quarter);
    }

    @Override
    public List<Evaluation> selectHalfOrFullYearAverage(String empId, int year, String quarter) {
        return evaluationDao.selectHalfOrFullYearAverage(empId, year, quarter);
    }

    @Override
    public void insertEvaluation(Evaluation evaluation) {
        evaluationDao.insertEvaluation(evaluation);
    }

    @Override
    public List<Evaluation> getEvaluationsByFilter(EvaluationFilterDto filter) {
        return evaluationDao.getEvaluationsByFilter(filter);
    }
    
    @Override
    public List<Evaluation> selectEvaluationByAuthority(EvaluationFilterDto filter) {
        return evaluationDao.selectEvaluationByAuthority(filter);
    }

    @Override
    public List<Employee> getEvaluableEmployeeList(String loginEmpId, String roleId, String deptId) {
        Map<String, Object> param = new HashMap<>();
        param.put("loginEmpId", loginEmpId);
        param.put("roleId", roleId);
        param.put("deptId", deptId);
        return evaluationDao.getEvaluableEmployees(param);
    }

    
}

