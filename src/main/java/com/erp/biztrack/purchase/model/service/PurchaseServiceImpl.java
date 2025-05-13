package com.erp.biztrack.purchase.model.service;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
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
		return purchaseDao.selectList(paging);
	}

	
	//문서 상세보기
	@Override
	public Purchase selectPurchaseDetail(String documentId) {
		return purchaseDao.selectPurchaseDetail(documentId);
	}
	
	//문서 등록관련
	@Override
	public int insertDocument(DocumentDTO document) {
		return purchaseDao.insertDocument(document);
	}

	@Override
	public int insertDocumentItem(DocumentItemDTO item) {
		return purchaseDao.insertDocumentItem(item);
	}

	@Override
	public int insertApproval(ApproveDTO approval) {
		return purchaseDao.insertApproval(approval);
	}

	@Override
	public String selectNextDocumentIdR() {
		return purchaseDao.selectNextDocumentIdR();
	}
	
	@Override
	public String selectNextDocumentIdT() {
		return purchaseDao.selectNextDocumentIdT();
	}

	@Override
	public String selectNextApproveId() {
		return purchaseDao.selectNextApproveId();
	}

	@Override
	public String selectNextItemId() {
		return purchaseDao.selectNextItemId();
	}

	@Override
	public int insertFile(FileDTO file) {
		return purchaseDao.insertFile(file);
	}

	
}
