package com.erp.biztrack.product.model.service;

import java.util.ArrayList;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.product.model.dto.Product;

public interface ProductService {
	int selectListCount();
	ArrayList<Product> selectList(Paging paging);
    ArrayList<Product> selectAll();
    
  //상품 상세보기
  	Product selectProductDetail(String productCode);

	 }