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

import jakarta.servlet.http.HttpSession;

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
	    List<Map<String, Object>> courseList = trainingRegistrationService.getTrainingStatusList();
	    model.addAttribute("courseList", courseList);
	    return "trainingregistration/history";
	}


}