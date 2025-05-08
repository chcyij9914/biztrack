package com.erp.biztrack.product.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;
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
	public Product selectProductDetail(String productCode) {
		return productDao.selectProductDetail(productCode);
	}
}