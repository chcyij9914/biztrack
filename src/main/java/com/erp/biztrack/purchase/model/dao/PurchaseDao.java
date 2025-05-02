package com.erp.biztrack.purchase.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Repository("purchaseDao")
public class PurchaseDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<Purchase> selectList(Paging paging) {
		List<Purchase> list = sqlSessionTemplate.selectList("purchaseMapper.selectList", paging);
		return (ArrayList<Purchase>) list;
	}
	
	public int selectListCount() {
	    return sqlSessionTemplate.selectOne("purchaseMapper.selectListCount");
	}
	
	// 시퀀스 실제 발급
    public int selectNextDocumentSeq(String prefix) {
        if ("A".equals(prefix)) {
            return sqlSessionTemplate.selectOne("purchaseMapper.selectNextSeqA");
        } else if ("B".equals(prefix)) {
            return sqlSessionTemplate.selectOne("purchaseMapper.selectNextSeqB");
        } else {
            throw new IllegalArgumentException("Unknown document prefix: " + prefix);
        }
    }

    // 시퀀스 현재 값 조회 (추가)
    public int selectCurrentDocumentSeq(String prefix) {
        if ("A".equals(prefix)) {
            return sqlSessionTemplate.selectOne("purchaseMapper.selectCurrSeqA");
        } else if ("B".equals(prefix)) {
            return sqlSessionTemplate.selectOne("purchaseMapper.selectCurrSeqB");
        } else {
            throw new IllegalArgumentException("Unknown document prefix: " + prefix);
        }
    }
    
    //문서 상세보기
    public Purchase selectPurchaseDetail(String documentId) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectPurchaseDetail", documentId);
    }

}