package com.erp.biztrack.purchase.service;

import java.util.ArrayList;

import com.erp.biztrack.purchase.dto.Purchase;

public interface PurchaseService {
	/*인터페이스 안의 모든 추상메소드 앞에 public abstract 표기 생략함*/ 
	int selectListCount();
	ArrayList<Purchase> selectList(Paging paging);
	Purchase selectPurchase(int purchaseNo);
}