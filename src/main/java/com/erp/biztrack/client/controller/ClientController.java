package com.erp.biztrack.client.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.client.model.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	// ClientService 서비스 모델과 연결 처리
	@Autowired
	private ClientService clientService;
	
	@RequestMapping("clist.do")
	public String clientList(Model model) {
		List<Client> clientList = clientService.selectClientList();
        model.addAttribute("clientList", clientList);
		
        // 뷰 이름 반환 (예: /WEB-INF/views/client/clientlist.jsp)
        return "client/clientListView";
    }
	}


