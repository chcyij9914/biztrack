package com.erp.biztrack.purchase.model.service;

import java.util.ArrayList;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.purchase.model.dto.Purchase;

public interface PurchaseService {
	//문서 목록 카운트
	int selectListCount();
	
	//문서 목록 조회
	ArrayList<Purchase> selectList(Paging paging);
   
	//문서번호 자동 생성
    String peekDocumentId(String type);
    String generateDocumentId(String type);

	 }