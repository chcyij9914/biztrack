package com.erp.biztrack.product.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.product.model.dao.ProductDao;
import com.erp.biztrack.product.model.dto.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public int selectListCount() {
	    return productDao.selectListCount();  
	}

	
	@Override
	public ArrayList<Product> selectList(Paging paging) {
		return productDao.selectList(paging); }
	 
}