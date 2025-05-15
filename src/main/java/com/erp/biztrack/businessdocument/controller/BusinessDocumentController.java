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
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.employee.model.service.EmployeeService;
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

	    // 상태 검색값 조합 분해 처리
	    String statusOption = request.getParameter("statusOption");
	    String approveStep = null;
	    String approveStatus = null;

	    if ("status".equals(searchType) && statusOption != null && !statusOption.isEmpty()) {
	        String[] parts = statusOption.split("/");
	        if (parts.length == 2) {
	            approveStep = parts[0];
	            approveStatus = parts[1];
	        } else if (parts.length == 1) {
	            approveStatus = parts[0];
	        }
	    }

	    // 2. 현재 페이지 설정
	    int currentPage = 1;
	    if (request.getParameter("page") != null) {
	        currentPage = Integer.parseInt(request.getParameter("page"));
	    }

	    // 3. 페이징 객체 생성 및 검색조건 세팅
	    DocumentPaging pageInfo = new DocumentPaging(currentPage, "O"); // 출고서 → documentTypeId = 'O'
	    pageInfo.setSearchType(searchType);
	    pageInfo.setKeyword(keyword);
	    pageInfo.setApproveStep(approveStep);
	    pageInfo.setApproveStatus(approveStatus);

	    // 4. 총 개수 조회 및 totalPage, startPage, endPage 계산
	    int totalCount = businessDocumentService.selectOutboundListCount(pageInfo);
	    pageInfo.setTotal(totalCount);

	    // 5. 현재 페이지 기준으로 start/end 재계산
	    pageInfo.setStart((currentPage - 1) * pageInfo.getLimit() + 1);
	    pageInfo.setEnd(pageInfo.getStart() + pageInfo.getLimit() - 1);

	    // 6. 리스트 조회
	    ArrayList<BusinessDocument> list = businessDocumentService.selectOutboundDocumentList(pageInfo);

	    // 7. 결과 전달
	    model.addAttribute("documentList", list);
	    model.addAttribute("pageInfo", pageInfo);
	    model.addAttribute("approveStep", approveStep); 
	    model.addAttribute("approveStatus", approveStatus); 

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
	    @RequestParam("approver1Id") String approver1Id,
	    @RequestParam("approver2Id") String approver2Id,
	    HttpServletRequest request) {

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

	    // 4. 품목 저장
	    List<BusinessDocument> items = document.getItems();
	    if (items != null && !items.isEmpty()) {
	        for (BusinessDocument item : items) {
	            String nextItemId = businessDocumentService.selectNextItemId();
	            item.setItemId(nextItemId);
	            item.setDocumentId(newDocumentId);
	            businessDocumentService.insertDocumentItem(item);
	        }
	    }

	    // 5. 결재자 정보 저장
	    BusinessDocument approval = new BusinessDocument();
	    approval.setDocumentId(newDocumentId);
	    approval.setApprover1Id(approver1Id);
	    approval.setApprover2Id(approver2Id);
	    approval.setDocumentWriterId(loginInfo.getEmpId());
	    businessDocumentService.insertApprovalInfo(approval);

	    // 6. 응답 처리
	    return "<script charset='UTF-8'>" +
	            "alert('출고서가 등록되었습니다.');" +
	            "window.opener.location.reload();" +
	            "window.close();" +
	            "</script>";
	}

	// 출고서 상세 보기
	@RequestMapping("/outboundDetail.do")
	public String showOutboundDetail(@RequestParam("documentId") String documentId, Model model) {
	    // 1. 문서 기본 정보 조회
	    BusinessDocument document = businessDocumentService.selectOneDocument(documentId);

	    // 2. 품목 리스트 추가 조회 
	    List<BusinessDocument> itemList = businessDocumentService.selectDocumentItemList(documentId);
	    document.setItems(itemList); // 문서에 품목 리스트 포함
	    
	    // 3. 결재 정보 & 첨부파일 정보
	    ApproveDTO approval = businessDocumentService.selectApprovalInfo(documentId);
	    FileDTO file = businessDocumentService.selectOneFileByDocumentId(documentId);

	    // 4. JSP 전달
	    model.addAttribute("document", document);
	    model.addAttribute("approval", approval);
	    model.addAttribute("file", file);

	    return "businessdocument/outboundDetailView";
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
