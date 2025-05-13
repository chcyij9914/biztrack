package com.erp.biztrack.purchase.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.erp.biztrack.admin.model.service.AdminServiceImpl;
import com.erp.biztrack.client.model.service.ClientService;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.FileRenameUtil;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.login.model.dto.LoginDto;
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
	// 이 클래스 안의 메소드들이 잘 동작하는지, 매개변수 또는 리턴값을 확인하는 용도로 로그 객체를 생성함
	private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ClientService clientService;

	PurchaseController(AdminServiceImpl adminServiceImpl) {
		this.adminServiceImpl = adminServiceImpl;
	}

	/*
	 * @Autowired 가 내부에서 자동 의존성 주입하고 서비스와 연결해 줌, 생성 코드 필요없음 public
	 * PurchaseController() { purchaseService = new PurchaseService(); }
	 */

	// 뷰 페이지 내보내기용 메소드 -----------------------------------------------------
	// 공지사항 전체 목록보기 요청 처리용 (페이징 처리 : 한 페이지에 10개씩 출력 처리)
	@RequestMapping("purchase-document.do")
	public ModelAndView purchaseListMethod(ModelAndView mv, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit) {
		// 페이징 처리
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지에 출력할 목록 갯수 기본 10개로 지정함
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		// 총 목록 갯수 조회해서, 총 페이지 수 계산함
		int listCount = purchaseService.selectListCount();
		// 페이지 관련 항목들 계산 처리
		Paging paging = new Paging(listCount, limit, currentPage, "purchase-document.do");
		paging.calculate();

		// 서비스 모델로 페이징 적용된 목록 조회 요청하고 결과받기
		ArrayList<Purchase> list = purchaseService.selectList(paging);

		if (list != null && list.size() > 0) { // 조회 성공시
			// ModelAndView : Model + View
			mv.addObject("list", list); // request.setAttribute("list", list) 와 같음
			mv.addObject("paging", paging);
			mv.setViewName("purchase/purchase-document");

		} else { // 조회 실패시
			mv.addObject("message", currentPage + "페이지에 출력할 공지글 목록 조회 실패!");
		}
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
	public void insertDocument(@ModelAttribute DocumentDTO document,
	                           @RequestParam("approver1Info") String approver1Id,
	                           @RequestParam("approver2Info") String approver2Id,
	                           @RequestParam(name = "uploadFile", required = false) MultipartFile uploadFile,
	                           HttpServletRequest request,
	                           HttpServletResponse response,
	                           HttpSession session) throws IOException {
	    
	    String documentId = purchaseService.selectNextDocumentIdR();
	    document.setDocumentId(documentId);
	    document.setDocumentTypeId("R");

	    LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
	    String empId = loginInfo.getEmpId();
	    document.setDocumentWriterId(empId);
	    document.setDocumentManagerId(empId);

	    purchaseService.insertDocument(document);

	    ApproveDTO approve = new ApproveDTO();
	    approve.setApproveId(purchaseService.selectNextApproveId());
	    approve.setDocumentId(documentId);
	    approve.setEmpId(empId);
	    approve.setFirstApproverId(approver1Id);
	    approve.setSecondApproverId(approver2Id);
	    approve.setFirstApproveStatus("결재 대기");
	    approve.setSecondApproveStatus("결재 대기");
	    purchaseService.insertApproval(approve);

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
	    response.getWriter().write(
	        "<script>" +
	        "alert('문서가 성공적으로 등록되었습니다.');" +
	        "window.opener.location.href='" + request.getContextPath() + "/purchase/purchase-document.do';" +
	        "window.close();" +
	        "</script>"
	    );
	}

	// 문서 상세보기
	@RequestMapping("purchase-detail.do")
	public ModelAndView purchaseDetail(@RequestParam("documentId") String documentId, ModelAndView mv) {
		Purchase detail = purchaseService.selectPurchaseDetail(documentId);

		if (detail != null) {
			mv.addObject("purchase", detail);

			// 문서 종류에 따라 뷰 분기 (ID로 비교)
			if ("R".equals(detail.getDocumentTypeId())) {
				mv.setViewName("purchase/purchase-detail");
			} else if ("T".equals(detail.getDocumentTypeId())) {
				mv.setViewName("purchase/payment-detail");
			} else {
				mv.addObject("message", "알 수 없는 문서 유형입니다.");
				mv.setViewName("common/errorPage");
			}
		} else {
			mv.addObject("message", "해당 문서를 찾을 수 없습니다.");
			mv.setViewName("common/errorPage");
		}

		return mv;
	}

}
