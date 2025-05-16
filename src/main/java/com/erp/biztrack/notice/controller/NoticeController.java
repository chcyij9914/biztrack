package com.erp.biztrack.notice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.notice.model.dto.Notice;
import com.erp.biztrack.notice.model.service.NoticeService;
import com.erp.biztrack.training.model.dto.Training;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;

	// 뷰 페이지 내보내기용 메소드 -----------------------------------------------------

	// 새 공지글 등록 페이지로 이동 처리용
	@RequestMapping("moveWrite.do")
	public String moveWritePage() {
		return "notice/noticeWriteForm";
	}

	// 공지글 수정페이지로 이동 처리용
	@RequestMapping("nmoveup.do")
	public ModelAndView moveUpdatePage(@RequestParam("no") int noticeNo, ModelAndView mv) {
		// 수정페이지로 수정할 공지글도 함께 내보내기 위해, 전송받은 공지번호에 대한 공지글 조회해 옴
		Notice notice = noticeService.selectNotice(noticeNo);

		if (notice != null) {
			mv.addObject("notice", notice);
			mv.setViewName("notice/noticeUpdateView");
		} else {
			mv.addObject("message", noticeNo + "번 공지글 수정페이지로 이동 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 요청 처리하는 메소드 (db 까지 연결되는 요청) -----------------------------------

	/*
	 * produces 속성 : 클라이언트에게 어떤 타입의 데이터를 응답할 것인지를 지정함 HTTP 응답(Response 객체)의
	 * Content-Type 해더를 설정한 것임 브라우저가 이 설정된 정보를 보고 응답 데이터를 올바르게 해석하게 됨
	 * application/json; 응답 데이터가 JSON 형식이라는 의미임 charset=UTF-8 응답 데이터가 UTF-8 문자 인코딩으로
	 * 인코딩되어 있다는 의미임, 한글 깨짐 방지에 아주 중요함
	 */
	@RequestMapping(value = "ntop3.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody // ajax 통신에서 사용함, 뷰리졸버로 리턴하지 않고 별도의 출력스트림을 만들어서 클라이언트로 바로 보냄
	public Map<String, Object> noticeNewTop3Method() { // Jackson 라이브러리 사용시 리턴방식
		// public String noticeNewTop3Method() { //JSONObject 리턴시 사용하는 리턴방식
		logger.info("ntop3.do run...");

		// 서비스 모델로 top3 결과 요청
		ArrayList<Notice> list = noticeService.selectTop3();
		logger.info("ntop3 list : " + list);

		// json 으로 보낼 객체 구조를 Map 으로 생성
		Map<String, Object> resultTop3 = new HashMap<>();
		resultTop3.put("notice", list);

		return resultTop3; // Jackson 이 자동으로 JSON 으로 변환
	}

	// 공지사항 전체 목록보기 요청 처리용 (페이징 처리 : 한 페이지에 10개씩 출력 처리)
	
	@RequestMapping("nlist.do")
	public ModelAndView noticeListMethod(ModelAndView mv,
	                                     @RequestParam(name = "page", required = false) String page,
	                                     @RequestParam(name = "limit", required = false) String slimit) {
	    int currentPage = 1;
	    if (page != null) {
	        currentPage = Integer.parseInt(page);
	    }

	    int limit = 10;
	    if (slimit != null) {
	        limit = Integer.parseInt(slimit);
	    }

	    // 중요공지 리스트
	    ArrayList<Notice> importantList = noticeService.selectImportantList();  // ★ importance = 'Y'

	    // 전체 공지 수 (중요공지 포함 전체 count)
	    int listCount = noticeService.selectListCount();

	    // 페이징 설정
	    Paging paging = new Paging(listCount, limit, currentPage, "nlist.do");
	    paging.calculate();

	    // 일반공지 리스트
	    ArrayList<Notice> generalList = noticeService.selectList(paging);  // ★ importance != 'Y'

	    // 통합 리스트 생성 (중요공지 먼저 추가)
	    ArrayList<Notice> finalList = new ArrayList<>();
	    
	    if (importantList != null && !importantList.isEmpty()) {
	        for (Notice n : importantList) {
	            n.setNoticeNo(-1); // 번호 대신 '중요' 표시용
	            finalList.add(n);
	        }
	    }

	    if (generalList != null && !generalList.isEmpty()) {
	        finalList.addAll(generalList);
	    }

	    // 뷰에 전달
	    if (!finalList.isEmpty()) {
	        mv.addObject("noticeList", finalList); // 🔑 중요: notice → noticeList 로 전달
	        mv.addObject("paging", paging);
	        mv.setViewName("notice/noticeListView");
	    } else {
	        mv.addObject("message", currentPage + "페이지에 출력할 공지글 목록 조회 실패!");
	        mv.setViewName("common/error");
	    }

	    return mv;
	}


	// 공지글 상세보기 요청 처리용

	 @RequestMapping("detail.do")
	    public ModelAndView noticeDetailMethod(
	            @RequestParam("no") int noticeNo,
	            ModelAndView mv,
	            HttpSession session) {

	        // 1) 파라미터 & 로깅
	        logger.info("detail.do : {}", noticeNo);

	        // 2) 공지 조회 + 조회수 증가
	        Notice notice = noticeService.selectNotice(noticeNo);
	        noticeService.updateAddReadCount(noticeNo);

	        if (notice != null) {
	            mv.addObject("notice", notice);
	            mv.setViewName("notice/noticedetail");

				/*
				 * // 3) 세션에서 로그인 사용자 꺼내오기 Member loginUser = (Member)
				 * session.getAttribute("loginUser"); String adminYn = (loginUser != null ?
				 * loginUser.adminYn() : "N");
				 * 
				 * // 4) 관리자 여부에 따라 다른 뷰로 포워드 if ("Y".equals(adminYn)) {
				 * mv.setViewName("notice/noticeAdminDetailView"); } else {
				 * mv.setViewName("notice/noticeDetailView"); }
				 */
	        } else {
	            mv.addObject("message",
	                noticeNo + "번 공지글 상세보기 요청 실패! 관리자에게 문의하세요.");
	            mv.setViewName("common/error");
	        }

	        return mv;
	        
	        
	    }
	 

		// 새 공지글 등록 요청 처리용 (파일 업로드 기능 포함)
		@RequestMapping(value="ninsert.do", method=RequestMethod.POST)
		public String noticeInsertMethod(
				Notice notice, 
				@RequestParam(name="ofile", required=false) MultipartFile mfile,
				HttpServletRequest request,
				Model model) {
			logger.info("ninsert.do : " + notice);
			if(noticeService.insertNotice(notice) > 0) {
				//새 공지글 등록 성공시, 공지 목록 페이지로 이동 처리
				return "redirect:nlist.do";
			} else {
				model.addAttribute("message", "새 공지글 등록 실패!");
				return "common/error";
			}
			
		}  // ninsert.do closed


	 

	// dml ****************************************************

	// 공지글 삭제 요청 처리용
	@RequestMapping("notice/delete.do")
	public String noticedeleteMethod(Model model, 
			@RequestParam("no") int noticeNo, RedirectAttributes ra,
			 HttpServletRequest request) {
		
		int result = noticeService.deleteNotice(noticeNo);
		
		 if (result > 0) {
			 ra.addFlashAttribute("msg", "공지사항이 삭제되었습니다.");
			 
			  return "redirect:/nlist.do";
	        } else {
	            ra.addFlashAttribute("msg", "삭제에 실패했습니다.");
	            // 실패 시 상세 페이지로 되돌아가기
	            return "redirect:/notice/detail.do?no=" + noticeNo;
	        }
	           
		        // 삭제 성공 시 목록 페이지로 이동
		
		/*
		 * if (noticeService.deleteNotice(noticeNo) > 0) { // 공지글 삭제 성공시 // 공지글 삭제 성공시
		 * 저장 폴더에 있는 첨부파일도 삭제 처리함 if (renameFileName != null && renameFileName.length()
		 * > 0) { // 공지사항 첨부파일 저장 폴더 경로 지정 String savePath =
		 * request.getSession().getServletContext().getRealPath(
		 * "resources/notice_upfiles"); // 저장 폴더에서 파일 삭제함 new File(savePath + "\\" +
		 * renameFileName).delete(); }
		 */
		/*
		 * return "redirect:nlist.do"; } else { // 공지글 삭제 실패시
		 * model.addAttribute("message", noticeNo + "번 공지글 삭제 실패!"); return
		 * "common/error"; }
		 */
	}
	
	@RequestMapping(value = "notice/update.do", method = RequestMethod.POST)
	 public String updateNotice(@ModelAttribute Notice notice, Model model) {
        logger.info("notice/update.do : {}", notice);

        int result = noticeService.updateNotice(notice);
        if (result > 0) {
            // 수정 성공 시 공지사항 목록으로 리다이렉트
            return "redirect:/nlist.do";
        } else {
            model.addAttribute("message", "공지사항 수정 실패!");
            return "common/error";
        }
    }

	// 공지글 검색 관련 **********************************************

	// 공지사항 제목 검색 목록보기 요청 처리용 (페이징 처리 : 한 페이지에 10개씩 출력 처리)
	@RequestMapping("nsearchTitle.do")
	public ModelAndView noticeSearchTitleMethod(ModelAndView mv, @RequestParam("action") String action,
			@RequestParam("keyword") String keyword, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit) {
		// 페이징 처리
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지에 출력할 목록 갯수 기본 10개로 지정함
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		// 검색결과가 적용된 총 목록 갯수 조회해서, 총 페이지 수 계산함
		int listCount = noticeService.selectSearchTitleCount(keyword);
		// 페이지 관련 항목들 계산 처리
		Paging paging = new Paging(listCount, limit, currentPage, "nsearchTitle.do");
		paging.calculate();

		// 마이바티스 매퍼에서 사용되는 메소드는 Object 1개만 전달할 수 있음
		// paging.startRow, paging.endRow, keyword 같이 전달해야 하므로 => 객체 하나를 만들어서 저장해서 보냄
		Search search = new Search();
		search.setKeyword(keyword);
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		// 서비스 모델로 페이징 적용된 목록 조회 요청하고 결과받기
		ArrayList<Notice> list = noticeService.selectSearchTitle(search);

		if (list != null && list.size() > 0) { // 조회 성공시
			// ModelAndView : Model + View
			mv.addObject("noticeList", list); // request.setAttribute("list", list) 와 같음
			mv.addObject("paging", paging);
			mv.addObject("action", action);
			mv.addObject("keyword", keyword);

			mv.setViewName("notice/noticeListView");
		} else { // 조회 실패시
			mv.addObject("message", action + "에 대한 " + keyword + " 검색 결과가 존재하지 않습니다.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 공지사항 내용 검색 목록보기 요청 처리용 (페이징 처리 : 한 페이지에 10개씩 출력 처리)
	@RequestMapping("nsearchContent.do")
	public ModelAndView noticeSearchContentMethod(ModelAndView mv, @RequestParam("action") String action,
			@RequestParam("keyword") String keyword, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit) {
		// 페이징 처리
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지에 출력할 목록 갯수 기본 10개로 지정함
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		// 검색결과가 적용된 총 목록 갯수 조회해서, 총 페이지 수 계산함
		int listCount = noticeService.selectSearchContentCount(keyword);
		// 페이지 관련 항목들 계산 처리
		Paging paging = new Paging(listCount, limit, currentPage, "nsearchContent.do");
		paging.calculate();

		// 마이바티스 매퍼에서 사용되는 메소드는 Object 1개만 전달할 수 있음
		// paging.startRow, paging.endRow, keyword 같이 전달해야 하므로 => 객체 하나를 만들어서 저장해서 보냄
		Search search = new Search();
		search.setKeyword(keyword);
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		// 서비스 모델로 페이징 적용된 목록 조회 요청하고 결과받기
		ArrayList<Notice> list = noticeService.selectSearchContent(search);

		if (list != null && list.size() > 0) { // 조회 성공시
			// ModelAndView : Model + View
			mv.addObject("noticeList", list); // request.setAttribute("list", list) 와 같음
			mv.addObject("paging", paging);
			mv.addObject("action", action);
			mv.addObject("keyword", keyword);

			mv.setViewName("notice/noticeListView");
		} else { // 조회 실패시
			mv.addObject("message", action + "에 대한 " + keyword + " 검색 결과가 존재하지 않습니다.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 공지사항 작성자 목록보기 요청 처리용 (페이징 처리 : 한 페이지에 10개씩 출력 처리)
	@RequestMapping("nsearchWriter.do")
	public ModelAndView noticeSearchWriterMethod(ModelAndView mv, @RequestParam("action") String action,
			@RequestParam("keyword") String keyword, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit) {
		// 페이징 처리
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지에 출력할 목록 갯수 기본 10개로 지정함
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		// 검색결과가 적용된 총 목록 갯수 조회해서, 총 페이지 수 계산함
		int listCount = noticeService.selectSearchContentCount(keyword);
		// 페이지 관련 항목들 계산 처리
		Paging paging = new Paging(listCount, limit, currentPage, "nsearchWriter.do");
		paging.calculate();

		// 마이바티스 매퍼에서 사용되는 메소드는 Object 1개만 전달할 수 있음
		// paging.startRow, paging.endRow, keyword 같이 전달해야 하므로 => 객체 하나를 만들어서 저장해서 보냄
		Search search = new Search();
		search.setKeyword(keyword);
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		// 서비스 모델로 페이징 적용된 목록 조회 요청하고 결과받기
		ArrayList<Notice> list = noticeService.selectSearchWriter(search);

		if (list != null && list.size() > 0) { // 조회 성공시
			// ModelAndView : Model + View
			mv.addObject("noticeList", list); // request.setAttribute("list", list) 와 같음
			mv.addObject("paging", paging);
			mv.addObject("action", action);
			mv.addObject("keyword", keyword);

			mv.setViewName("notice/noticeListView");
		} else { // 조회 실패시
			mv.addObject("message", action + "에 대한 " + keyword + " 검색 결과가 존재하지 않습니다.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 공지사항 등록날짜 검색 목록보기 요청 처리용 (페이징 처리 : 한 페이지에 10개씩 출력 처리)
	@RequestMapping("nsearchDate.do")
	public ModelAndView noticeSearchDateMethod(ModelAndView mv, Search search, @RequestParam("action") String action,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit) {
		// 페이징 처리
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지에 출력할 목록 갯수 기본 10개로 지정함
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		// 검색결과가 적용된 총 목록 갯수 조회해서, 총 페이지 수 계산함
		int listCount = noticeService.selectSearchDateCount(search);
		// 페이지 관련 항목들 계산 처리
		Paging paging = new Paging(listCount, limit, currentPage, "nsearchDate.do");
		paging.calculate();

		// 마이바티스 매퍼에서 사용되는 메소드는 Object 1개만 전달할 수 있음
		// paging.startRow, paging.endRow, begin, end 같이 전달해야 하므로 => 객체 하나를 만들어서 저장해서 보냄
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		// 서비스 모델로 페이징 적용된 목록 조회 요청하고 결과받기
		ArrayList<Notice> list = noticeService.selectSearchDate(search);

		if (list != null && list.size() > 0) { // 조회 성공시
			// ModelAndView : Model + View
			mv.addObject("noticeList", list); // request.setAttribute("list", list) 와 같음
			mv.addObject("paging", paging);
			mv.addObject("action", action);
			mv.addObject("begin", search.getBegin());
			mv.addObject("end", search.getEnd());

			mv.setViewName("notice/noticeListView");
		} else { // 조회 실패시
			mv.addObject("message", action + "에 대한 " + search.getBegin() + "부터 " + search.getEnd()
					+ "기간 사이에 등록한 공지글 검색 결과가 존재하지 않습니다.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	
	//메인페이지 최근공지 5개!!-------------------------------------------------------------------------
	@RequestMapping("nlist5.do")
	public ModelAndView noticeListMethod5(ModelAndView mv,
	                                     @RequestParam(name = "page", required = false) String page,
	                                     @RequestParam(name = "limit", required = false) String slimit) {
	    int currentPage = 1;
	    if (page != null) {
	        currentPage = Integer.parseInt(page);
	    }

	    int limit = 5;
	    if (slimit != null) {
	        limit = Integer.parseInt(slimit);
	    }

	    // 전체 공지 수 (중요공지 포함 전체 count)
	    int listCount = noticeService.selectListCount();

	    // 페이징 설정
	    Paging paging = new Paging(listCount, limit, currentPage, "nlist.do");
	    paging.calculate();

	    // 공지 리스트
	    ArrayList<Notice> List = noticeService.selectList5(paging);  

	    // 뷰에 전달
	    if (!List.isEmpty()) {
	        mv.addObject("noticeList", List); 
	        mv.addObject("paging", paging);
	        mv.setViewName("common/notice-fragment"); 
	    } else {
	        mv.addObject("message", currentPage + "페이지에 출력할 공지글 목록 조회 실패!");
	        mv.setViewName("common/error");
	    }

	    return mv;
	}
}
