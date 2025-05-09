package com.erp.biztrack.product.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.product.exception.ProductException;
import com.erp.biztrack.product.model.dto.Product;

public interface ProductService {
	int selectListCount();

	ArrayList<Product> selectList(Paging paging);

	ArrayList<Product> selectAll();

	// 상품 상세보기
	Product selectProductDetail(String productCode);

	// 상품 등록
	public int insertProduct(Product product) throws ProductException;

	//상품 코드 자동 생성
	String getNextProductCode();
	
	//검색기능
	List<Product> searchByName(String keyword);
	List<Product> searchByCategory(String categoryId);

}