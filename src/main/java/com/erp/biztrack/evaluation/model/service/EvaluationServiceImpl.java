package com.erp.biztrack.evaluation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.evaluation.model.dao.EvaluationDao;
import com.erp.biztrack.evaluation.model.dto.Evaluation;

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
	public void insertEvaluation(Evaluation evaluation) {
		  evaluationDao.insertEvaluation(evaluation);
	}
    
   }
