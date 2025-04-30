package com.erp.biztrack.purchase.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.purchase.model.dao.PurchaseDao;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDao purchaseDao;

	@Override
	public int selectListCount() {
	    return purchaseDao.selectListCount();  
	}

	
	@Override
	public ArrayList<Purchase> selectList(Paging paging) {
		return purchaseDao.selectList(paging); }
	 
}