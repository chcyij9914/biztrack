package com.erp.biztrack.inbound.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dao.InboundDao;
import com.erp.biztrack.inbound.model.dto.Inbound;

@Service("inboundService")
public class InboundServiceImpl implements InboundService {

	@Autowired
	private InboundDao inboundDao;

	@Override
	public int selectListCount() {
	    return inboundDao.selectListCount();  
	}

	
	@Override
	public ArrayList<Inbound> selectList(Paging paging) {
		return inboundDao.selectList(paging); }
	 
}