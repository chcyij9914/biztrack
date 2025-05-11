package com.erp.biztrack.businessdocument.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.service.BusinessDocumentService;
import com.erp.biztrack.common.Paging;

@Controller
@RequestMapping("/businessdocument")
public class BusinessDocumentController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessDocumentController.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private BusinessDocumentService businessDocumentService;

    // 전체 문서 목록 (출고서 + 세금계산서) 조회 또는 검색
    @RequestMapping("BusinessDocumentList.do")
    public String selectBusinessDocumentList(
            @RequestParam(value = "page", defaultValue = "1") int currentPage,
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        int limit = 10;

        // 검색어가 없으면 전체 문서 목록 (출고서 + 세금계산서)
        if (keyword == null || keyword.trim().isEmpty()) {
            int totalCount = businessDocumentService.selectOutBoundDocumentListCount(sqlSessionTemplate)
                    + businessDocumentService.selectTaxInvoiceDocumentListCount(sqlSessionTemplate);

            Paging paging = new Paging(totalCount, limit, currentPage, "BusinessDocumentList.do");
            paging.calculate();

            ArrayList<BusinessDocument> outboundList =
                    businessDocumentService.selectOutBoundDocumentList(sqlSessionTemplate, paging);

            ArrayList<BusinessDocument> taxInvoiceList =
                    businessDocumentService.selectTaxInvoiceDocumentList(sqlSessionTemplate, paging);

            ArrayList<BusinessDocument> documentList = new ArrayList<>();
            documentList.addAll(outboundList);
            documentList.addAll(taxInvoiceList);

            model.addAttribute("documentList", documentList);
            model.addAttribute("paging", paging);
        } else {
            // 검색 조건 있을 경우 (문서유형 / 제목 / 거래처명)
            int totalCount = businessDocumentService.selectDocumentListSearchCount(sqlSessionTemplate, searchType, keyword);

            Paging paging = new Paging(totalCount, limit, currentPage, "BusinessDocumentList.do");
            paging.calculate();
            
            Map<String, Object> param = new HashMap<>();
            param.put("searchType", searchType);
            param.put("keyword", keyword);
            param.put("startRow", paging.getStartRow());
            param.put("endRow", paging.getEndRow());

            if ("title".equals(searchType)) {
                param.put("title", keyword);
            } else if ("type".equals(searchType)) {
                param.put("documentTypeId", keyword);
            } else if ("client".equals(searchType)) {
                param.put("clientName", keyword);
            }

            ArrayList<BusinessDocument> documentList = businessDocumentService
                    .selectDocumentListBySearch(sqlSessionTemplate, param);

            model.addAttribute("documentList", documentList);
            model.addAttribute("paging", paging);
            model.addAttribute("searchType", searchType);
            model.addAttribute("keyword", keyword);
        }

        return "businessdocument/businessDocumentList";
    }

    // 출고서 목록
    @RequestMapping("outboundList.do")
    public String selectOutBoundDocumentList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
            Model model) {

        int limit = 10;
        int totalCount = businessDocumentService.selectOutBoundDocumentListCount(sqlSessionTemplate);

        Paging paging = new Paging(totalCount, limit, currentPage, "outboundList.do");
        paging.calculate();

        ArrayList<BusinessDocument> list = businessDocumentService
                .selectOutBoundDocumentList(sqlSessionTemplate, paging);

        model.addAttribute("list", list);
        model.addAttribute("paging", paging);

        return "document/outboundDocumentListView";
    }

    // 세금계산서 목록
    @RequestMapping("taxinvoiceList.do")
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
