package com.erp.biztrack.training.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.training.model.service.TrainingService;
import com.erp.biztrack.trainingregistration.model.dto.TrainingRegistration;

import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;

@Controller

public class TrainingController {

	private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

	@Autowired
	private TrainingService trainingService;

	/*
	 * // 교육 등록 페이지로 이동
	 * 
	 * @RequestMapping("/training/register.do") public String registerPage() {
	 * return "training/register"; }
	 */

	// 교육 등록
	@RequestMapping(value = "/training/insert.do", method = RequestMethod.POST)
	public String insertTraining(Training training, Model model) {
		logger.info("insert.do : " + training);

		// ID 생성
		training.setTrainingId(generateTrainingId());

		int result = trainingService.insertTraining(training);

		if (result > 0) {
			// 새 교육 등록 성공시, 교육 목록 페이지로 이동 처리
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
	@RequestMapping("/training/detail.do")
	public ModelAndView trainingDetailMethod(@RequestParam("id") String trainingId, ModelAndView mv) {
		logger.info("detail.do : " + trainingId);

		System.out.println("받은 ID: " + trainingId);

		Training training = trainingService.selectTraining(trainingId); // 상세 조회

		int enrollmentCount = trainingService.getEnrollmentCount(trainingId); // 수강 인원
		boolean isFull = enrollmentCount >= training.getCapacity(); // 정원 확인

		if (training != null) {
			mv.addObject("training", training);
			mv.setViewName("training/detail");
			mv.addObject("isFull", isFull);
		} else {
			mv.addObject("message", trainingId + "번 교육 상세 조회 실패!");
			mv.setViewName("common/error");
		}
		return mv;
	}

	/*
	 * @RequestMapping("training/detail.do") public ModelAndView
	 * trainingDetailMethod(@RequestParam("id") String trainingId, ModelAndView mv)
	 * { logger.info("detail.do : " + trainingId);
	 * 
	 * System.out.println("받은 ID: " + trainingId);
	 * 
	 * Training training = trainingService.selectTraining(trainingId);
	 * 
	 * if (training != null) { mv.addObject("training", training);
	 * mv.setViewName("training/detail"); } else { mv.addObject("message",
	 * trainingId + "번 교육 상세 조회 실패!"); mv.setViewName("common/error"); }
	 * 
	 * return mv; }
	 */

// 수강신청 목록 조회
	@RequestMapping("training/registrationView.do")
	public ModelAndView trainingRegistrationView(@RequestParam("id") String trainingId, ModelAndView mv) {
		Training training = trainingService.selectTraining(trainingId); // 동일 서비스 호출
		if (training != null) {
			mv.addObject("training", training);
			mv.setViewName("training/registrationView");
		} else {
			mv.addObject("message", trainingId + "번 교육 정보를 불러올 수 없습니다.");
			mv.setViewName("common/error");
		}
		return mv;
	}
	
	@RequestMapping(value = "/training/register.do", method = RequestMethod.GET)
	public String registerPage() {
	    return "training/register"; // JSP 페이지
	}


	// 수강신청 등록
	// 수강신청 등록 (Controller에서 로직 처리 중심)

	@RequestMapping(value = "/training/register.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerTraining(@RequestBody Map<String, Object> data, HttpSession session) {

	    Map<String, Object> result = new HashMap<>();
	    LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");

	    if (loginInfo == null) {
	        result.put("status", "fail");
	        result.put("message", "로그인이 필요합니다.");
	        return result;
	    }

	    String trainingId = (String) data.get("trainingId");
	    if (trainingId == null || trainingId.trim().isEmpty()) {
	        result.put("status", "fail");
	        result.put("message", "교육 ID가 누락되었습니다.");
	        return result;
	    }

	    int currentEnrollment = trainingService.getCurrentEnrollment(trainingId);
	    int capacity = trainingService.getTrainingCapacity(trainingId);
	    if (currentEnrollment >= capacity) {
	        result.put("status", "fail");
	        result.put("message", "정원이 초과되어 수강신청이 불가합니다.");
	        return result;
	    }

	    Date startDate = trainingService.getTrainingStartDate(trainingId);
	    Date endDate = trainingService.getTrainingEndDate(trainingId);
	    Date today = new Date(System.currentTimeMillis());

	    if (today.before(startDate) || today.after(endDate)) {
	        result.put("status", "fail");
	        result.put("message", "신청 가능한 기간이 아닙니다.");
	        return result;
	    }

	    String registrationId = loginInfo.getEmpId();
	    data.put("registrationId", registrationId);
	    data.put("isCancelled", "N");
	    data.put("registrationAt", new Timestamp(System.currentTimeMillis())); // 이건 여전히 Timestamp 유지

	    try {
	        int insertResult = trainingService.insertTrainingRegistration(data);

	        if (insertResult > 0) {
	            result.put("status", "success");
	        } else {
	            result.put("status", "fail");
	            result.put("message", "수강신청에 실패했습니다.");
	        }

	    } catch (Exception e) {
	        String errorMsg = e.getMessage();
	        if (errorMsg != null && errorMsg.contains("ORA-00001")) {
	            result.put("status", "fail");
	            result.put("message", "이미 신청하신 교육입니다.");
	        } else {
	            result.put("status", "fail");
	            result.put("message", "처리 중 오류가 발생했습니다.");
	        }
	        e.printStackTrace();
	    }

	    return result;
	}

	/*
	 * @RequestMapping(value = "/training/register.do", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Map<String, Object> registerTraining(@RequestBody
	 * Map<String, Object> data, HttpSession session) {
	 * 
	 * Map<String, Object> result = new HashMap<>(); LoginDto loginInfo = (LoginDto)
	 * session.getAttribute("loginInfo");
	 * 
	 * // 1. 로그인 여부 확인 if (loginInfo == null) { result.put("status", "fail");
	 * result.put("message", "로그인이 필요합니다."); return result; }
	 * 
	 * // 2. 필수 파라미터 검증 String trainingId = (String) data.get("trainingId"); if
	 * (trainingId == null || trainingId.trim().isEmpty()) { result.put("status",
	 * "fail"); result.put("message", "교육 ID가 누락되었습니다."); return result; }
	 * 
	 * // 3. 파라미터 조립 String registrationId = loginInfo.getEmpId();
	 * data.put("registrationId", registrationId); data.put("isCancelled", "N");
	 * data.put("registrationAt", new
	 * java.sql.Timestamp(System.currentTimeMillis()));
	 * 
	 * try { int insertResult = trainingService.insertTrainingRegistration(data);
	 * 
	 * if (insertResult > 0) { result.put("status", "success"); } else {
	 * result.put("status", "fail"); result.put("message", "수강신청에 실패했습니다."); }
	 * 
	 * } catch (Exception e) { String errorMsg = e.getMessage();
	 * 
	 * // ORA-00001 = 중복 신청 (무결성 제약조건 위반) if (errorMsg != null &&
	 * errorMsg.contains("ORA-00001")) { result.put("status", "fail");
	 * result.put("message", "이미 신청하신 교육입니다."); } else { result.put("status",
	 * "fail"); result.put("message", "처리 중 오류가 발생했습니다."); }
	 * 
	 * // Optional: 개발 중이라면 로그에 원본 에러 남기기 e.printStackTrace(); }
	 * 
	 * return result; }
	 */


	/*
	 * @RequestMapping(value = "/training/register.do", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Map<String, Object> registerTraining(@RequestBody
	 * Map<String, Object> data, HttpSession session) { LoginDto loginInfo =
	 * (LoginDto) session.getAttribute("loginInfo");
	 * 
	 * 
	 * Map<String, Object> result = new HashMap<>();
	 * 
	 * if (loginInfo == null) { result.put("status", "fail"); result.put("message",
	 * "로그인이 필요합니다."); return result; }
	 * 
	 * 
	 * data.put("registrationId", loginInfo.getEmpId());
	 * 
	 * try { trainingService.insertTrainingRegistration(data); result.put("status",
	 * "success"); } catch (RuntimeException e) { result.put("status", "fail");
	 * result.put("message", e.getMessage()); } catch (Exception e) {
	 * result.put("status", "fail"); result.put("message", "DB 오류: " +
	 * e.getMessage()); }
	 * 
	 * return result; }
	 */


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
	public ModelAndView trainingSearchAllMethod(ModelAndView mv, @RequestParam("action") String action,
			@RequestParam("keyword") String keyword, @RequestParam(name = "page", required = false) String page,
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

	// 수강신청 내역 페이지 
	@RequestMapping("/training/applicant.do")
	public ModelAndView viewMyTrainingList(HttpSession session) {
	    LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
	    if (loginInfo == null) return new ModelAndView("redirect:/login.do");

	    List<Training> trainingList = trainingService.getTrainingListByEmpId(loginInfo.getEmpId());
	    ModelAndView mv = new ModelAndView("training/applicant");
	    mv.addObject("trainingList", trainingList);
	    return mv;
	}

	/*
	 * @RequestMapping("/training/applicant.do") public ModelAndView
	 * viewTrainingApplicant(
	 * 
	 * @RequestParam(value = "trainingId", required = false) String trainingId,
	 * HttpSession session ) { ModelAndView mv = new ModelAndView(); LoginDto
	 * loginInfo = (LoginDto) session.getAttribute("loginInfo");
	 * 
	 * if (loginInfo == null) { mv.setViewName("redirect:/login.do"); return mv; }
	 * 
	 * String empId = loginInfo.getEmpId(); // 사원번호 기준 List<Training> list;
	 * 
	 * if (trainingId != null && !trainingId.isEmpty()) { Training training =
	 * trainingService.selectTraining(trainingId); if (training == null) {
	 * mv.setViewName("common/error"); mv.addObject("msg", "해당 교육 정보를 불러올 수 없습니다.");
	 * return mv; } list = List.of(training); } else { list =
	 * trainingService.getTrainingListByEmpId(empId); }
	 * 
	 * mv.addObject("trainingList", list); mv.setViewName("training/applicant");
	 * return mv; }
	 */



	
	@RequestMapping("/training/myList.do")
	public String viewMyTrainingList(HttpSession session, Model model) {
		String loginEmail = (String) session.getAttribute("loginEmail");
		if (loginEmail == null) {
			return "redirect:/login.do";
		}

		List<Training> myList = trainingService.getTrainingsByEmail(loginEmail);
		model.addAttribute("trainingList", myList);
		return "training/applicant";
	}

}
