package com.erp.biztrack.businessdocument.controller;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.businessdocument.model.service.BusinessDocumentService;
import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.client.model.service.ClientService;
import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.product.model.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/businessdocument")
public class BusinessDocumentController {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	private BusinessDocumentService businessDocumentService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProductService productService;

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
		
		int totalCount = businessDocumentService.selectOutboundListCount(pageInfo);
		pageInfo.setTotal(totalCount);

		// 3. 서비스 호출
		ArrayList<BusinessDocument> list = businessDocumentService.selectOutboundDocumentList(pageInfo);
		pageInfo.setTotal(businessDocumentService.selectOutboundListCount(pageInfo));

		// 4. 결과 전달
		model.addAttribute("documentList", list);
		model.addAttribute("pageInfo", pageInfo);

		return "businessdocument/outboundDocumentList";
	}

	// 출고서 등록 폼 이동
	@RequestMapping("/outboundInsertForm.do")
	public String showOutboundInsertForm(Model model) {
	    ArrayList<Client> clientList = clientService.selectAllClients();
	    ArrayList<Product> productList = productService.selectAll();

	    model.addAttribute("clientList", clientList);
	    model.addAttribute("productList", productList);

	    return "businessdocument/outboundInsertForm";
	}
	
	// 출고서 등록 처리
	@RequestMapping(value = "/insertOutbound.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String insertOutboundDocument(
            @ModelAttribute BusinessDocument document,
            HttpServletRequest request
    ) {
        // 1. 로그인 정보 가져오기
        LoginDto loginInfo = (LoginDto) request.getSession().getAttribute("loginInfo");

        // 2. 문서번호 생성
        String newDocumentId = businessDocumentService.selectNextOutboundId();
        document.setDocumentId(newDocumentId);
        document.setDocumentTypeId("O"); // 출고서 유형

        if (loginInfo != null) {
            document.setDocumentWriterId(loginInfo.getEmpId());
            document.setDocumentManagerId(loginInfo.getEmpId());
        }

        // 3. 문서 저장
        businessDocumentService.insertOutboundDocument(document);

        // 4. 품목 저장 (items 필드에 리스트로 들어옴)
        List<BusinessDocument> items = document.getItems();
        if (items != null && !items.isEmpty()) {
            for (BusinessDocument item : items) {
            	// ITEM_ID 시퀀스 조회
                String nextItemId = businessDocumentService.selectNextItemId();

                // 필드 설정
                item.setItemId(nextItemId);              // PK
                item.setDocumentId(newDocumentId);       // FK

                // 저장
                businessDocumentService.insertDocumentItem(item);
            }
        }

        // 5. 새 창 닫기 + 목록 새로고침
        return "<script charset='UTF-8'>"
             + "alert('출고서가 등록되었습니다.');"
             + "window.opener.location.reload();"
             + "window.close();"
             + "</script>";
    }

	// 세금계산서 목록
	@RequestMapping("/TaxinvoiceList.do")
	public String showTaxInvoiceList(HttpServletRequest request, Model model) {
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

		DocumentPaging pageInfo = new DocumentPaging(currentPage, "G"); // 세금계산서 → documentTypeId = 'G'
		pageInfo.setSearchType(searchType);
		pageInfo.setKeyword(keyword);
		pageInfo.setApproveStep(approveStep);
		pageInfo.setApproveStatus(approveStatus);

		// 3. 서비스 호출
		ArrayList<BusinessDocument> list = businessDocumentService.selectTaxInvoiceDocumentList(pageInfo);
		pageInfo.setTotal(businessDocumentService.selectTaxInvoiceListCount(pageInfo));

		// 4. 결과 전달
		model.addAttribute("documentList", list);
		model.addAttribute("pageInfo", pageInfo);

		return "businessdocument/taxInvoiceDocumentList";
	}
}
