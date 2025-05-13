package com.erp.biztrack.trainingregistration.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.trainingregistration.model.dto.TrainingRegistration;
import com.erp.biztrack.trainingregistration.model.service.TrainingRegistrationService;

@Controller
public class TrainingRegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(TrainingRegistrationController.class);

	@Autowired
	private TrainingRegistrationService trainingregistrationService;
	
	// 수강신청 페이지로 이동
	@RequestMapping(value = "training/registration.do", method = RequestMethod.GET)
	public String trainingregistrationForm() {
		return "trainingregistration";
	}
	/*
	 * @RequestMapping("training/registrationView.do") public ModelAndView
	 * trainingRegistrationView(@RequestParam("id") String trainingId, ModelAndView
	 * mv) { logger.info("registrationView.do : " + trainingId);
	 * 
	 * Training training = trainingService.selectTraining(trainingId);
	 * 
	 * if (training != null) { mv.addObject("training", training); // 교육 정보 전달
	 * mv.setViewName("training/registrationView"); // JSP 파일 위치 } else {
	 * mv.addObject("message", trainingId + "번 수강신청 교육 정보 조회 실패!");
	 * mv.setViewName("common/error"); }
	 * 
	 * return mv; }
	 */
	
}