package com.erp.biztrack.purchase.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.purchase.model.dto.Purchase;
import com.erp.biztrack.purchase.model.service.PurchaseService;

@Controller
public class PurchaseController {
	// 이 클래스 안의 메소드들이 잘 동작하는지, 매개변수 또는 리턴값을 확인하는 용도로 로그 객체를 생성함
	private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Autowired
	private PurchaseService purchaseService;

	/*
	 * @Autowired 가 내부에서 자동 의존성 주입하고 서비스와 연결해 줌, 생성 코드 필요없음 public
	 * PurchaseController() { purchaseService = new PurchaseService(); }
	 */

	// 뷰 페이지 내보내기용 메소드 -----------------------------------------------------
	// 공지사항 전체 목록보기 요청 처리용 (페이징 처리 : 한 페이지에 10개씩 출력 처리)
		@RequestMapping("nlist.do")
		public ModelAndView purchaseListMethod(ModelAndView mv, @RequestParam(name = "page", required = false) String page,
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

			// 총 목록 갯수 조회해서, 총 페이지 수 계산함
			int listCount = purchaseService.selectListCount();
			// 페이지 관련 항목들 계산 처리
			Paging paging = new Paging(listCount, limit, currentPage, "nlist.do");
			paging.calculate();

			// 서비스 모델로 페이징 적용된 목록 조회 요청하고 결과받기
			ArrayList<Purchase> list = purchaseService.selectList(paging);

			if (list != null && list.size() > 0) { // 조회 성공시
				// ModelAndView : Model + View
				mv.addObject("list", list); // request.setAttribute("list", list) 와 같음
				mv.addObject("paging", paging);

				mv.setViewName("purchase/purchaseListView");
			} else { // 조회 실패시
				mv.addObject("message", currentPage + "페이지에 출력할 공지글 목록 조회 실패!");
				mv.setViewName("common/error");
			}

			return mv;
		}
}
