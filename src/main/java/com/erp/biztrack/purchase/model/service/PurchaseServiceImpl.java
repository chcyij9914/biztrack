package com.erp.biztrack.purchase.model.service;

import java.text.DecimalFormat;
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
	        return purchaseDao.selectList(paging);
	    }
	 
	 // 실제 상신 시 문서번호 발급
    @Override
    public String generateDocumentId(String type) {
        String prefix = "";
        if ("구매품의서".equals(type)) {
            prefix = "A";
        } else if ("지출결의서".equals(type)) {
            prefix = "B";
        } else {
            throw new IllegalArgumentException("Unknown document type: " + type);
        }

        int nextSeq = purchaseDao.selectNextDocumentSeq(prefix); // 실제 시퀀스 NEXTVAL
        String formattedSeq = new DecimalFormat("000").format(nextSeq);
        return prefix + formattedSeq;
    }

    // 문서 작성 전에 보여주기용 (현재 시퀀스값 조회)
    @Override
    public String peekDocumentId(String type) {
        String prefix = "";
        if ("구매품의서".equals(type)) {
            prefix = "A";
        } else if ("지출결의서".equals(type)) {
            prefix = "B";
        } else {
            throw new IllegalArgumentException("Unknown document type: " + type);
        }

        int currentSeq = purchaseDao.selectCurrentDocumentSeq(prefix); // 시퀀스 CURRVAL 조회
        String formattedSeq = new DecimalFormat("000").format(currentSeq);
        return prefix + formattedSeq;
    }


}
