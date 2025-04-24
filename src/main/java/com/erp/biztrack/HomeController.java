package com.erp.biztrack;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("main.do")
	public String forwardMainView() {
		return "common/main";
	}
	
	 // 구매문서 목록 조회
    @GetMapping("/purchase/purchase-document.do")
    public String showPurchaseList(Model model) {
        List<PurchaseDTO> list = purchaseService.getPurchaseList(); // 서비스에 요청
        model.addAttribute("purchaseList", list); // 모델에 담아서 JSP에 전달
        return "purchase/purchase-document"; // 뷰 이름
    }
}
