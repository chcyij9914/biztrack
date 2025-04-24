package com.erp.biztrack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("main.do")
	public String forwardMainView() {
		return "common/main";
	}
}
