package com.erp.biztrack.businessdocument.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;

public interface BusinessDocumentService {
	
	// 출고서 목록 조회
    ArrayList<BusinessDocument> selectOutboundDocumentList(DocumentPaging pageInfo);

    // 출고서 목록 개수 조회
    int selectOutboundListCount(DocumentPaging pageInfo);

    // 출고서 등록
    int insertOutboundDocument(BusinessDocument document);

    // 출고서 품목 등록
    int insertDocumentItem(DocumentItemDTO item);
    
    // 품목 리스트 일괄 등록용
    int insertDocumentItemList(List<DocumentItemDTO> items);
    
    // 출고서 문서번호 시퀀스 조회
    String selectNextOutboundId();
    
    // 품목 ID 시퀀스 조회 
    String selectNextItemId();
    
    // 출고서 상세정보 조회
    BusinessDocument selectOneDocument(String documentId);
    
    // 첨부파일 조회
    FileDTO selectOneFileByDocumentId(String documentId);
    
    // 결재자 정보 조회
    ApproveDTO selectApprovalInfo(String documentId);
    
    // 품목 리스트 추가 조회
    List<DocumentItemDTO> selectDocumentItemList(String documentId);
    
    // 결재자 정보 입력
    int insertApprovalInfo(BusinessDocument document);
    
    // 출고서 정보 수정
    int updateOutboundDocument(BusinessDocument  document);

    // 출고 품목 전체 업데이트 (delete → insert 방식)
    void updateOutboundItems(String documentId, List<DocumentItemDTO> items);

    // 첨부파일 insert
    int insertUploadFile(FileDTO file);

	/*
	 * // 세금계산서 목록 조회 ArrayList<BusinessDocument>
	 * selectTaxInvoiceDocumentList(DocumentPaging pageInfo);
	 * 
	 * // 세금계산서 개수 int selectTaxInvoiceListCount(DocumentPaging pageInfo);
	 */
}
