package com.erp.biztrack.inbound.service;

import java.util.ArrayList;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;

public interface InboundService {
	int selectListCount();
	ArrayList<Inbound> selectList(Paging paging);
	 }