package com.erp.biztrack.client.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.client.model.service.ClientService;
import com.erp.biztrack.common.Paging;

@Controller
@RequestMapping("/client")
public class ClientController {
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	// ClientService 서비스 모델과 연결 처리
	@Autowired
	private ClientService clientService;
	
	// 거래처 목록 페이지 요청 처리용 Controller (페이징 포함)
	@RequestMapping("clist.do")
	public ModelAndView clientList(
	      ModelAndView mv,
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

	   int listCount = clientService.selectListCount();
	   Paging paging = new Paging(listCount, limit, currentPage, "clist.do");
	   paging.calculate();

	   ArrayList<Client> list = clientService.selectClientList(paging);

	   if (list != null && list.size() > 0) {
	      mv.addObject("clientList", list);
	      mv.addObject("paging", paging);
	      mv.setViewName("client/clientListView");
	   } else {
	      mv.addObject("message", currentPage + "페이지에 출력할 거래처 목록 조회 실패!");
	      mv.setViewName("common/error");
	   }

	   return mv;
	}
	
	
}


