package com.erp.biztrack.inbound.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dao.InboundDao;
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Service("inboundService")
public class InboundServiceImpl implements InboundService {

	@Autowired
	private InboundDao inboundDao;

	//입고 카운팅
	@Override
	public int selectListCount() {
	    return inboundDao.selectListCount();  
	}

	//입고목록 조회
	@Override
	public ArrayList<Inbound> selectList(Paging paging) {
		return inboundDao.selectList(paging); }
	
	
	//입고 상세보기
		@Override
		public Inbound selectInboundDetail(String documentId) {
			return inboundDao.selectInboundDetail(documentId);
		}
	 
}