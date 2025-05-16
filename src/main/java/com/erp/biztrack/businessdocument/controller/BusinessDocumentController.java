package com.erp.biztrack.businessdocument.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.businessdocument.model.service.BusinessDocumentService;
import com.erp.biztrack.client.model.service.ClientService;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.product.model.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/businessdocument")
public class BusinessDocumentController {

    @Autowired
    private BusinessDocumentService businessDocumentService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    // 출고서 목록
    @RequestMapping("/OutboundList.do")
    public String showOutboundList(HttpServletRequest request, Model model) {
        String searchType = request.getParameter("searchType");
        String keyword = request.getParameter("keyword");
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

        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        DocumentPaging pageInfo = new DocumentPaging(currentPage, "O");
        pageInfo.setSearchType(searchType);
        pageInfo.setKeyword(keyword);
        pageInfo.setApproveStep(approveStep);
        pageInfo.setApproveStatus(approveStatus);

        int totalCount = businessDocumentService.selectOutboundListCount(pageInfo);
        pageInfo.setTotal(totalCount);
        pageInfo.setStart((currentPage - 1) * pageInfo.getLimit() + 1);
        pageInfo.setEnd(pageInfo.getStart() + pageInfo.getLimit() - 1);

        model.addAttribute("documentList", businessDocumentService.selectOutboundDocumentList(pageInfo));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("approveStep", approveStep);
        model.addAttribute("approveStatus", approveStatus);

        return "businessdocument/outboundDocumentList";
    }

    // 출고서 등록 폼
    @RequestMapping("/outboundInsertForm.do")
    public String showOutboundInsertForm(Model model) {
        model.addAttribute("clientList", clientService.selectAllClients());
        model.addAttribute("productList", productService.selectAll());
        return "businessdocument/outboundInsertForm";
    }

    // 출고서 등록
    @RequestMapping(value = "/insertOutbound.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String insertOutboundDocument(
        @ModelAttribute BusinessDocument document,
        @RequestParam("approver1Id") String approver1Id,
        @RequestParam("approver2Id") String approver2Id,
        HttpServletRequest request) {

        LoginDto loginInfo = (LoginDto) request.getSession().getAttribute("loginInfo");
        String newDocumentId = businessDocumentService.selectNextOutboundId();
        document.setDocumentId(newDocumentId);
        document.setDocumentTypeId("O");

        if (loginInfo != null) {
            document.setDocumentWriterId(loginInfo.getEmpId());
            document.setDocumentManagerId(loginInfo.getEmpId());
        }

        businessDocumentService.insertOutboundDocument(document);

        if (document.getItems() != null && !document.getItems().isEmpty()) {
            for (DocumentItemDTO item : document.getItems()) {
                item.setItemId(businessDocumentService.selectNextItemId());
                item.setDocumentId(newDocumentId);
                businessDocumentService.insertDocumentItem(item);
            }
        }

        BusinessDocument approval = new BusinessDocument();
        approval.setDocumentId(newDocumentId);
        approval.setApprover1Id(approver1Id);
        approval.setApprover2Id(approver2Id);
        approval.setDocumentWriterId(loginInfo.getEmpId());
        businessDocumentService.insertApprovalInfo(approval);

        return "<script charset='UTF-8'>" +
                "alert('출고서가 등록되었습니다.');" +
                "window.opener.location.reload();" +
                "window.close();" +
                "</script>";
    }

    // 출고서 상세 보기
    @RequestMapping("/outboundDetail.do")
    public String showOutboundDetail(@RequestParam("documentId") String documentId, Model model) {
        BusinessDocument document = businessDocumentService.selectOneDocument(documentId);
        document.setItems(businessDocumentService.selectDocumentItemList(documentId));

        model.addAttribute("document", document);
        model.addAttribute("approval", businessDocumentService.selectApprovalInfo(documentId));
        model.addAttribute("file", businessDocumentService.selectOneFileByDocumentId(documentId));

        return "businessdocument/outboundDetailView";
    }

    // 출고서 수정 폼
    @RequestMapping("/outboundUpdateForm.do")
    public String showOutboundUpdate(@RequestParam("documentId") String documentId, Model model) {
        BusinessDocument document = businessDocumentService.selectOneDocument(documentId);
        document.setItems(businessDocumentService.selectDocumentItemList(documentId));

        model.addAttribute("document", document);
        model.addAttribute("itemList", businessDocumentService.selectDocumentItemList(documentId));
        model.addAttribute("approve", businessDocumentService.selectApprovalInfo(documentId));
        model.addAttribute("file", businessDocumentService.selectOneFileByDocumentId(documentId));
        model.addAttribute("productList", productService.selectAll());
        return "businessdocument/outboundDocumentUpdate";
    }

    // 출고서 수정
    @RequestMapping(value = "/outboundUpdate.do", method = RequestMethod.POST)
    public String updateOutboundDocument(
        @ModelAttribute("document") BusinessDocument document,
        @RequestParam(value = "uploadFiles", required = false) List<MultipartFile> uploadFiles,
        HttpServletRequest request,
        RedirectAttributes redirectAttr) {

        try {
            businessDocumentService.updateOutboundDocument(document);

            if (document.getItems() != null && !document.getItems().isEmpty()) {
                businessDocumentService.updateOutboundItems(document.getDocumentId(), document.getItems());
            }

            if (uploadFiles != null && !uploadFiles.isEmpty()) {
                String uploadDir = request.getServletContext().getRealPath("/resources/upload/outbound");
                File uploadPath = new File(uploadDir);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                for (MultipartFile file : uploadFiles) {
                    if (!file.isEmpty()) {
                        String originName = file.getOriginalFilename();
                        String ext = originName.substring(originName.lastIndexOf("."));
                        String rename = System.currentTimeMillis() + "_" + (int) (Math.random() * 10000) + ext;

                        File saveFile = new File(uploadDir, rename);
                        file.transferTo(saveFile);

                        FileDTO fileDTO = new FileDTO();
                        fileDTO.setDocumentId(document.getDocumentId());
                        fileDTO.setOriginalFileName(originName);
                        fileDTO.setRenameFileName(rename);
                        fileDTO.setFilePath("/resources/upload/outbound");
                        fileDTO.setUploadFileSize((int) file.getSize());

                        businessDocumentService.insertUploadFile(fileDTO);
                    }
                }
            }

            redirectAttr.addFlashAttribute("msg", "출고서가 성공적으로 수정되었습니다.");
            return "redirect:/businessdocument/outboundDetail.do?documentId=" + document.getDocumentId();

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttr.addFlashAttribute("errorMsg", "출고서 수정 중 오류가 발생했습니다.");
            return "redirect:/businessdocument/outboundUpdateForm.do?documentId=" + document.getDocumentId();
        }
    }
}
