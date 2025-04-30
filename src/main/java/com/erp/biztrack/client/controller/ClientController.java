package com.erp.biztrack.client.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.client.model.service.ClientService;
import com.erp.biztrack.common.ClovaOcrService;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.FileRenameUtil;
import com.erp.biztrack.common.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

	// 뷰 페이지 내보내기용 메소드
	// --------------------------------------------------------------
	@RequestMapping("insertForm.do")
	public String showInsertForm() {
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

		if (list != null && list.size() > 0) {
			mv.addObject("clientList", list);
			mv.addObject("paging", paging);
			mv.setViewName("client/clientListView");
		} else {
			mv.addObject("message", currentPage + "페이지에 출력할 거래처 목록 조회 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 거래처 등록
	// 관련--------------------------------------------------------------------------------------
	// 거래처 등록 페이지 요청 처리용 (명함 + 파일첨부)
	@RequestMapping(value = "insert.do", method = RequestMethod.POST)
	public String insertClient(Client client, @RequestParam("contractFile") MultipartFile contractFile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		String contractSavePath = request.getSession().getServletContext().getRealPath("/resources/upload/contract");
		String businessCardSavePath = request.getSession().getServletContext()
				.getRealPath("/resources/upload/businesscard");

		new File(contractSavePath).mkdirs();
		new File(businessCardSavePath).mkdirs();

		String clientId = null;
		String documentId = null;

		// 1. 세션에 저장된 명함 파일 정보 가져오기
		File savedBusinessCardFile = (File) request.getSession().getAttribute("businessCardSavedFile");
		String savedBusinessCardOriginalName = (String) request.getSession().getAttribute("businessCardOriginalName");
		String savedBusinessCardRenameName = (String) request.getSession().getAttribute("businessCardRenameName");

		// 2. 거래처 등록
		clientService.insertClient(client);
		clientId = clientService.selectLastClientId();

		// 3. 계약서 문서 등록
		if (clientId != null) {
			DocumentDTO doc = new DocumentDTO();
			doc.setDocumentType("계약서");
			doc.setClientId(clientId);
			doc.setDocumentWriter(client.getFirstManagerId());
			doc.setDocumentManagerId(client.getCurrentManagerId());

			clientService.insertDocument(doc);
			documentId = clientService.selectLatestDocumentId();
		}

		// 4. 계약서 파일 저장
		if (!contractFile.isEmpty() && documentId != null) {
			String originName = contractFile.getOriginalFilename();
			String renameName = FileRenameUtil.changeFileName(originName);
			contractFile.transferTo(new File(contractSavePath, renameName));

			FileDTO contractFileDTO = new FileDTO();
			contractFileDTO.setFilePath("/resources/upload/contract/" + renameName);
			contractFileDTO.setOriginalFileName(originName);
			contractFileDTO.setRenameFileName(renameName);
			contractFileDTO.setUploadFileSize((int) contractFile.getSize());
			contractFileDTO.setDocumentId(documentId);
			contractFileDTO.setClientId(null); // 계약서 파일은 clientId X

			clientService.insertFile(contractFileDTO);
		}

		// 5. 명함 파일 등록
		if (savedBusinessCardFile != null && clientId != null) {
			FileDTO businessCardFileDTO = new FileDTO();
			businessCardFileDTO.setFilePath("/resources/upload/businesscard/" + savedBusinessCardRenameName);
			businessCardFileDTO.setOriginalFileName(savedBusinessCardOriginalName);
			businessCardFileDTO.setRenameFileName(savedBusinessCardRenameName);
			businessCardFileDTO.setUploadFileSize((int) savedBusinessCardFile.length());
			businessCardFileDTO.setDocumentId(null);
			businessCardFileDTO.setClientId(clientId);

			clientService.insertFile(businessCardFileDTO);
		}

		// 6. 완료 후 세션 정리
		request.getSession().removeAttribute("businessCardSavedFile");
		request.getSession().removeAttribute("businessCardOriginalName");
		request.getSession().removeAttribute("businessCardRenameName");

		// 7. 부모창 새로고침 + 현재창 닫기
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
	public String clientDetail(@RequestParam("clientId") String clientId, Model model) {
		Client client = clientService.selectClientDetail(clientId);
		String contractFilePath = clientService.selectContractFilePath(clientId);
		String businessCardFilePath = clientService.selectBusinessCardFilePath(clientId);

		model.addAttribute("client", client);
		model.addAttribute("contractFilePath", contractFilePath);
		model.addAttribute("businessCardFilePath", businessCardFilePath);

		return "client/clientDetailView";
	}

	// 거래처 수정 처리
	// 수정 폼 요청 (GET)
	@RequestMapping(value = "cupdate.do", method = RequestMethod.GET)
	public String updateForm(@RequestParam("clientId") String clientId, Model model) {
		Client client = clientService.selectClientDetail(clientId);
		String businessCardFilePath = clientService.selectBusinessCardFilePath(clientId);
		model.addAttribute("client", client);
		model.addAttribute("businessCardFilePath", businessCardFilePath);
		return "client/clientUpdateView"; // 수정 JSP 경로
	}

	// 거래처 수정 요청 (POST 방식만 사용)
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

			// 2-1. 기존 명함 물리 파일 삭제
			String oldPath = clientService.selectBusinessCardFilePath(client.getClientId());
			if (oldPath != null) {
				String fullPath = request.getSession().getServletContext().getRealPath(oldPath);
				File oldFile = new File(fullPath);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}

			// 2-2. DB의 명함 파일 정보 삭제
			FileDTO deleteTarget = new FileDTO();
			deleteTarget.setClientId(client.getClientId());
			deleteTarget.setDocumentId(null);
			clientService.deleteFile(deleteTarget);

			// 2-3. 새 명함 insert
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
				e.printStackTrace();
			}
		}

		redirectAttributes.addFlashAttribute("message", "거래처 정보가 수정되었습니다.");
		return "redirect:/client/cdetail.do?clientId=" + client.getClientId();
	}

}