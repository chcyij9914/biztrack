package com.erp.biztrack.evaluation.model.service;

import java.util.List;

import com.erp.biztrack.evaluation.model.dto.Evaluation;

public interface EvaluationService {
	// 사번 기준 평가 목록 조회
	List<Evaluation> selectEvaluationListByEmpId(String evaluatorId);
	// 평가 ID로 상세 조회
    Evaluation selectEvaluationById(String evaluationId);
    // 평가 이력 조회 (사번, 연도, 분기)
    List<Evaluation> selectHistoryByQuarter(String empId, int year, String quarter);
    void insertEvaluation(Evaluation evaluation);
}
