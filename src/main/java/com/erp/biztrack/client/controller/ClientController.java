package com.erp.biztrack.client.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.biztrack.admin.model.service.AdminService;
import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.client.model.service.ClientService;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.ClovaOcrService;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.FileRenameUtil;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.employee.model.service.EmployeeService;
import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.login.model.service.LoginService;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.product.model.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/client")
public class ClientController {
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	// ClientService 서비스 모델과 연결 처리
	@Autowired
	private ClientService clientService;

	// ClovaOcrService 서비스 연결 처리
	@Autowired
	private ClovaOcrService clovaOcrService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EmployeeService employeeService;

	// 뷰 페이지 내보내기용 메소드
	// --------------------------------------------------------------
	@RequestMapping("insertForm.do")
	public String showInsertForm(HttpSession session, Model model) {
		LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
		model.addAttribute("loginInfo", loginInfo);
		ArrayList<Client> categoryList = clientService.selectCategoryList();
		model.addAttribute("categoryList", categoryList);
		return "client/clientInsertForm";
	}

	// 거래처 목록 페이지 요청 처리용 Controller (페이징 포함)
	@RequestMapping("clist.do")
	public ModelAndView clientList(ModelAndView mv, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit) {

		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		int listCount = clientService.selectListCount();
		Paging paging = new Paging(listCount, limit, currentPage, "clist.do");
		paging.calculate();

		ArrayList<Client> list = clientService.selectClientList(paging);
		ArrayList<Client> categoryList = clientService.selectCategoryList();

		if (list != null && list.size() > 0) {
			mv.addObject("clientList", list);
			mv.addObject("paging", paging);
			mv.addObject("categoryList", categoryList);
			mv.setViewName("client/clientListView");
		} else {
			mv.addObject("message", currentPage + "페이지에 출력할 거래처 목록 조회 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 거래처 등록
	// 관련--------------------------------------------------------------------------------------
	// 거래처 등록 페이지 요청 처리용 (명함만 첨부)
	@RequestMapping(value = "insert.do", method = RequestMethod.POST)
	public String insertClient(Client client, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 0. 로그인한 사번으로 최초/현재 담당자 자동 세팅
		LoginDto loginInfo = (LoginDto) request.getSession().getAttribute("loginInfo");
		if (loginInfo != null) {
			client.setFirstManagerId(loginInfo.getEmpId());
			client.setCurrentManagerId(loginInfo.getEmpId());
		}

		// 1. 명함 업로드 경로 세팅 및 디렉토리 생성
		String businessCardSavePath = request.getSession().getServletContext()
				.getRealPath("/resources/upload/businesscard");
		new File(businessCardSavePath).mkdirs();

		// 2. 카테고리 직접 입력 시 CATEGORY 테이블에 신규 등록
		// 직접입력 선택 시 categoryId 무시하고 categoryName을 사용
		if ("direct".equals(client.getCategoryId()) && client.getCategoryName() != null
				&& !client.getCategoryName().trim().isEmpty()) {
			client.setCategoryId(null); // 기존 categoryId 무효화
			clientService.insertCategoryClient(client); // 새로운 카테고리 insert
			String newCategoryId = clientService.selectLatestCategoryId();
			client.setCategoryId(newCategoryId); // 새 ID로 세팅
		}

		// 3. 거래처 정보 등록
		clientService.insertClient(client);
		String clientId = clientService.selectLastClientId();

		// 4. 세션에 저장된 명함 파일 정보 가져오기
		File savedBusinessCardFile = (File) request.getSession().getAttribute("businessCardSavedFile");
		String savedBusinessCardOriginalName = (String) request.getSession().getAttribute("businessCardOriginalName");
		String savedBusinessCardRenameName = (String) request.getSession().getAttribute("businessCardRenameName");

		// 5. 명함 파일 존재 시 UPLOAD_FILE 테이블에 저장
		if (savedBusinessCardFile != null && clientId != null) {
			FileDTO businessCardFileDTO = new FileDTO();
			businessCardFileDTO.setFilePath("/resources/upload/businesscard/" + savedBusinessCardRenameName);
			businessCardFileDTO.setOriginalFileName(savedBusinessCardOriginalName);
			businessCardFileDTO.setRenameFileName(savedBusinessCardRenameName);
			businessCardFileDTO.setUploadFileSize((int) savedBusinessCardFile.length());
			businessCardFileDTO.setDocumentId(null); // 명함은 문서 아님
			businessCardFileDTO.setClientId(clientId);

			clientService.insertFile(businessCardFileDTO);
		}

		// 6. 세션 정리
		request.getSession().removeAttribute("businessCardSavedFile");
		request.getSession().removeAttribute("businessCardOriginalName");
		request.getSession().removeAttribute("businessCardRenameName");

		// 7. 부모창 새로고침 및 등록창 닫기 처리
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println("<script>");
		response.getWriter().println("opener.location.reload();");
		response.getWriter().println("window.close();");
		response.getWriter().println("</script>");
		response.getWriter().flush();

		return null;
	}

	// 명함 OCR 적용 및 명함 파일 저장
	@ResponseBody
	@RequestMapping(value = "applyBusinessCard.do", method = RequestMethod.POST)
	public Map<String, String> applyBusinessCard(@RequestParam("businessCard") MultipartFile businessCard,
			HttpServletRequest request) {
		Map<String, String> result = new HashMap<>();

		if (!businessCard.isEmpty()) {
			try {
				String savePath = request.getSession().getServletContext()
						.getRealPath("/resources/upload/businesscard");

				// 명함 파일 저장
				String originName = businessCard.getOriginalFilename();
				String renameName = FileRenameUtil.changeFileName(originName);
				File savedFile = new File(savePath, renameName);
				businessCard.transferTo(savedFile);

				// OCR 호출
				String ocrJson = clovaOcrService.readBusinessCard(savedFile);
				Map<String, String> info = clovaOcrService.extractContactInfo(ocrJson);

				// 결과 리턴
				result.put("name", info.getOrDefault("name", ""));
				result.put("phone", info.getOrDefault("phone", ""));
				result.put("email", info.getOrDefault("email", ""));
				result.put("tel", info.getOrDefault("tel", ""));
				result.put("fax", info.getOrDefault("fax", ""));
				result.put("address", info.getOrDefault("address", ""));

				// 세션에 저장
				request.getSession().setAttribute("businessCardSavedFile", savedFile);
				request.getSession().setAttribute("businessCardOriginalName", originName);
				request.getSession().setAttribute("businessCardRenameName", renameName);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	//거래처 상세보기
	@RequestMapping("cdetail.do")
	public String clientDetail(@RequestParam("clientId") String clientId,
	                           HttpServletRequest request,
	                           Model model) {

	    // 거래처 상세 정보
	    Client client = clientService.selectClientDetail(clientId);

	    // 명함 파일 경로
	    String businessCardFilePath = clientService.selectBusinessCardFilePath(clientId);

	    // 계약서 미리보기 (기존 단건 파일 경로, 필요 시 삭제 가능)
	    String contractFilePath = clientService.selectContractFilePath(clientId);

	    // 로그인한 사용자 정보
	    LoginDto loginInfo = (LoginDto) request.getSession().getAttribute("loginInfo");

	    // 현재 담당자 정보 (사번 기준으로 EMPLOYEE 테이블에서 부서 조회)
	    Employee currentManager = employeeService.selectEmpById(client.getCurrentManagerId());
	    String clientDeptId = currentManager != null ? currentManager.getDeptId() : "";

	    // 계약서 리스트 (문서 작성자의 부서 == 로그인한 사람의 부서인 경우만 조회)
	    DocumentDTO cond = new DocumentDTO();
	    cond.setClientId(clientId);
	    cond.setDeptId(loginInfo.getDeptId()); // 로그인한 사용자 부서 기준 조회

	    ArrayList<DocumentDTO> contractList = clientService.selectContractListByClientAndDept(cond);

	    // 모델 등록
	    model.addAttribute("client", client);
	    model.addAttribute("businessCardFilePath", businessCardFilePath);
	    model.addAttribute("contractFilePath", contractFilePath); // 기존 단건 미리보기용 (선택사항)
	    model.addAttribute("clientDeptId", clientDeptId); // 담당자 부서 비교용
	    model.addAttribute("contractList", contractList); // 부서 제한된 계약서 리스트

	    return "client/clientDetailView";
	}

	// 거래처 수정 폼 요청 (GET)
	@RequestMapping(value = "cupdate.do", method = RequestMethod.GET)
	public String updateForm(@RequestParam("clientId") String clientId, Model model) {
		Client client = clientService.selectClientDetail(clientId);
		String businessCardFilePath = clientService.selectBusinessCardFilePath(clientId);

		model.addAttribute("client", client);
		model.addAttribute("businessCardFilePath", businessCardFilePath);

		return "client/clientUpdateView";
	}

	// 거래처 수정 처리 (POST)
	@RequestMapping(value = "cupdate.do", method = RequestMethod.POST)
	public String updateClient(Client client,
	        @RequestParam(value = "businessCardFile", required = false) MultipartFile businessCardFile,
	        HttpServletRequest request, RedirectAttributes redirectAttributes) {

	    // 1. 거래처 정보 수정
	    clientService.updateClient(client);

	    // 2. 명함 파일이 새로 업로드된 경우
	    if (businessCardFile != null && !businessCardFile.isEmpty()) {
	        String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/businesscard/");
	        String originalFilename = businessCardFile.getOriginalFilename();
	        String renameFilename = FileRenameUtil.changeFileName(originalFilename);

	        // 2-1. 기존 명함 파일 삭제 (서버 파일)
	        String oldPath = clientService.selectBusinessCardFilePath(client.getClientId());
	        if (oldPath != null && !oldPath.isEmpty()) {
	            File oldFile = new File(request.getSession().getServletContext().getRealPath(oldPath));
	            if (oldFile.exists()) oldFile.delete();
	        }

	        // 2-2. 기존 명함 파일 DB 정보 삭제
	        clientService.deleteFileByClientIdOnly(client.getClientId());

	        // 2-3. 새 명함 파일 등록
	        FileDTO newFile = new FileDTO();
	        newFile.setClientId(client.getClientId());
	        newFile.setOriginalFileName(originalFilename);
	        newFile.setRenameFileName(renameFilename);
	        newFile.setFilePath("/resources/upload/businesscard/" + renameFilename);
	        newFile.setUploadFileSize((int) businessCardFile.getSize());

	        try {
	            businessCardFile.transferTo(new File(savePath + renameFilename));
	            clientService.insertFile(newFile);
	        } catch (Exception e) {
	            e.printStackTrace(); // 추후 로그 처리 가능
	        }
	    }

	    redirectAttributes.addFlashAttribute("message", "거래처 정보가 수정되었습니다.");
	    return "redirect:/client/cdetail.do?clientId=" + client.getClientId();
	}


	// 거래처 수정 - empId로 직원 정보 조회 (AJAX 응답용)
	@ResponseBody
	@RequestMapping(value = "fetchEmpInfo.do", method = RequestMethod.GET)
	public Map<String, String> getEmployeeInfo(@RequestParam("empId") String empId) {
		LoginDto emp = adminService.getEmployeeById(empId);
		Map<String, String> result = new HashMap<>();
		if (emp != null) {
			result.put("empName", emp.getEmpName());
			result.put("jobId", emp.getJobId());
		} else {
			result.put("error", "not found");
		}
		return result;
	}

	// 거래처 검색 관련----------------------------------------
	// 거래처명으로 검색
	@RequestMapping("csearchName.do")
	public ModelAndView searchClientByName(@RequestParam("keyword") String keyword,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit, ModelAndView mv) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;

		int listCount = clientService.selectSearchClientNameCount(keyword);
		Paging paging = new Paging(listCount, limit, currentPage, "csearchName.do");
		paging.calculate();

		Search search = new Search();
		search.setKeyword(keyword);
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Client> list = clientService.selectSearchClientNameList(search);
		ArrayList<Client> categoryList = clientService.selectCategoryList();

		mv.addObject("clientList", list);
		mv.addObject("paging", paging);
		mv.addObject("action", "name");
		mv.addObject("keyword", keyword);
		mv.addObject("categoryList", categoryList);
		mv.setViewName("client/clientListView");
		return mv;
	}

	// 거래처상태로 검색
	@RequestMapping("csearchStatus.do")
	public ModelAndView searchClientByStatus(@RequestParam(name = "statusParam", required = false) String statusParam,
			@RequestParam(name = "page", required = false) String page, ModelAndView mv) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = 10;

		int listCount = clientService.selectSearchClientStatusCount(statusParam);
		Paging paging = new Paging(listCount, limit, currentPage, "csearchStatus.do");
		paging.calculate();

		Search search = new Search();
		search.setStatus(statusParam);
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Client> list = clientService.selectSearchClientStatusList(search);
		ArrayList<Client> categoryList = clientService.selectCategoryList();

		mv.addObject("clientList", list);
		mv.addObject("paging", paging);
		mv.addObject("action", "status");
		mv.addObject("searchStatus", statusParam);
		mv.addObject("categoryList", categoryList);
		mv.setViewName("client/clientListView");

		return mv;
	}

	// 거래처 카테고리로 검색
	@RequestMapping("csearchCategory.do")
	public ModelAndView searchClientByCategory(@RequestParam(name = "categoryId", required = false) String categoryId,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit, ModelAndView mv) {

		int currentPage = (page != null) ? Integer.parseInt(page) : 1;
		int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;

		int listCount = clientService.selectSearchClientCategoryCount(categoryId);
		Paging paging = new Paging(listCount, limit, currentPage, "csearchCategory.do");
		paging.calculate();

		Search search = new Search();
		search.setCategoryId(categoryId);
		search.setStartRow(paging.getStartRow());
		search.setEndRow(paging.getEndRow());

		ArrayList<Client> list = clientService.selectSearchClientCategoryList(search);
		ArrayList<Client> categoryList = clientService.selectCategoryList();

		String categoryName = categoryList.stream().filter(cat -> cat.getCategoryId().equals(categoryId))
				.map(Client::getCategoryName).findFirst().orElse("알 수 없음");

		mv.addObject("clientList", list);
		mv.addObject("paging", paging);
		mv.addObject("action", "category");
		mv.addObject("categoryId", categoryId);
		mv.addObject("categoryName", categoryName);
		mv.addObject("categoryList", categoryList);
		mv.setViewName("client/clientListView");

		return mv;
	}
	
	//거래처 삭제
	@RequestMapping("delete.do")
	public String deleteClient(@RequestParam("clientId") String clientId, RedirectAttributes redirectAttr) {
	    int result = clientService.deleteClient(clientId);

	    if (result > 0) {
	        redirectAttr.addFlashAttribute("msg", "거래처가 성공적으로 삭제되었습니다.");
	    } else {
	        redirectAttr.addFlashAttribute("msg", "거래처 삭제에 실패했습니다.");
	    }

	    return "redirect:/client/clist.do";
	}
	
	//문서 관리 목록 조회
	// 문서 목록 조회
	@RequestMapping("documentList.do")
	public ModelAndView selectDocumentList(
	    @RequestParam(name = "type", defaultValue = "D") String type,
	    @RequestParam(name = "page", required = false) String page,
	    @RequestParam(name = "limit", required = false) String slimit,
	    ModelAndView mv) {

	    int currentPage = (page != null) ? Integer.parseInt(page) : 1;
	    int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;

	    int listCount = clientService.selectDocumentListCountByType(type);
	    Paging paging = new Paging(listCount, limit, currentPage, "documentList.do");
	    paging.calculate();

	    Map<String, Object> param = new HashMap<>();
	    param.put("startRow", paging.getStartRow());
	    param.put("endRow", paging.getEndRow());
	    param.put("documentTypeId", type);

	    ArrayList<DocumentDTO> documentList = clientService.selectDocumentListByType(param);

	    mv.addObject("docType", type);
	    mv.addObject("documentList", documentList);
	    mv.addObject("paging", paging);
	    mv.setViewName("client/documentListView");

	    return mv;
	}

	
	//제안서 등록 관련 -------------------------------------------
	//제안서 등록(GET)
	@RequestMapping("documentInsertForm.do")
	public String showDocumentInsertForm(Model model) {
	    model.addAttribute("clientList", clientService.selectAllClients());      // 거래처 목록
	    model.addAttribute("productList", productService.selectAll());		// 상품 목록
	    return "client/documentInsertForm"; // JSP 경로
	}
	
	//계약서 등록(GET)
	@RequestMapping("cdocumentInsertForm.do")
	public String showContractInsertForm(@RequestParam(name = "proposalId", required = false) String proposalId,
	                                     HttpSession session, Model model) {

	    // 0. 로그인 정보 가져오기
	    LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
	    String empId = loginInfo.getEmpId();

	    // 1. 연결된 제안서 정보 불러오기
	    if (proposalId != null && !proposalId.trim().isEmpty()) {
	        DocumentDTO proposal = clientService.selectOneDocument(proposalId);
	        List<DocumentItemDTO> items = clientService.selectDocumentItemList(proposalId);

	        int totalAmount = 0;
	        for (DocumentItemDTO item : items) {
	            int amount = item.getQuantity() * item.getSalePrice();
	            item.setAmount(amount);
	            totalAmount += amount;
	        }
	        proposal.setItems(items);
	        proposal.setTotalAmount(totalAmount);

	        model.addAttribute("proposal", proposal);
	        model.addAttribute("items", items);
	    }

	    // 2. 로그인 작성자의 제안서 목록만 조회
	    List<DocumentDTO> proposalList = clientService.selectProposalListByWriter(empId);
	    model.addAttribute("proposalList", proposalList);

	    // 3. 거래처 목록 전달 (readonly지만 데이터 유효성 검사용으로 필요함)
	    List<Client> clientList = clientService.selectAllClients();
	    model.addAttribute("clientList", clientList);

	    return "client/cdocumentInsertForm";
	}



	//제안서 등록(POST)
	@RequestMapping(value = "documentInsert.do", method = RequestMethod.POST)
	public void insertDocument(@ModelAttribute DocumentDTO document,
	                           @RequestParam("approver1Info") String approver1Id,
	                           @RequestParam("approver2Info") String approver2Id,
	                           @RequestParam(name = "uploadFile", required = false) MultipartFile uploadFile,
	                           HttpServletRequest request,
	                           HttpServletResponse response,
	                           HttpSession session) throws IOException {

	    // 1. 문서 ID 생성
	    String documentId = clientService.selectNextDocumentIdD();
	    document.setDocumentId(documentId);
	    document.setDocumentTypeId("D"); // 제안서 고정

	    LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
	    String empId = loginInfo.getEmpId();

	    // 2. 작성자/담당자 ID 설정
	    document.setDocumentWriterId(empId);
	    document.setDocumentManagerId(empId);

	    // 3. 문서 등록
	    clientService.insertDocument(document);

	    // 4. 품목 목록 등록
	    for (DocumentItemDTO item : document.getItems()) {
	        item.setDocumentId(documentId);
	        item.setItemId(clientService.selectNextItemId());
	        clientService.insertDocumentItem(item);
	    }

	    // 5. 결재 정보 등록
	    ApproveDTO approve = new ApproveDTO();
	    approve.setApproveId(clientService.selectNextApproveId());
	    approve.setDocumentId(documentId);
	    approve.setEmpId(empId);
	    approve.setFirstApproverId(approver1Id);
	    approve.setSecondApproverId(approver2Id);
	    approve.setFirstApproveStatus("1차 결재 대기");
	    approve.setSecondApproveStatus("2차 결재 대기");
	    clientService.insertApproval(approve);

	    // 6. 파일 업로드 처리 (제안서 전용)
	    if (uploadFile != null && !uploadFile.isEmpty()) {
	        String path = request.getServletContext().getRealPath("/resources/upload/proposal");
	        new File(path).mkdirs();

	        String originalName = uploadFile.getOriginalFilename();
	        String renameName = FileRenameUtil.changeFileName(originalName);
	        File saveFile = new File(path, renameName);
	        try {
	            uploadFile.transferTo(saveFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        FileDTO file = new FileDTO();
	        file.setDocumentId(documentId);
	        file.setFilePath("/resources/upload/proposal/");
	        file.setOriginalFileName(originalName);
	        file.setRenameFileName(renameName);
	        file.setUploadFileSize((int) saveFile.length());

	        clientService.insertFile(file);
	    }

	    // 7. 등록 완료 후 팝업 닫고 부모창 새로고침
	    response.setContentType("text/html; charset=UTF-8");
	    response.getWriter().println("<script>");
	    response.getWriter().println("alert('문서 " + documentId + " 이(가) 등록되었습니다.');");
	    response.getWriter().println("opener.location.reload();");  // 부모창 새로고침
	    response.getWriter().println("window.close();");            // 현재 창 닫기
	    response.getWriter().println("</script>");
	    response.getWriter().flush();
	}
	
	// 계약서 등록(post)
	@RequestMapping(value = "cdocumentInsert.do", method = RequestMethod.POST)
	public void insertContract(@ModelAttribute DocumentDTO document,
	                           @RequestParam("approver1Info") String approver1Id,
	                           @RequestParam("approver2Info") String approver2Id,
	                           @RequestParam(name = "uploadFile", required = false) MultipartFile uploadFile,
	                           HttpServletRequest request,
	                           HttpServletResponse response,
	                           HttpSession session) throws IOException {

	    // 1. 문서 ID 생성 및 기본 설정
	    String documentId = clientService.selectNextDocumentIdC();  // 시퀀스 기반
	    document.setDocumentId(documentId);
	    document.setDocumentTypeId("C"); // 계약서 고정

	    LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
	    String empId = loginInfo.getEmpId();
	    document.setDocumentWriterId(empId);
	    document.setDocumentManagerId(empId);

	    // 2. 연결된 제안서 확인
	    if (document.getConnectDocumentId() == null || document.getConnectDocumentId().trim().isEmpty()) {
	        response.setContentType("text/html; charset=UTF-8");
	        response.getWriter().println("<script>alert('연결된 제안서가 없습니다.'); history.back();</script>");
	        response.getWriter().flush();
	        return;
	    }

	    // 3. 문서 등록
	    clientService.insertDocument(document);
	    
	    // 거래처 계약기간 업데이트
	    Client client = new Client();
	    client.setClientId(document.getClientId());
	    client.setContractStartDate(Date.valueOf(request.getParameter("contractStartDate")));
	    client.setContractEndDate(Date.valueOf(request.getParameter("contractEndDate")));
	    clientService.updateContractPeriod(client);

	    // 4. 품목 등록 (수량만 수정, 나머지는 제안서 기준)
	    for (DocumentItemDTO item : document.getItems()) {
	        item.setDocumentId(documentId);
	        item.setItemId(clientService.selectNextItemId()); // 시퀀스 기반
	        clientService.insertDocumentItem(item);
	    }

	    // 5. 결재 정보 등록
	    ApproveDTO approve = new ApproveDTO();
	    approve.setApproveId(clientService.selectNextApproveId());
	    approve.setDocumentId(documentId);
	    approve.setEmpId(empId);  // 작성자
	    approve.setFirstApproverId(approver1Id);
	    approve.setSecondApproverId(approver2Id);
	    approve.setFirstApproveStatus("1차 결재 대기");
	    approve.setSecondApproveStatus("2차 결재 대기");
	    clientService.insertApproval(approve);

	    // 6. 첨부파일 저장
	    if (uploadFile != null && !uploadFile.isEmpty()) {
	        String path = request.getServletContext().getRealPath("/resources/upload/contract");
	        new File(path).mkdirs();

	        String originalName = uploadFile.getOriginalFilename();
	        String renameName = FileRenameUtil.changeFileName(originalName);
	        File saveFile = new File(path, renameName);
	        uploadFile.transferTo(saveFile);

	        FileDTO file = new FileDTO();
	        file.setDocumentId(documentId);
	        file.setFilePath("/resources/upload/contract/" + renameName);
	        file.setOriginalFileName(originalName);
	        file.setRenameFileName(renameName);
	        file.setUploadFileSize((int) saveFile.length());

	        clientService.insertFile(file);
	    }

	    // 7. 등록 완료 후 팝업 닫기 + 부모창 새로고침
	    response.setContentType("text/html; charset=UTF-8");
	    response.getWriter().println("<script>");
	    response.getWriter().println("alert('계약서 " + documentId + " 이(가) 등록되었습니다.');");
	    response.getWriter().println("opener.location.reload();");
	    response.getWriter().println("window.close();");
	    response.getWriter().println("</script>");
	    response.getWriter().flush();
	}
	
	// 제안서 상세보기 관련 -----------------------------------------------------------------
	// 문서 상세보기
	@RequestMapping("documentDetailView.do")
	public String showDocumentDetail(@RequestParam("documentId") String documentId,
	                                 HttpServletRequest request,
	                                 Model model) {

	    // 1. 로그인 사용자 정보
	    LoginDto loginInfo = (LoginDto) request.getSession().getAttribute("loginInfo");
	    model.addAttribute("loginInfo", loginInfo); // JSP에서 권한 판단용

	    // 2. 문서 기본 정보
	    DocumentDTO document = clientService.selectOneDocument(documentId);

	    // 3. 품목 목록 및 총금액 계산
	    List<DocumentItemDTO> itemList = clientService.selectDocumentItemList(documentId);
	    int totalAmount = 0;
	    for (DocumentItemDTO item : itemList) {
	        int amount = item.getQuantity() * item.getSalePrice();
	        item.setAmount(amount);
	        totalAmount += amount;
	    }
	    document.setItems(itemList);
	    document.setTotalAmount(totalAmount);

	    // 4. 첨부파일 정보
	    FileDTO file = clientService.selectFileByDocumentId(documentId);
	    model.addAttribute("file", file);

	    // 5. 결재 정보
	    ApproveDTO approval = clientService.selectApprovalByDocumentId(documentId);
	    model.addAttribute("approval", approval);

	    // 6. 모델 등록
	    model.addAttribute("document", document);

	    return "client/documentDetailView";
	}
	
	// 문서 파일 다운로드
	@RequestMapping("documentDownload.do")
	public ModelAndView fileDownload(ModelAndView mv, HttpServletRequest request,
			@RequestParam("ofile") String originalFileName, @RequestParam("rfile") String renameFileName) {

		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/proposal");

		File downFile = new File(savePath + File.separator + renameFileName);
		File originFile = new File(originalFileName);

		mv.setViewName("filedown");
		mv.addObject("originFile", originFile);
		mv.addObject("renameFile", downFile);

		return mv;
	}
	
	// 문서 수정 관련
	// 문서 수정폼 이동 (GET)
	@GetMapping("documentUpdateForm.do")
	public String showDocumentUpdateForm(@RequestParam("documentId") String documentId, Model model) {

	    // 문서 기본 정보
	    DocumentDTO document = clientService.selectOneDocument(documentId);
	    model.addAttribute("document", document);

	    // 문서 품목 목록
	    List<DocumentItemDTO> documentItemList = clientService.selectDocumentItemList(documentId);
	    model.addAttribute("documentItemList", documentItemList);

	    // 결재자 정보
	    ApproveDTO approve = clientService.selectApprovalByDocumentId(documentId);
	    model.addAttribute("approve", approve);

	    // 첨부파일 정보
	    FileDTO file = clientService.selectFileByDocumentId(documentId);
	    model.addAttribute("file", file);

	    // 거래처 목록
	    List<Client> clientList = clientService.selectAllClients();
	    model.addAttribute("clientList", clientList);

	    // 상품 목록 (품목 select 용)
	    List<Product> productList = productService.selectAll();
	    model.addAttribute("productList", productList);

	    return "client/documentUpdateForm";
	}

	@PostMapping("documentUpdate.do")
	public String updateDocument(@ModelAttribute DocumentDTO document,
	                             @ModelAttribute ApproveDTO approve,
	                             @RequestParam(name = "uploadFile", required = false) MultipartFile uploadFile,
	                             @RequestParam(name = "deleteFlag", required = false) String deleteFlag,
	                             @RequestParam(name = "originalClientId", required = false) String originalClientId,
	                             HttpServletRequest request,
	                             Model model) {

	    String savePath = request.getServletContext().getRealPath("/resources/upload/proposal");

	    // 파일 삭제 + 업로드
	    if ((deleteFlag != null && deleteFlag.equals("yes")) || (uploadFile != null && !uploadFile.isEmpty())) {
	        clientService.deleteFileByDocumentId(document.getDocumentId());  // 기존 파일 삭제
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
	        fileDto.setFilePath("/resources/upload/proposal");
	        fileDto.setUploadFileSize((int) uploadFile.getSize());

	        clientService.insertFile(fileDto);
	    }

	    // 문서 정보 업데이트
	    clientService.updateDocument(document);

	    // 결재자 정보 업데이트
	    approve.setDocumentId(document.getDocumentId());
	    clientService.updateApprove(approve);

	    // 품목 처리
	    boolean isClientChanged = !document.getClientId().equals(originalClientId);

	    if (isClientChanged) {
	        // 거래처 변경 시: 기존 품목 삭제 후 새로 insert (itemId도 새로 생성)
	        clientService.deleteDocumentItems(document.getDocumentId());

	        for (DocumentItemDTO item : document.getItems()) {
	            item.setDocumentId(document.getDocumentId());

	            // itemId 시퀀스로 새로 생성
	            String newItemId = clientService.selectNextItemId();
	            item.setItemId(newItemId);

	            clientService.insertDocumentItem(item);
	        }
	    } else {
	        // 거래처 동일: 기존 itemId로 update
	        for (DocumentItemDTO item : document.getItems()) {
	            clientService.updateDocumentItem(item);
	        }
	    }

	    return "redirect:/client/documentDetailView.do?documentId=" + document.getDocumentId();
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
    	clientService.deleteDocumentItems(documentId);
    	clientService.deleteApprove(documentId);
    	clientService.deleteFileByDocumentId(documentId);
    	clientService.deleteDocumentOnly(documentId);

        return "redirect:/client/documentList.do";
    }
    
    // 문서 검색 관련 -------------------------------------------------------------------
    // 문서 제목으로 검색
    @GetMapping("documentSearch.do")
    public String searchDocumentList(@ModelAttribute Search search,
                                     @RequestParam(value = "searchType", required = false) String searchType,
                                     @RequestParam(value = "keyword", required = false) String keyword,
                                     @RequestParam(value = "approveStatus", required = false) String approveStatus,
                                     @RequestParam(value = "documentTypeId", required = false) String documentTypeId, // 📌 추가
                                     @RequestParam(value = "page", defaultValue = "1") int currentPage,
                                     Model model) {

        search.setScType(searchType);
        search.setKeyword(keyword);
        search.setStatus(approveStatus);
        search.setDocumentTypeId(documentTypeId); // 📌 세팅

        int listCount = 0;
        ArrayList<DocumentDTO> list = null;

        if ("title".equals(searchType)) {
            listCount = clientService.selectDocumentCountByTitle(search);
        } else if ("client".equals(searchType)) {
            listCount = clientService.selectDocumentCountByClientName(search);
        } else if ("status".equals(searchType)) {
            listCount = clientService.selectDocumentCountByStatus(search);
        }

        int limit = 10;
        Paging paging = new Paging(listCount, limit, currentPage, "documentSearch.do");
        paging.calculate();

        search.setStartRow(paging.getStartRow());
        search.setEndRow(paging.getEndRow());

        if ("title".equals(searchType)) {
            list = clientService.selectDocumentListByTitle(search);
        } else if ("client".equals(searchType)) {
            list = clientService.selectDocumentListByClientName(search);
        } else if ("status".equals(searchType)) {
            list = clientService.selectDocumentListByStatus(search);
        }

        model.addAttribute("documentList", list);
        model.addAttribute("paging", paging);
        model.addAttribute("search", search);
        model.addAttribute("documentTypeId", documentTypeId); // 탭 유지용
        model.addAttribute("docType", documentTypeId);// 탭 활성화 및 JS용

        return "client/documentListView";
    }
}