package com.erp.biztrack.product.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.product.exception.ProductException;
import com.erp.biztrack.product.model.dto.Product;

public interface ProductService {
	int selectListCount();

	ArrayList<Product> selectList(Paging paging);

	ArrayList<Product> selectAll();

	// 상품 상세보기
	Product selectProductDetail(String productId);

	// 상품 등록
	public int insertProduct(Product product) throws ProductException;

	//상품 코드 자동 생성
	String getNextProductId();
	
	//검색기능
	List<Product> searchByName(String keyword);
	List<Product> searchByCategory(String categoryId);

	//상품  입출고내역
	List<Map<String, Object>> getProductHistory(String productId) throws ProductException;
	int getCalculatedStock(String productId) throws ProductException;
	Product getProductById(String productId) throws ProductException;

	
	//수정기능
    int updateProduct(Product product);

    
    //상품 삭제
    int deleteProduct(String productId);

}