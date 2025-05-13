package com.erp.biztrack.trainingregistration.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.trainingregistration.model.dto.TrainingRegistration;
import com.erp.biztrack.trainingregistration.model.service.TrainingRegistrationService;

@Controller
public class TrainingRegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(TrainingRegistrationController.class);

	@Autowired
	/* private TrainingRegistrationService trainingregistrationService; */
	private TrainingRegistrationService trainingRegistrationService;

	// 수강신청 페이지로 이동
	@RequestMapping(value = "training/registration.do", method = RequestMethod.GET)
	public String trainingregistrationForm() {
		return "trainingregistration";
	}

	@RequestMapping("/trainingregistration/history.do")
	public String showRegistrationHistory(Model model) {
		List<Map<String, Object>> list = trainingRegistrationService.getAllRegistrations();
		model.addAttribute("courses", list); // <- 중요

		// 수강신청 가능한 교육 과정 리스트 가져오기
		List<Map<String, Object>> courseList = trainingRegistrationService.getTrainingStatusList();
		model.addAttribute("courses", courseList);
		List<Map<String, Object>> statusList = trainingRegistrationService.getTrainingStatusList();
		model.addAttribute("statusList", statusList);

		return "trainingregistration/history"; // JSP 페이지 경로
	}

	@RequestMapping("/trainingregistration/applicant.do")
		
		  public String showApplicantPage(Model model) { List<Map<String, Object>> list
		  = trainingRegistrationService.getAllRegistrations(); // 예시 메서드
		  model.addAttribute("registrationList", list); List<Map<String, Object>>
		  courseList = trainingRegistrationService.getTrainingStatusList();
		  model.addAttribute("courses", courseList);	 
		// 이 코드가 있어야 JSP에서 ${courses} 사용 가능

		// 수강신청자 목록 조회 등 로직 작성
		return "trainingregistration/applicant";
	}
	
	@RequestMapping("/training/form.do")
	public String registerTraining(
			@ModelAttribute TrainingRegistration dto, RedirectAttributes redirectAttributes) {
	    trainingRegistrationService.insertTrainingRegistration(dto);  // DB에 저장

	    // training_id에 해당하는 교육 정보 조회
	    Training training = trainingRegistrationService.getTrainingById(dto.getTrainingId());  // 반드시 service에 있어야 함

	    // redirect 시 전달할 데이터 (Flash Scope)
	    redirectAttributes.addFlashAttribute("training", training);
	    redirectAttributes.addFlashAttribute("registration", dto);  // 신청자 정보도 함께

	    // 수강내역 페이지로 이동
	    return "redirect:/trainingregistration/applicant.do";
	}


}