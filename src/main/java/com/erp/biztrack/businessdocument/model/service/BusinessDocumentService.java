package com.erp.biztrack.businessdocument.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;

public interface BusinessDocumentService {
	
	// 출고서 목록 조회
    ArrayList<BusinessDocument> selectOutboundDocumentList(DocumentPaging pageInfo);

    // 출고서 목록 개수 조회
    int selectOutboundListCount(DocumentPaging pageInfo);

    // 출고서 등록
    int insertOutboundDocument(BusinessDocument document);

    // 출고서 품목 등록
    int insertDocumentItem(BusinessDocument item);
    
    // 품목 리스트 일괄 등록용
    int insertDocumentItemList(List<BusinessDocument> items);
    
    // 출고서 문서번호 시퀀스 조회
    String selectNextOutboundId();
    
    // 품목 ID 시퀀스 조회 
    String selectNextItemId();

    // 세금계산서 목록 조회
    ArrayList<BusinessDocument> selectTaxInvoiceDocumentList(DocumentPaging pageInfo);

    // 세금계산서 개수
    int selectTaxInvoiceListCount(DocumentPaging pageInfo);
}
