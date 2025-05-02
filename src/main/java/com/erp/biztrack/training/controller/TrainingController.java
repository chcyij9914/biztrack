package com.erp.biztrack.training.controller;

import java.util.ArrayList;

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

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.training.model.service.TrainingService;

@Controller

public class TrainingController {

	private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

	@Autowired
	private TrainingService trainingService;

	// 교육 등록 페이지로 이동
	@RequestMapping("/training/register.do")
	public String registerPage() {
	    return "training/register";  
	}
	
	// 교육 등록 
	@RequestMapping(value = "/training/insert.do", method = RequestMethod.POST)
	public String insertTraining(
			  Training training, Model model) {
		logger.info("insert.do : " + training);
	    
	    //  ID 생성
	    training.setTrainingId(generateTrainingId());
	    
	    int result = trainingService.insertTraining(training);
	    
	    if (result > 0) {
	    	//새 교육 등록 성공시, 교육 목록 페이지로 이동 처리
	        return "redirect:/list.do";  
	    } else {
	        model.addAttribute("message", " 새 교육 등록 실패!");
	        return "common/error";
	    }
	}
	private String generateTrainingId() {
		int random = (int) (Math.random() * 1000);
		return "TR" + String.format("%03d", random);
	}

	// 교육 목록
	@RequestMapping("list.do")
	public ModelAndView trainingList(ModelAndView mv,

			@RequestParam(name = "page", required = false) String page,

			@RequestParam(name = "limit", required = false) String slimit) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;

		int listCount = trainingService.selectListCount();
		Paging paging = new Paging(listCount, limit, currentPage, "list.do");
		paging.calculate();

		ArrayList<Training> list = trainingService.selectAll(paging);

		if (list != null && list.size() > 0) {
			mv.addObject("trainingList", list);
			mv.addObject("paging", paging);
			mv.setViewName("training/list");
		} else {
			mv.addObject("message", currentPage + "페이지에 출력할 교육 목록 조회 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 교육 상세보기 페이지로 이동
	@RequestMapping("training/detail.do")
	public ModelAndView trainingDetailMethod(@RequestParam("id") String trainingId, ModelAndView mv) {
		logger.info("detail.do : " + trainingId);

		System.out.println("받은 ID: " + trainingId);

		Training training = trainingService.selectTraining(trainingId);

		if (training != null) {
			mv.addObject("training", training);
			mv.setViewName("training/detail");
		} else {
			mv.addObject("message", trainingId + "번 교육 상세 조회 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 교육 삭제
	@RequestMapping(value = "/training/delete.do", method = RequestMethod.POST)
	public String deleteTraining(@RequestParam("trainingId") String trainingId, Model model) {
		logger.info("deleteSelected.do : " + trainingId);

	    int result = trainingService.deleteTraining(trainingId);

	    if (result > 0) {
	        // 삭제 성공 시 목록 페이지로 이동
	        return "redirect:/list.do";
	    } else {
	        model.addAttribute("message", "교육 삭제 실패!");
	        return "common/error";
	    }
	}
	
	// 교육 수정 
	@RequestMapping(value = "training/update.do", method = RequestMethod.POST)
	public String updateTraining(@ModelAttribute Training training, Model model) {
	    logger.info("update.do : " + training);

	    int result = trainingService.updateTraining(training); // 전체 Training 객체로 수정

	    if (result > 0) {
	        return "redirect:/list.do"; // 수정 성공 시 목록 이동
	    } else {
	        model.addAttribute("message", "교육 수정 실패!");
	        return "common/error";
	    }
	}

	// 교육 평가 분석 페이지로 이동
	@RequestMapping("training/analysis.do")
	public String AnalysisPage(Model model) {
	    // 분석 데이터 로딩 로직 작성
	    return "training/analysis";
	}

	// 교육 검색 페이지
	@RequestMapping("training/searchAll.do")
	public ModelAndView trainingSearchAllMethod(
			ModelAndView mv, 
			@RequestParam("action") String action,
			@RequestParam("keyword") String keyword,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit) {
		logger.info("searchAll.do : " + keyword);
		logger.info("searchAll.do : " + action);

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;

		int listCount = 0;
		switch (action.trim()) {
		case "title":
			listCount = trainingService.selectSearchTitleCount(keyword);
			break;
		case "Instructor":
			listCount = trainingService.selectSearchInstructorCount(keyword);
			break;
		case "content":
			listCount = trainingService.selectSearchcourseContentCount(keyword);
			break;
		}
		Paging paging = new Paging(listCount, limit, currentPage, "training/searchAll.do");
		paging.calculate();

		Search search = new Search();
		search.setKeyword(keyword);
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Training> list = null;
		switch (action.trim()) {
		case "title":
			list = trainingService.selectSearchTitle(search);
			break;
		case "Instructor":
			list = trainingService.selectSearchInstructor(search);
			break;
		case "content":
			list = trainingService.selectSearchcourseContent(search);
			break;
		}
		logger.info("searchAll.do : " + listCount);
		logger.info("searchAll.do : " + list);

		if (list != null && list.size() >= 0) {
			mv.addObject("trainingList", list);
			mv.addObject("paging", paging);
			mv.addObject("action", action);
			mv.addObject("keyword", keyword);
			mv.setViewName("training/list");
		} else { // 조회 실패시
			mv.addObject("message", currentPage + "페이지에 출력할 공지글 목록 조회 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}
}
