package com.erp.biztrack.client.controller;

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
import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.login.model.service.LoginService;
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

	// 거래처 상세보기
	@RequestMapping("cdetail.do")
	public String clientDetail(@RequestParam("clientId") String clientId, HttpServletRequest request, Model model) {

		Client client = clientService.selectClientDetail(clientId);
		String contractFilePath = clientService.selectContractFilePath(clientId);
		String businessCardFilePath = clientService.selectBusinessCardFilePath(clientId);

		// 로그인 정보에서 loginUser와 비교하기 위한 부서 ID 추출
		LoginDto loginInfo = (LoginDto) request.getSession().getAttribute("loginInfo");

		// 현재 담당자 정보 조회해서 부서 ID 추출
		LoginDto currentManager = adminService.getEmployeeById(client.getCurrentManagerId());

		model.addAttribute("client", client);
		model.addAttribute("contractFilePath", contractFilePath);
		model.addAttribute("businessCardFilePath", businessCardFilePath);
		model.addAttribute("clientDeptId", currentManager != null ? currentManager.getDeptId() : "");

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

			// 2-1. 기존 명함 파일 삭제
			String oldPath = clientService.selectBusinessCardFilePath(client.getClientId());
			if (oldPath != null && !oldPath.isEmpty()) {
				File oldFile = new File(request.getSession().getServletContext().getRealPath(oldPath));
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}

			// 2-2. 기존 DB 정보 삭제
			FileDTO deleteTarget = new FileDTO();
			deleteTarget.setClientId(client.getClientId());
			deleteTarget.setDocumentId(null); // 명함은 문서 아님
			clientService.deleteFile(deleteTarget);

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
				e.printStackTrace(); // 로그 기록 또는 슬랙 알림으로 확장 가능
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
	public ModelAndView selectDocumentList(@RequestParam(name = "page", required = false) String page,
	                                       @RequestParam(name = "limit", required = false) String slimit,
	                                       ModelAndView mv) {
	    int currentPage = (page != null) ? Integer.parseInt(page) : 1;
	    int limit = (slimit != null) ? Integer.parseInt(slimit) : 10;

	    int listCount = clientService.selectDocumentListCount();
	    Paging paging = new Paging(listCount, limit, currentPage, "documentList.do");
	    paging.calculate();

	    Map<String, Object> param = new HashMap<>();
	    param.put("startRow", paging.getStartRow());
	    param.put("endRow", paging.getEndRow());

	    ArrayList<DocumentDTO> documentList = clientService.selectDocumentList(paging);

	    mv.addObject("documentList", documentList);
	    mv.addObject("paging", paging);
	    mv.setViewName("client/documentListView");

	    return mv;
	}
	
	//제안서 등록 관련 -------------------------------------------
	//제안서 등록(GET)
	@GetMapping("documentInsertForm.do")
	public String showDocumentInsertForm(Model model) {
	    model.addAttribute("clientList", clientService.selectAllClients());      // 거래처 목록
	    model.addAttribute("productList", productService.selectAll());		// 상품 목록
	    return "client/documentInsertForm"; // JSP 경로
	}
	
	//제안서 등록(POST)
	@PostMapping("documentInsert.do")
	public String insertDocument(@ModelAttribute DocumentDTO document,
	                             @RequestParam("approver1Info") String approver1Id,
	                             @RequestParam("approver2Info") String approver2Id,
	                             @RequestParam(name = "uploadFile", required = false) MultipartFile uploadFile,
	                             HttpServletRequest request,
	                             HttpSession session,
	                             RedirectAttributes redirectAttributes) {

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
	    approve.setFirstApproveStatus("결재 대기");
	    approve.setSecondApproveStatus("결재 대기");
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
	        file.setFilePath("/resources/upload/proposal/" + renameName);
	        file.setOriginalFileName(originalName);
	        file.setRenameFileName(renameName);
	        file.setUploadFileSize((int) saveFile.length());

	        clientService.insertFile(file);
	    }

	    // 7. 완료 후 메시지 및 리다이렉트
	    redirectAttributes.addFlashAttribute("msg", "문서 " + documentId + "가 성공적으로 등록되었습니다.");
	    return "redirect:/client/documentList.do";
	}
	
	// 제안서 상세보기 관련 -----------------------------------------------------------------
	// 문서 상세보기 이동용 컨트롤러
	@GetMapping("documentDetail.do")
	public String showDocumentDetail(@RequestParam("documentId") String documentId, Model model) {

	    // 1. 문서 기본 정보 조회
	    DocumentDTO document = clientService.selectOneDocument(documentId);

	    // 2. 품목 목록 + 금액 계산
	    List<DocumentItemDTO> itemList = clientService.selectDocumentItemList(documentId);
	    int totalAmount = 0;
	    for (DocumentItemDTO item : itemList) {
	        int amount = item.getQuantity() * item.getSalePrice();
	        item.setAmount(amount);
	        totalAmount += amount;
	    }
	    document.setItems(itemList);
	    document.setTotalAmount(totalAmount); // ✅ 핵심: document 객체에 총합 저장

	    // 3. 첨부파일 정보
	    FileDTO file = clientService.selectFileByDocumentId(documentId);
	    model.addAttribute("file", file);

	    // 4. 결재 정보
	    ApproveDTO approval = clientService.selectApprovalByDocumentId(documentId);
	    model.addAttribute("approval", approval);

	    // 5. 문서 모델 등록
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

}