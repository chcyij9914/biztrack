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
}