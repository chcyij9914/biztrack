package com.erp.biztrack.product.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.product.model.dto.Product;

@Repository("productDao")
public class ProductDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 판매상품 리스트 조회
	public ArrayList<Product> selectList(Paging paging) {
		List<Product> list = sqlSessionTemplate.selectList("productMapper.selectList", paging);
		return (ArrayList<Product>) list;
	}

	// 판매상품 목록 카운트(페이징용)
	public int selectListCount() {
		return sqlSessionTemplate.selectOne("productMapper.selectListCount");
	}

	// 구매문서 작성 시 상품목록 가져옴
	public ArrayList<Product> selectAll() {
		List<Product> list = sqlSessionTemplate.selectList("productMapper.selectAll");
		return (ArrayList<Product>) list;
	}

	// 상품 상세보기
	public Product selectProductDetail(String productId) {
		return sqlSessionTemplate.selectOne("productMapper.selectProductDetail", productId);
	}

	// 상품 등록
	public int insertProduct(Product product) {
		return sqlSessionTemplate.insert("productMapper.insertProduct", product);
	}
	
	//상품 코드 자동 생성
	public String getNextProductId() {
	    return sqlSessionTemplate.selectOne("productMapper.getNextProductId");
	}
	
	//검색기능
	// 상품명 검색
	public List<Product> searchByName(String keyword) {
	    return sqlSessionTemplate.selectList("productMapper.searchByName", keyword);
	}
	// 카테고리 검색
	public List<Product> searchByCategory(String categoryId) {
	    return sqlSessionTemplate.selectList("productMapper.searchByCategory", categoryId);
	}
}