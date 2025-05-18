package com.erp.biztrack.inbound.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.inbound.model.service.InboundService;
import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.product.model.service.ProductService;
import com.erp.biztrack.purchase.model.dto.Purchase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/inbound")
public class InboundController {
	// 이 클래스 안의 메소드들이 잘 동작하는지, 매개변수 또는 리턴값을 확인하는 용도로 로그 객체를 생성함
	private static final Logger logger = LoggerFactory.getLogger(InboundController.class);

	@Autowired
	private InboundService inboundService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private EmployeeService employeeService;

	// 입고서 조회 -----------------------------------------------------
	// 문서 개수 카운팅 및 조회 
	@RequestMapping("/inbound-document.do")
	public ModelAndView selectDocumentList(@RequestParam(name = "type", defaultValue = "I") String type,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit, HttpSession session, ModelAndView mv) {

		LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
		if (loginInfo == null) {
			mv.setViewName("redirect:/login.do");
			return mv;
		}

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;

		String empId = loginInfo.getEmpId();
		String roleId = loginInfo.getRoleId();

		Map<String, Object> param = new HashMap<>();
		param.put("startRow", (currentPage - 1) * limit + 1);
		param.put("endRow", currentPage * limit);
		param.put("documentTypeId", type);
		param.put("empId", empId); // 모든 쿼리에서 공통 사용
		int listCount;
		ArrayList<DocumentDTO> documentList;

		if ("A1".equals(roleId)) {
			// 대표이사: 전체 문서
			listCount = inboundService.selectDocumentListCountByType3(param);
			documentList = inboundService.selectDocumentListByType3(param);

		} else if ("A2".equals(roleId) || "A3".equals(roleId)) {
			// 팀장/부서장: 작성자 + 결재자 포함 쿼리 (쿼리만 수정, 메서드명 그대로 사용)
			listCount = inboundService.selectDocumentListCountByType2(param);
			documentList = inboundService.selectDocumentListByType2(param);

		} else {
			// 일반 사원: 본인이 작성한 문서만
			listCount = inboundService.selectDocumentListCountByType(param);
			documentList = inboundService.selectDocumentListByType(param);
		}

		Paging paging = new Paging(listCount, limit, currentPage, "inbound-document.do");
		paging.calculate();

		mv.addObject("docType", type);
		mv.addObject("documentList", documentList);
		mv.addObject("paging", paging);
		mv.setViewName("inbound/inbound-document");

		return mv;
	}

	// 검색기능-------------------------------------------------------------------------------------
	// 검색기능 - 문서번호로 검색
	@RequestMapping("/searchByDocumentId.do")
	public String searchByDocumentId(@RequestParam("keyword") String documentId,
	                                 HttpSession session,
	                                 Model model) {
	    LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
	    String empId = loginInfo.getEmpId();
	    String roleId = loginInfo.getRoleId(); // A1, A2, A3 등

	    List<Inbound> result;

	    if ("A1".equals(roleId)) {
	        result = inboundService.searchByDocumentId3(documentId, empId);
	    } else if ("A2".equals(roleId) || "A3".equals(roleId)) {
	        result = inboundService.searchByDocumentId2(documentId, empId);
	    } else {
	        result = inboundService.searchByDocumentId(documentId, empId);
	    }

	    model.addAttribute("documentList", result);
	    return "inbound/inbound-document";
	}

	// 검색기능 - 제목으로 검색
	@RequestMapping("/searchByTitle.do")
	public String searchByTitle(@RequestParam("keyword") String title,
	                                 HttpSession session,
	                                 Model model) {
	    LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
	    String empId = loginInfo.getEmpId();
	    String roleId = loginInfo.getRoleId(); // A1, A2, A3 등

	    List<Inbound> result;

	    if ("A1".equals(roleId)) {
	        result = inboundService.searchByTitle3(title, empId);
	    } else if ("A2".equals(roleId) || "A3".equals(roleId)) {
	        result = inboundService.searchByTitle2(title, empId);
	    } else {
	        result = inboundService.searchByTitle(title, empId);
	    }

	    model.addAttribute("documentList", result);
	    return "inbound/inbound-document";
	}
	
	// 검색기능 - 상태로 검색
	@RequestMapping("/searchByStatus.do")
	public String searchByStatus(@RequestParam("status") String status,
	                                 HttpSession session,
	                                 Model model) {
	    LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
	    String empId = loginInfo.getEmpId();
	    String roleId = loginInfo.getRoleId(); // A1, A2, A3 등

	    List<Inbound> result;

	    if ("A1".equals(roleId)) {
	        result = inboundService.searchByStatus3(status, empId);
	    } else if ("A2".equals(roleId) || "A3".equals(roleId)) {
	        result = inboundService.searchByStatus2(status, empId);
	    } else {
	        result = inboundService.searchByStatus(status, empId);
	    }

	    model.addAttribute("documentList", result);
	    return "inbound/inbound-document";
	}


	// -------------------------------------------------------------------------------------------------
	// 입고 작성
	@RequestMapping("/new-inbound.do")
	public String moveNewInboundPage() {
		return "inbound/new-inbound";
	}

	// 입고서 등록 -------------------------------------------
	// 입고서 등록(GET)
	@RequestMapping("/new-inbound.do")
	public String showDocumentInsertForm(Model model) {
		model.addAttribute("clientList", clientService.selectAllClients()); // 거래처 목록
		model.addAttribute("productList", productService.selectAll()); // 상품 목록
		return "inbound/new-inbound"; // JSP 경로adminServiceImpl
	}

	// 입고서 등록(POST)
	@RequestMapping(value="/new-inbound.do", method=RequestMethod.POST)
	public void insertDocument(@ModelAttribute DocumentDTO document, @RequestParam("approver1Info") String approver1Id,
			@RequestParam("approver2Info") String approver2Id,
			@RequestParam(name = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		// 1.문서 ID생성
		String documentId = inboundService.selectNextDocumentIdI();
		document.setDocumentId(documentId);
		document.setDocumentTypeId("I");

		LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
		String empId = loginInfo.getEmpId();

		// 2. 작성자/담당자 ID 설정
		document.setDocumentWriterId(empId);
		document.setDocumentManagerId(empId);

		// 3. 문서 등록
		inboundService.insertDocument(document);

		// 4. 품목 목록 등록
		for (DocumentItemDTO item : document.getItems()) {
			item.setDocumentId(documentId);
			item.setItemId(inboundService.selectNextItemId());
			inboundService.insertDocumentItem(item);
		}

		// 5. 결재 정보 등록
		ApproveDTO approve = new ApproveDTO();
		approve.setApproveId(inboundService.selectNextApproveId());
		approve.setDocumentId(documentId);
		approve.setEmpId(empId);
		approve.setFirstApproverId(approver1Id);
		approve.setSecondApproverId(approver2Id);
		approve.setFirstApproveStatus("결재 대기");
		approve.setSecondApproveStatus("결재 대기");
		inboundService.insertApproval(approve);

		// 6. 파일 업로드 처리
		if (uploadFile != null && !uploadFile.isEmpty()) {
			String path = request.getServletContext().getRealPath("/resources/upload/inbound");
			new File(path).mkdirs();

			String originalName = uploadFile.getOriginalFilename();
			String renameName = FileRenameUtil.changeFileName(originalName);
			File saveFile = new File(path, renameName);
			uploadFile.transferTo(saveFile);

			FileDTO file = new FileDTO();
			file.setDocumentId(documentId);
			file.setFilePath("/resources/upload/inbound/");
			file.setOriginalFileName(originalName);
			file.setRenameFileName(renameName);
			file.setUploadFileSize((int) saveFile.length());
			inboundService.insertFile(file);
		}

		// 창 닫기 + 부모 창 새로고침
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write("<script>" + "alert('문서가 성공적으로 등록되었습니다.');" + "window.opener.location.href='"
				+ request.getContextPath() + "/inbound/inbound-document.do';" + "window.close();" + "</script>");
	}
	

	// 문서 상세보기 이동용 컨트롤러------------------------------------------------------------
	@RequestMapping("/inbound-detail.do")
	public String showDocumentDetail(@RequestParam("documentId") String documentId, Model model) {

		// 1. 문서 기본 정보 조회
		DocumentDTO document = inboundService.selectOneDocument(documentId);

		// 2. 품목 목록 + 금액 계산
		List<DocumentItemDTO> itemList = inboundService.selectDocumentItemList(documentId);
		int totalAmount = 0;
		for (DocumentItemDTO item : itemList) {
			int amount = item.getQuantity() * item.getSalePrice();
			item.setAmount(amount);
			totalAmount += amount;
		}
		document.setItems(itemList);
		document.setTotalAmount(totalAmount);

		// 3. 첨부파일 정보
		FileDTO file = inboundService.selectFileByDocumentId(documentId);
		model.addAttribute("file", file);

		// 4. 결재 정보
		ApproveDTO approval = inboundService.selectApprovalByDocumentId(documentId);
		model.addAttribute("approval", approval);

		// 5. 문서 모델 등록
		model.addAttribute("document", document);

		return "inbound/inbound-detail";
	}

	// 문서 파일 다운로드
	@RequestMapping("/documentDownload.do")
	public ModelAndView fileDownload(ModelAndView mv, HttpServletRequest request,
			@RequestParam("ofile") String originalFileName, @RequestParam("rfile") String renameFileName) {

		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/inbound");

		File downFile = new File(savePath + File.separator + renameFileName);
		File originFile = new File(originalFileName);

		mv.setViewName("filedown");
		mv.addObject("originFile", originFile);
		mv.addObject("renameFile", downFile);

		return mv;
	}

	// 문서 수정 관련-------------------------------
	// 문서 수정폼 이동 (GET)
	@RequestMapping("/inbound-update.do")
	public String showDocumentUpdateForm(@RequestParam("documentId") String documentId, Model model) {

		// 문서 기본 정보
		DocumentDTO document = inboundService.selectOneDocument(documentId);
		model.addAttribute("document", document);

		// 문서 품목 목록
		List<DocumentItemDTO> documentItemList = inboundService.selectDocumentItemList(documentId);
		model.addAttribute("documentItemList", documentItemList);

		// 결재자 정보
		ApproveDTO approve = inboundService.selectApprovalByDocumentId(documentId);
		model.addAttribute("approve", approve);

		// 첨부파일 정보
		FileDTO file = inboundService.selectFileByDocumentId(documentId);
		model.addAttribute("file", file);

		// 거래처 목록
		List<Client> clientList = inboundService.selectAllClients();
		model.addAttribute("clientList", clientList);

		// 상품 목록 (품목 select 용)
		List<Product> productList = productService.selectAll();
		model.addAttribute("productList", productList);

		return "inbound/inbound-update";
	}

	@RequestMapping(value="/inbound-update.do", method=RequestMethod.POST)
	public String updateDocument(@ModelAttribute DocumentDTO document, @ModelAttribute ApproveDTO approve,
			@RequestParam(name = "uploadFile", required = false) MultipartFile uploadFile,
			@RequestParam(name = "deleteFlag", required = false) String deleteFlag,
			@RequestParam(name = "originalClientId", required = false) String originalClientId,
			HttpServletRequest request, Model model) {

		String savePath = request.getServletContext().getRealPath("/resources/upload/inbound");

		// 파일 삭제 + 업로드
		if ((deleteFlag != null && deleteFlag.equals("yes")) || (uploadFile != null && !uploadFile.isEmpty())) {
			inboundService.deleteFileByDocumentId(document.getDocumentId()); // 기존 파일 삭제
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
			fileDto.setFilePath("/resources/upload/inbound");
			fileDto.setUploadFileSize((int) uploadFile.getSize());

			inboundService.insertFile(fileDto);
		}

		// 문서 정보 업데이트
		inboundService.updateDocument(document);

		// 결재자 정보 업데이트
		approve.setDocumentId(document.getDocumentId());
		inboundService.updateApprove(approve);

		// 품목 처리
		boolean isPurchaseChanged = !document.getClientId().equals(originalClientId);

		if (isPurchaseChanged) {
			// 거래처 변경 시: 기존 품목 삭제 후 새로 insert (itemId도 새로 생성)
			inboundService.deleteDocumentItems(document.getDocumentId());

			for (DocumentItemDTO item : document.getItems()) {
				item.setDocumentId(document.getDocumentId());

				// itemId 시퀀스로 새로 생성
				String newItemId = inboundService.selectNextItemId();
				item.setItemId(newItemId);

				inboundService.insertDocumentItem(item);
			}
		} else {

			// 거래처 동일: 기존 itemId로 update
			for (DocumentItemDTO item : document.getItems()) {
				inboundService.updateDocumentItem(item);
			}
		}

		return "redirect:/inbound/inbound-detail.do?documentId=" + document.getDocumentId();
	}

	@RequestMapping("/documentManEmpInfo.do")
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

	@RequestMapping("/documentDelete.do")
	public String deleteDocument(@RequestParam("documentId") String documentId) {
		// 재고 및 단가 복원
		inboundService.deleteInbound(documentId);

		// 삭제 순서 중요!
		inboundService.deleteDocumentItems(documentId);
		inboundService.deleteApprove(documentId);
		inboundService.deleteFileByDocumentId(documentId);
		inboundService.deleteDocumentOnly(documentId);

		return "redirect:/inbound/inbound-document.do";
	}

	//------------------------------
	  // 결재자 결재 기능
    @RequestMapping("/updateApprovalStatus.do")
    public String updateApprovalStatus(@RequestParam("documentId") String documentId,
                                       @RequestParam("step") int step,
                                       @RequestParam("status") String status,
                                       HttpSession session) {

        LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
        if (loginInfo == null) return "redirect:/login.do";

        ApproveDTO approve = new ApproveDTO();
        approve.setDocumentId(documentId);

        // 1차 결재 처리
        if (step == 1) {
            approve.setFirstApproveStatus(status);
        }

        // 2차 결재 처리
        else if (step == 2) {
            approve.setSecondApproveStatus(status);
        }

        inboundService.updateApprovalStatus(approve);
        if (step == 2 && "승인".equals(status)) {
            inboundService.insertInbound(documentId);
        }
        return "redirect:/inbound/inbound-detail.do?documentId=" + documentId;
    }
}
