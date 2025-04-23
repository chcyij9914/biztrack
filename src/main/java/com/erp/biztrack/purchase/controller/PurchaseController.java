package com.erp.biztrack.purchase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @GetMapping("/purchase-document.do")
    public String showPurchaseDocumentPage() {
        return "purchase/purchase-document";  
    }
    
    @GetMapping("/purchase-document-new.do")
    public String showPurchaseDocumentNewPage() {
        return "purchase/purchase-document-new"; 
    }
}