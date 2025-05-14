package com.erp.biztrack.purchase.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.purchase.model.dto.Purchase;

public interface PurchaseService {
	// 문서 목록 카운트
	int selectListCount();

	// 문서 목록 조회
	ArrayList<Purchase> selectList(Paging paging);


	// 문서 상세보기
	Purchase selectPurchaseDetail(String documentId);

	// 문서등록관련---------------------------------
	int insertDocument(DocumentDTO document);
	int insertDocumentItem(DocumentItemDTO item);
	int insertApproval(ApproveDTO approval);
	String selectNextDocumentIdR();
	String selectNextDocumentIdT();
	String selectNextApproveId();
	String selectNextItemId();
    int insertFile(FileDTO file);

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
    
    //문서 삭제 관련 ----------------------------------------------
    int deleteDocumentOnly(String documentId);
    int deleteApprove(String documentId);

	List<Client> selectAllClients();

}