package com.erp.biztrack.training.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.training.model.dto.Training;
import com.erp.biztrack.training.model.service.TrainingService;

import jakarta.servlet.http.HttpSession;

@Controller

public class TrainingController {

    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    private TrainingService trainingService;
    

 // 교육 등록 페이지로 이동
    @RequestMapping(value = "/training/register.do", method = RequestMethod.GET)
    public String trainingRegisterPage() {
        return "training/trainingRegister";
    }

    // 교육 리스트 AJAX 조회
    @RequestMapping(value = "list.ajax", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object>ListAjax() {
        logger.info("list.ajax run...");
        ArrayList<Training> list = trainingService.selectAll();
        Map<String, Object> resultList = new HashMap<>();
        resultList.put("list", list);
        return resultList;
    }

	
	  // 교육 목록 
	  @RequestMapping("list.do") 
	  public ModelAndView trainingList(ModelAndView mv,
	  
	  @RequestParam(name = "page", required = false) String page,
	  
	  @RequestParam(name = "limit", required = false) String slimit) {
	  
	  int currentPage = (page != null) ? Integer.parseInt(page) : 1; int limit =
	  (slimit != null) ? Integer.parseInt(slimit) : 10;
	  
	  int listCount = trainingService.selectListCount(); Paging paging = new
	  Paging(listCount, limit, currentPage, "list.do"); 
	  paging.calculate();
	  
	  ArrayList<Training> list = trainingService.selectList(paging);
	  
	  if (list != null && list.size() > 0) 
	  { mv.addObject("trainingList", list);
	  mv.addObject("paging", paging); 
	  mv.setViewName("training/list"); 
	  } else { mv.addObject("message", currentPage + "페이지에 출력할 교육 목록 조회 실패!");
	  mv.setViewName("common/error"); }
	  
	  return mv; 
	  }
	 

    // 교육 상세보기 페이지로 이동
	  @GetMapping("detail.do")
    public ModelAndView trainingDetailMethod(@RequestParam("id") int trainingId,
                                             ModelAndView mv,
                                             HttpSession session) {
        logger.info("detail.do : " + trainingId);

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
    
    // 교육 분석 페이지로 이동
	  @GetMapping("/analysis.do")
    public String analysisPage() {
        return "training/analysis";
    }

	/*
	 * @RequestMapping(value = "traininginsert.do", method = RequestMethod.POST)
	 * public String trainingInsertMethod(Training training,
	 * 
	 * @RequestParam(name = "ofile", required = false) MultipartFile mfile,
	 * HttpServletRequest request, Model model) {
	 * 
	 * logger.info("traininginsert.do : " + training);
	 * 
	 * // 생략된 파일 저장 로직 (필요시 복구 가능)
	 * 
	 * if (trainingService.insertTraining(training) > 0) { return
	 * "redirect:traininglist.do"; } else { model.addAttribute("message",
	 * "새 교육 등록 실패!"); return "common/error"; } }
	 * 
	 * @RequestMapping("trainingSearchTitle.do") public ModelAndView
	 * trainingSearchTitleMethod(ModelAndView mv,
	 * 
	 * @RequestParam("action") String action,
	 * 
	 * @RequestParam("keyword") String keyword,
	 * 
	 * @RequestParam(name = "page", required = false) String page,
	 * 
	 * @RequestParam(name = "limit", required = false) String slimit) {
	 * 
	 * int currentPage = (page != null) ? Integer.parseInt(page) : 1; int limit =
	 * (slimit != null) ? Integer.parseInt(slimit) : 10;
	 * 
	 * int listCount = trainingService.selectSearchTitleCount(keyword); Paging
	 * paging = new Paging(listCount, limit, currentPage, "trainingSearchTitle.do");
	 * paging.calculate();
	 * 
	 * Search search = new Search(); search.setKeyword(keyword);
	 * search.setStartRow(paging.getStartRow());
	 * search.setEndRow(paging.getEndRow());
	 * 
	 * ArrayList<Training> list = trainingService.selectSearchTitle(search);
	 * 
	 * if (list != null && list.size() > 0) { mv.addObject("list", list);
	 * mv.addObject("paging", paging); mv.addObject("action", action);
	 * mv.addObject("keyword", keyword);
	 * mv.setViewName("training/trainingListView"); } else { mv.addObject("message",
	 * action + "에 대한 " + keyword + " 검색 결과가 존재하지 않습니다.");
	 * mv.setViewName("common/error"); }
	 * 
	 * return mv; }
	 * 
	 * @RequestMapping("trainingSearchContent.do") public ModelAndView
	 * trainingSearchContentMethod(ModelAndView mv,
	 * 
	 * @RequestParam("action") String action,
	 * 
	 * @RequestParam("keyword") String keyword,
	 * 
	 * @RequestParam(name = "page", required = false) String page,
	 * 
	 * @RequestParam(name = "limit", required = false) String slimit) {
	 * 
	 * int currentPage = (page != null) ? Integer.parseInt(page) : 1; int limit =
	 * (slimit != null) ? Integer.parseInt(slimit) : 10;
	 * 
	 * int listCount = trainingService.selectSearchContentCount(keyword); Paging
	 * paging = new Paging(listCount, limit, currentPage,
	 * "trainingSearchContent.do"); paging.calculate();
	 * 
	 * Search search = new Search(); search.setKeyword(keyword);
	 * search.setStartRow(paging.getStartRow());
	 * search.setEndRow(paging.getEndRow());
	 * 
	 * ArrayList<Training> list = trainingService.selectSearchContent(search);
	 * 
	 * if (list != null && list.size() > 0) { mv.addObject("list", list);
	 * mv.addObject("paging", paging); mv.addObject("action", action);
	 * mv.addObject("keyword", keyword);
	 * mv.setViewName("training/trainingListView"); } else { mv.addObject("message",
	 * action + "에 대한 " + keyword + " 검색 결과가 존재하지 않습니다.");
	 * mv.setViewName("common/error"); }
	 * 
	 * return mv; }
	 * 
	 * @RequestMapping("trainingSearchDate.do") public ModelAndView
	 * trainingSearchDateMethod(ModelAndView mv, Search search,
	 * 
	 * @RequestParam("action") String action,
	 * 
	 * @RequestParam(name = "page", required = false) String page,
	 * 
	 * @RequestParam(name = "limit", required = false) String slimit) {
	 * 
	 * int currentPage = (page != null) ? Integer.parseInt(page) : 1; int limit =
	 * (slimit != null) ? Integer.parseInt(slimit) : 10;
	 * 
	 * int listCount = trainingService.selectSearchDateCount(search); Paging paging
	 * = new Paging(listCount, limit, currentPage, "trainingSearchDate.do");
	 * paging.calculate();
	 * 
	 * search.setStartRow(paging.getStartRow());
	 * search.setEndRow(paging.getEndRow());
	 * 
	 * 
	 * ArrayList<Training> list = trainingService.selectSearchDate(search);
	 * 
	 * if (list != null && list.size() > 0) { mv.addObject("list", list);
	 * mv.addObject("paging", paging); mv.addObject("action", action);
	 * mv.addObject("begin", search.getBegin()); mv.addObject("end",
	 * search.getEnd()); mv.setViewName("training/trainingListView"); } else {
	 * mv.addObject("message", action + "에 대한 " + search.getBegin() + "부터 " +
	 * search.getEnd() + "까지 등록된 교육 검색 결과가 존재하지 않습니다.");
	 * mv.setViewName("common/error"); }
	 * 
	 * return mv; }
	 */
}
