package com.erp.biztrack.inbound.model.service;

import java.util.ArrayList;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.purchase.model.dto.Purchase;

public interface InboundService {
	//목록 카운트
	int selectListCount();
	
	//목록 조회
	ArrayList<Inbound> selectList(Paging paging);
	
	//문서 상세보기
	Inbound selectInboundDetail(String documentId);
	 }