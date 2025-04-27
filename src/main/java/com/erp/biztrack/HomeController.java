package com.erp.biztrack;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erp.biztrack.purchase.model.dto.Purchase;
import com.erp.biztrack.purchase.service.PurchaseService;

@Controller
public class HomeController {
	
	@RequestMapping("main.do")
	public String forwardMainView() {
		return "common/main";
	}

}
