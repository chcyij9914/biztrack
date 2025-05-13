package com.erp.biztrack.businessdocument.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.businessdocument.model.service.BusinessDocumentService;
import com.erp.biztrack.common.Paging;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/businessdocument")
public class BusinessDocumentController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private BusinessDocumentService businessDocumentService;

    // 출고서 목록
    @RequestMapping("/OutboundList.do")
    public String showOutboundList(HttpServletRequest request, Model model) {
        // 1. 파라미터 수집
        String searchType = request.getParameter("searchType");
        String keyword = request.getParameter("keyword");
        String approveStep = request.getParameter("approveStep");
        String approveStatus = request.getParameter("approveStatus");

        // 2. 페이징 처리
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        DocumentPaging pageInfo = new DocumentPaging(currentPage, "O"); // 출고서 → documentTypeId = 'O'
        pageInfo.setSearchType(searchType);
        pageInfo.setKeyword(keyword);
        pageInfo.setApproveStep(approveStep);
        pageInfo.setApproveStatus(approveStatus);

        // 3. 서비스 호출
        ArrayList<BusinessDocument> list = businessDocumentService.selectOutboundDocumentList(pageInfo);
        pageInfo.setTotal(businessDocumentService.selectOutboundListCount(pageInfo));

        // 4. 결과 전달
        model.addAttribute("documentList", list);
        model.addAttribute("pageInfo", pageInfo);

        return "businessdocument/outboundDocumentList";
    }

    // 세금계산서 목록
    @RequestMapping("/taxinvoiceList.do")
    public String selectTaxInvoiceDocumentList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
            Model model) {

        int limit = 10;
        int totalCount = businessDocumentService.selectTaxInvoiceDocumentListCount(sqlSessionTemplate);

        Paging paging = new Paging(totalCount, limit, currentPage, "taxinvoiceList.do");
        paging.calculate();

        ArrayList<BusinessDocument> list = businessDocumentService
                .selectTaxInvoiceDocumentList(sqlSessionTemplate, paging);

        model.addAttribute("list", list);
        model.addAttribute("paging", paging);

        return "document/taxInvoiceDocumentListView";
    }
}
