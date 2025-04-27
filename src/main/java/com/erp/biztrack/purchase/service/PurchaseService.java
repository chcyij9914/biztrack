package com.erp.biztrack.purchase.service;

import java.util.ArrayList;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.purchase.model.dto.Purchase;

public interface PurchaseService {
	int selectListCount();
	ArrayList<Purchase> selectList(Paging paging);
	 }