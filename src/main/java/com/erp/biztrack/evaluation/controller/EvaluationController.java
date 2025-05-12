package com.erp.biztrack.evaluation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erp.biztrack.evaluation.model.dto.Evaluation;
import com.erp.biztrack.evaluation.model.service.EvaluationService;
import com.erp.biztrack.login.model.dto.LoginDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController {
    private static final Logger logger = LoggerFactory.getLogger(EvaluationController.class);

    @Autowired
    private EvaluationService evaluationService;

    // 직원 평가 목록 조회
    @RequestMapping("evaluationList.do")
    public String listEvaluation(HttpSession session, Model model) {
        // 로그인한 사용자의 사번
    	LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
    	String evaluatorId = loginInfo.getEmpId();
        // 평가 목록 조회
        List<Evaluation> evaluationList = evaluationService.selectEvaluationListByEmpId(evaluatorId);
        // 뷰로 전달
        model.addAttribute("evaluationList", evaluationList);
        return "evaluation/evaluationList"; // /WEB-INF/views/evaluation/evaluationList.jsp
    }
    @RequestMapping("/detail.do")
   public String evaluationDetail(String evaluationId, Model model) {
        Evaluation eval = evaluationService.selectEvaluationById(evaluationId);
        model.addAttribute("evaluation", eval);
        return "evaluation/evaluationDetail";
    }
    @RequestMapping("/history.do")
    public String evaluationHistory( String empId, int year,
                                     String quarter,
                                    Model model) {
    	List<Evaluation> historyList = evaluationService.selectHistoryByQuarter(empId, year, quarter);
  
         model.addAttribute("historyList", historyList);
         return "evaluation/evaluationHistory";
     }
        
}
