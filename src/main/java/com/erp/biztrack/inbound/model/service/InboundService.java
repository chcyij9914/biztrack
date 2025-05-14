package com.erp.biztrack.inbound.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;

public interface InboundService {
	// 목록 카운트
	int selectListCount();

	// 목록 조회
	ArrayList<Inbound> selectList(Paging paging);

	// 문서 상세보기
	Inbound selectInboundDetail(String documentId);
	
	// 문서등록관련---------------------------------
		int insertDocument(DocumentDTO document);
		int insertDocumentItem(DocumentItemDTO item);
		int insertApproval(ApproveDTO approval);
		String selectNextDocumentIdI();
		String selectNextApproveId();
		String selectNextItemId();
	    int insertFile(FileDTO file);
	
	//재고 수량 변경----------------------------------
	    void insertInbound(DocumentDTO document);// 입고 등록 시 재고 + 단가 증가
	    void updateInbound(DocumentDTO document); // 입고 수정 시 재고 조정
	    void deleteInbound(String documentId);         // 입고 삭제 시 재고 감소
	
	//문서 상세정보 관련 -------------------------------------
    FileDTO selectFileByDocumentId(String documentId);
    DocumentDTO selectOneDocument(String documentId);
    ArrayList<DocumentItemDTO> selectDocumentItemList(String documentId);
    ApproveDTO selectApprovalByDocumentId(String documentId);
    
    //문서 수정 관련 --------------------------------------------
    int updateDocument(DocumentDTO document);
    int deleteDocumentItems(String documentId);
    int updateApprove(ApproveDTO approve);
    int updateDocumentItem(DocumentItemDTO item);
    int deleteFileByDocumentId(String documentId);
	List<Client> selectAllClients();

    
    //문서 삭제 관련 ----------------------------------------------
    int deleteDocumentOnly(String documentId);
    int deleteApprove(String documentId);


}