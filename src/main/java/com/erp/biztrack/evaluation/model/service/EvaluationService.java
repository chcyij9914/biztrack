package com.erp.biztrack.evaluation.model.service;

import java.util.List;
import java.util.Map;

import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.evaluation.model.dto.Evaluation;
import com.erp.biztrack.evaluation.model.dto.EvaluationFilterDto;

public interface EvaluationService {
    List<Evaluation> selectEvaluationListByEmpId(String evaluatorId);
    Evaluation selectEvaluationById(String evaluationId);
    List<Evaluation> selectEvaluationByAuthority(EvaluationFilterDto filter);
    List<Evaluation> selectHistoryByQuarter(String empId, int year, String quarter);
    List<Evaluation> selectHalfOrFullYearAverage(String empId, int year, String quarter);
    void insertEvaluation(Evaluation evaluation);
    List<Evaluation> getEvaluationsByFilter(EvaluationFilterDto filter);
    List<Employee> getEvaluableEmployeeList(String loginEmpId, String roleId, String deptId);


}