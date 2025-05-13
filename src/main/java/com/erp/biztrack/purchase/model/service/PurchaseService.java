package com.erp.biztrack.purchase.model.service;

import java.util.ArrayList;

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


}