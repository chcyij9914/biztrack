package com.erp.biztrack.evaluation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.evaluation.model.dto.Evaluation;
import com.erp.biztrack.evaluation.model.dto.EvaluationFilterDto;
import com.erp.biztrack.evaluation.model.service.EvaluationService;
import com.erp.biztrack.login.model.dto.LoginDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    // 직원 평가 목록 페이지 (기본 + 분기 필터용)
    @RequestMapping("evaluationList.do")
    public String listEvaluation(
        @RequestParam(required = false) String year,
        @RequestParam(required = false) String quarter,
        HttpSession session, Model model
    ) {
        LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
        if (loginInfo == null) return "redirect:/login.do";

        String empId = loginInfo.getEmpId();
        String roleId = loginInfo.getRoleId();
        String deptId = loginInfo.getDeptId();

        EvaluationFilterDto filter = new EvaluationFilterDto();
        filter.setEmpId(empId);
        filter.setRoleId(roleId);
        filter.setDeptId(deptId);
        if (year != null) filter.setYear(year);
        if (quarter != null) filter.setQuarter(quarter);

        List<Evaluation> evaluationList = evaluationService.selectEvaluationByAuthority(filter);
        model.addAttribute("evaluationList", evaluationList);

        // 선택된 값 유지
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedQuarter", quarter);

        return "evaluation/evaluationList";
    }

    // 분기 필터 버튼용 엔드포인트
    @RequestMapping("filter.do")
    public String filterEvaluation(
        @RequestParam(required = false) String year,
        @RequestParam(required = false) String quarter,
        HttpSession session, Model model
    ) {
        // 그냥 위에 있는 listEvaluation 메서드 재사용!
        return listEvaluation(year, quarter, session, model);
    }

    // 상세 페이지
    @RequestMapping("/detail.do")
    public String evaluationDetail(String evaluationId, Model model) {
        Evaluation eval = evaluationService.selectEvaluationById(evaluationId);
        model.addAttribute("evaluation", eval);
        return "evaluation/evaluationDetail";
    }
    
    // 평가 페이지 해당 직원
    @RequestMapping("/registerForm.do")
    public String showRegisterForm(HttpSession session, Model model) {
        LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
        if (loginInfo == null) return "redirect:/login.do";

        List<Employee> selectableEmployees = evaluationService.getEvaluableEmployeeList(
            loginInfo.getEmpId(),
            loginInfo.getRoleId(),
            loginInfo.getDeptId()
        );

        model.addAttribute("employeeList", selectableEmployees);
        return "evaluation/evaluationRegisterForm"; 
    }
    
    //직원 평가 등록 
    @RequestMapping("/register.do")
    public String registerEvaluation(Evaluation evaluation, HttpSession session) {
        LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
        if (loginInfo == null) return "redirect:/login.do";

        // 1. 평가자 정보 설정
        evaluation.setEvaluatorId(loginInfo.getEmpId());

        // 2. 총점 계산
        int total = evaluation.getScoreSales() 
                  + evaluation.getScoreAttitude();
        evaluation.setTotalScore(total);

        // 3. 등급 계산
        String grade;
        if (total >= 181) grade = "A";
        else if (total >= 161) grade = "B";
        else if (total >= 141) grade = "C";
        else if (total >= 121) grade = "D";
        else if (total >= 101) grade = "E";
        else grade = "F";
        evaluation.setGrade(grade);

        // ✅ 4. 저장 (recordId 없이 quarter만 사용)
        evaluationService.insertEvaluation(evaluation);

        // 5. 목록으로 이동
        return "redirect:/evaluation/evaluationList.do";
    }



}
