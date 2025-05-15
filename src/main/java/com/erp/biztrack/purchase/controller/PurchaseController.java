package com.erp.biztrack.purchase.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.erp.biztrack.admin.model.service.AdminServiceImpl;
import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.client.model.service.ClientService;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.FileRenameUtil;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.employee.model.service.EmployeeService;
import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.product.model.service.ProductService;
import com.erp.biztrack.purchase.model.dto.Purchase;
import com.erp.biztrack.purchase.model.service.PurchaseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	private final AdminServiceImpl adminServiceImpl;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private EmployeeService employeeService;

	PurchaseController(AdminServiceImpl adminServiceImpl) {
		this.adminServiceImpl = adminServiceImpl;
	}

	// 문서 개수 카운팅 및 조회 (품의서)
	@RequestMapping("purchase-document.do")
	public ModelAndView selectDocumentList(@RequestParam(name = "type", defaultValue = "R") String type,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit, ModelAndView mv) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;

		int listCount = purchaseService.selectDocumentListCountByType(type);

		Paging paging = new Paging(listCount, limit, currentPage, "purchase-document.do");
		paging.calculate();

		Map<String, Object> param = new HashMap<>();
		param.put("startRow", paging.getStartRow());
		param.put("endRow", paging.getEndRow());
		param.put("documentTypeId", type);

		ArrayList<DocumentDTO> documentList = purchaseService.selectDocumentListByType(param);

		mv.addObject("docType", type);
		mv.addObject("documentList", documentList);
		mv.addObject("paging", paging);
		mv.setViewName("purchase/purchase-document");

		return mv;
	}

	// 문서 개수 카운팅 및 조회 (지출결의서)
	@RequestMapping("payment-document.do")
	public ModelAndView selectDocumentListT(@RequestParam(name = "type", defaultValue = "T") String type,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit, ModelAndView mv) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;

		int listCount = purchaseService.selectDocumentListCountByTypeT(type);

		Paging paging = new Paging(listCount, limit, currentPage, "payment-document.do");
		paging.calculate();

		Map<String, Object> param = new HashMap<>();
		param.put("startRow", paging.getStartRow());
		param.put("endRow", paging.getEndRow());
		param.put("documentTypeId", type);

		ArrayList<DocumentDTO> documentList = purchaseService.selectDocumentListByTypeT(param);

		mv.addObject("docType", type);
		mv.addObject("documentList", documentList);
		mv.addObject("paging", paging);
		mv.setViewName("purchase/payment-document");

		return mv;
	}

	// 문서 작성 페이지로 이동 처리용(품의서)
	@RequestMapping("new-purchase.do")
	public String moveNewPDocumentPage() {
		return "purchase/new-purchase";
	}

	// 문서 작성 페이지로 이동 처리용(지출결의서)
	@RequestMapping("new-payment.do")
	public String moveNewPaymentDocumentPage() {
		return "purchase/new-payment";
	}

	// 검색기능-------------------------------------------------------------------------------------
	// 검색기능 - 문서번호로 검색
	// 문서번호로 검색
	@RequestMapping("searchByDocumentId.do")
	public String searchByDocumentId(@RequestParam("keyword") String documentId,
	                                 @RequestParam("documentTypeId") String documentTypeId,
	                                 Model model) {
	    Map<String, Object> param = new HashMap<>();
	    param.put("documentId", documentId);
	    param.put("documentTypeId", documentTypeId);

	    List<Purchase> result = purchaseService.searchByDocumentId(param);
	    model.addAttribute("documentList", result);

	    return "T".equals(documentTypeId) ? "purchase/payment-document" : "purchase/purchase-document";
	}

	// 제목으로 검색
	@RequestMapping("searchByTitle.do")
	public String searchByTitle(@RequestParam("keyword") String title,
	                            @RequestParam("documentTypeId") String documentTypeId,
	                            Model model) {
	    Map<String, Object> param = new HashMap<>();
	    param.put("title", title);
	    param.put("documentTypeId", documentTypeId);

	    List<Purchase> result = purchaseService.searchByTitle(param);
	    model.addAttribute("documentList", result);

	    return "T".equals(documentTypeId) ? "purchase/payment-document" : "purchase/purchase-document";
	}

	// 상태로 검색
	@RequestMapping("searchByStatus.do")
	public String searchByStatus(@RequestParam("status") String status,
	                             @RequestParam("documentTypeId") String documentTypeId,
	                             Model model) {
	    Map<String, Object> param = new HashMap<>();
	    param.put("status", status);
	    param.put("documentTypeId", documentTypeId);

	    List<Purchase> result = purchaseService.searchByStatus(param);
	    model.addAttribute("documentList", result);

	    return "T".equals(documentTypeId) ? "purchase/payment-document" : "purchase/purchase-document";
	}

	// 품의서 등록 -------------------------------------------
	// 품의서 등록(GET)
	@GetMapping("new-purchase.do")
	public String showDocumentInsertForm(Model model) {
		model.addAttribute("clientList", clientService.selectAllClients()); // 거래처 목록
		model.addAttribute("productList", productService.selectAll()); // 상품 목록
		return "purchase/new-purchase"; // JSP 경로adminServiceImpl
	}

	// 품의서 등록(POST)
	@PostMapping("new-purchase.do")
	public void insertDocument(@ModelAttribute DocumentDTO document, @RequestParam("approver1Info") String approver1Id,
			@RequestParam("approver2Info") String approver2Id,
			@RequestParam(name = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		// 1.문서 ID생성
		String documentId = purchaseService.selectNextDocumentIdR();
		document.setDocumentId(documentId);
		document.setDocumentTypeId("R");

		LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
		String empId = loginInfo.getEmpId();

		// 2. 작성자/담당자 ID 설정
		document.setDocumentWriterId(empId);
		document.setDocumentManagerId(empId);

		// 3. 문서 등록
		purchaseService.insertDocument(document);

		// 4. 품목 목록 등록
		for (DocumentItemDTO item : document.getItems()) {
			item.setDocumentId(documentId);
			item.setItemId(purchaseService.selectNextItemId());
			purchaseService.insertDocumentItem(item);
		}

		// 5. 결재 정보 등록
		ApproveDTO approve = new ApproveDTO();
		approve.setApproveId(purchaseService.selectNextApproveId());
		approve.setDocumentId(documentId);
		approve.setEmpId(empId);
		approve.setFirstApproverId(approver1Id);
		approve.setSecondApproverId(approver2Id);
		approve.setFirstApproveStatus("결재 대기");
		approve.setSecondApproveStatus("결재 대기");
		purchaseService.insertApproval(approve);

		// 6. 파일 업로드 처리
		if (uploadFile != null && !uploadFile.isEmpty()) {
			String path = request.getServletContext().getRealPath("/resources/upload/purchase");
			new File(path).mkdirs();

			String originalName = uploadFile.getOriginalFilename();
			String renameName = FileRenameUtil.changeFileName(originalName);
			File saveFile = new File(path, renameName);
			uploadFile.transferTo(saveFile);

			FileDTO file = new FileDTO();
			file.setDocumentId(documentId);
			file.setFilePath("/resources/upload/purchase/" + renameName);
			file.setOriginalFileName(originalName);
			file.setRenameFileName(renameName);
			file.setUploadFileSize((int) saveFile.length());
			purchaseService.insertFile(file);
		}

		// 창 닫기 + 부모 창 새로고침
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write("<script>" + "alert('문서가 성공적으로 등록되었습니다.');" + "window.opener.location.href='"
				+ request.getContextPath() + "/purchase/purchase-document.do';" + "window.close();" + "</script>");
	}

	// 문서 상세보기 이동용 컨트롤러
	@GetMapping("purchase-detail.do")
	public String showDocumentDetail(@RequestParam("documentId") String documentId, Model model) {

		// 1. 문서 기본 정보 조회
		DocumentDTO document = purchaseService.selectOneDocument(documentId);

		// 2. 품목 목록 + 금액 계산
		List<DocumentItemDTO> itemList = purchaseService.selectDocumentItemList(documentId);
		int totalAmount = 0;
		for (DocumentItemDTO item : itemList) {
			int amount = item.getQuantity() * item.getUnitPrice();
			item.setAmount(amount);
			totalAmount += amount;
		}
		document.setItems(itemList);
		document.setTotalAmount(totalAmount);

		// 3. 첨부파일 정보
		FileDTO file = purchaseService.selectFileByDocumentId(documentId);
		model.addAttribute("file", file);

		// 4. 결재 정보
		ApproveDTO approval = purchaseService.selectApprovalByDocumentId(documentId);
		model.addAttribute("approval", approval);

		// 5. 문서 모델 등록
		model.addAttribute("document", document);

		return "purchase/purchase-detail";
	}

	// 문서 파일 다운로드
	@RequestMapping("documentDownload.do")
	public ModelAndView fileDownload(ModelAndView mv, HttpServletRequest request,
			@RequestParam("ofile") String originalFileName, @RequestParam("rfile") String renameFileName) {

		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/purchase");

		File downFile = new File(savePath + File.separator + renameFileName);
		File originFile = new File(originalFileName);

		mv.setViewName("filedown");
		mv.addObject("originFile", originFile);
		mv.addObject("renameFile", downFile);

		return mv;
	}

	// 문서 수정 관련
	// 문서 수정폼 이동 (GET)
	@GetMapping("purchase-update.do")
	public String showDocumentUpdateForm(@RequestParam("documentId") String documentId, Model model) {

		// 문서 기본 정보
		DocumentDTO document = purchaseService.selectOneDocument(documentId);
		model.addAttribute("document", document);

		// 문서 품목 목록
		List<DocumentItemDTO> documentItemList = purchaseService.selectDocumentItemList(documentId);
		model.addAttribute("documentItemList", documentItemList);

		// 결재자 정보
		ApproveDTO approve = purchaseService.selectApprovalByDocumentId(documentId);
		model.addAttribute("approve", approve);

		// 첨부파일 정보
		FileDTO file = purchaseService.selectFileByDocumentId(documentId);
		model.addAttribute("file", file);

		// 거래처 목록
		List<Client> clientList = purchaseService.selectAllClients();
		model.addAttribute("clientList", clientList);

		// 상품 목록 (품목 select 용)
		List<Product> productList = productService.selectAll();
		model.addAttribute("productList", productList);

		return "purchase/purchase-update";
	}

	@PostMapping("purchase-update.do")
	public String updateDocument(@ModelAttribute DocumentDTO document, @ModelAttribute ApproveDTO approve,
			@RequestParam(name = "uploadFile", required = false) MultipartFile uploadFile,
			@RequestParam(name = "deleteFlag", required = false) String deleteFlag,
			@RequestParam(name = "originalClientId", required = false) String originalClientId,
			HttpServletRequest request, Model model) {

		String savePath = request.getServletContext().getRealPath("/resources/upload/purchase");

		// 파일 삭제 + 업로드
		if ((deleteFlag != null && deleteFlag.equals("yes")) || (uploadFile != null && !uploadFile.isEmpty())) {
			purchaseService.deleteFileByDocumentId(document.getDocumentId()); // 기존 파일 삭제
		}

		if (uploadFile != null && !uploadFile.isEmpty()) {
			String origin = uploadFile.getOriginalFilename();
			String rename = FileRenameUtil.changeFileName(origin);

			try {
				uploadFile.transferTo(new File(savePath + "/" + rename));
			} catch (Exception e) {
				model.addAttribute("errorMsg", "파일 업로드 실패");
				return "common/errorPage";
			}

			FileDTO fileDto = new FileDTO();
			fileDto.setDocumentId(document.getDocumentId());
			fileDto.setOriginalFileName(origin);
			fileDto.setRenameFileName(rename);
			fileDto.setFilePath("/resources/upload/purchase");
			fileDto.setUploadFileSize((int) uploadFile.getSize());

			purchaseService.insertFile(fileDto);
		}

		// 문서 정보 업데이트
		purchaseService.updateDocument(document);

		// 결재자 정보 업데이트
		approve.setDocumentId(document.getDocumentId());
		purchaseService.updateApprove(approve);

		// 품목 처리
		boolean isPurchaseChanged = !document.getClientId().equals(originalClientId);

		if (isPurchaseChanged) {
			// 거래처 변경 시: 기존 품목 삭제 후 새로 insert (itemId도 새로 생성)
			purchaseService.deleteDocumentItems(document.getDocumentId());

			for (DocumentItemDTO item : document.getItems()) {
				item.setDocumentId(document.getDocumentId());

				// itemId 시퀀스로 새로 생성
				String newItemId = purchaseService.selectNextItemId();
				item.setItemId(newItemId);

				purchaseService.insertDocumentItem(item);
			}
		} else {
			// 거래처 동일: 기존 itemId로 update
			for (DocumentItemDTO item : document.getItems()) {
				purchaseService.updateDocumentItem(item);
			}
		}

		return "redirect:/purchase/purchase-detail.do?documentId=" + document.getDocumentId();
	}

	@GetMapping("documentManEmpInfo.do")
	@ResponseBody
	public Map<String, String> fetchEmpInfo(@RequestParam("empId") String empId) {
		Employee emp = employeeService.selectEmpById(empId);

		Map<String, String> result = new HashMap<>();
		if (emp != null) {
			result.put("empId", emp.getEmpId());
			result.put("empName", emp.getEmpName());
			result.put("jobTitle", emp.getJobTitle());
		}
		return result;
	}

	@GetMapping("documentDelete.do")
	public String deleteDocument(@RequestParam("documentId") String documentId) {
		// 삭제 순서 중요!
		purchaseService.deleteDocumentItems(documentId);
		purchaseService.deleteApprove(documentId);
		purchaseService.deleteFileByDocumentId(documentId);
		purchaseService.deleteDocumentOnly(documentId);

		return "redirect:/purchase/purchase-document.do";
	}
}