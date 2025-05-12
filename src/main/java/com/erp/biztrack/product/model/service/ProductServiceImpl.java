package com.erp.biztrack.product.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.product.exception.ProductException;
import com.erp.biztrack.product.model.dao.ProductDao;
import com.erp.biztrack.product.model.dto.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	// 페이징 목적 (게시글 수 카운팅)
	@Override
	public int selectListCount() {
		return productDao.selectListCount();
	}

	// 판매상품 리스트 조회
	@Override
	public ArrayList<Product> selectList(Paging paging) {
		return productDao.selectList(paging);
	}

	// 구매문서 작성 시 상품목록 가져오기
	@Override
	public ArrayList<Product> selectAll() {
		return productDao.selectAll();

	}

	// 상품 상세보기
	@Override
	public Product selectProductDetail(String productId) {
		return productDao.selectProductDetail(productId);
	}

	//상품 등록
	@Override
	public int insertProduct(Product product) throws ProductException {
		int result1 = productDao.insertProduct(product);
	    int result2 = productDao.insertStock(product);    
		if (result1 <= 0) {
			throw new ProductException("상품 등록 실패");
		}
		return result1 + result2;
	}
	
	//상품 코드 자동 생성
	@Override
	public String getNextProductId() {
	    return productDao.getNextProductId();
	}
	
	//검색기능
	@Override
	public List<Product> searchByName(String keyword) {
	    return productDao.searchByName(keyword);
	}
	@Override
	public List<Product> searchByCategory(String categoryId) {
	    return productDao.searchByCategory(categoryId);
	}
	
	//상품 입출고내역
	@Override
	public List<Map<String, Object>> getProductHistory(String productId) throws ProductException {
	    try {
	        return productDao.selectProductHistory(productId);
	    } catch (Exception e) {
	        throw new ProductException("입출고 내역 조회 실패");
	    }
	}

	@Override
	public int getCalculatedStock(String productId) throws ProductException {
		  try {
		        return productDao.selectCalculatedStock(productId);
		    } catch (Exception e) {
		        throw new ProductException("총 재고 계산 실패");
		    }
	}
	
	//수정기능
	@Override
	 public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }
	
	//상품 삭제
	public int deleteProduct(String productId) throws ProductException {
	    int result1 = productDao.deleteStock(productId);
	    int result2 = productDao.deleteProduct(productId);
	    
	    if (result2 == 0) {
	        throw new ProductException("상품 삭제 실패");
	    }
	    return result1 + result2;
	}

	@Override
	public Product getProductById(String productId) {
		// TODO Auto-generated method stub
		return null;
	}
}