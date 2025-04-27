package com.erp.biztrack.product.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.product.model.dto.Product;

@Repository("productDao")
public class ProductDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<Product> selectList(Paging paging) {
		List<Product> list = sqlSessionTemplate.selectList("productMapper.selectList", paging);
		return (ArrayList<Product>) list;
	}
	
	public int selectListCount() {
	    return sqlSessionTemplate.selectOne("productMapper.selectListCount");
	}
}