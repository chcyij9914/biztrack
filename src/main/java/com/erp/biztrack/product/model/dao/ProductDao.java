package com.erp.biztrack.product.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.Paging;
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
	
	// 상품 등록시 재고 등록
		public int insertStock(Product product) {
			return sqlSessionTemplate.insert("productMapper.insertStock", product);
		}

	// 상품 코드 자동 생성
	public String getNextProductId() {
		return sqlSessionTemplate.selectOne("productMapper.getNextProductId");
	}

	// 검색기능
	// 상품명 검색
	public List<Product> searchByName(String keyword) {
		return sqlSessionTemplate.selectList("productMapper.searchByName", keyword);
	}

	// 카테고리 검색
	public List<Product> searchByCategory(String categoryId) {
		return sqlSessionTemplate.selectList("productMapper.searchByCategory", categoryId);
	}

	// 상품 입출고내역
	public List<Map<String, Object>> selectProductHistory(String productId) {
		return sqlSessionTemplate.selectList("productMapper.selectProductHistory", productId);
	}
	public int selectCalculatedStock(String productId) {
	    return sqlSessionTemplate.selectOne("productMapper.selectCalculatedStock", productId);
	}
	
	//수정기능
	public int updateProduct(Product product) {
		 int result1 = sqlSessionTemplate.update("productMapper.updateProduct", product);
		    int result2 = sqlSessionTemplate.update("productMapper.updateStock", product);
		    int result3 = 0;
		    int result4 = 0;

		    if (product.getNewCategoryName() != null && !product.getNewCategoryName().isEmpty()) {
		        result3 = sqlSessionTemplate.update("productMapper.updateCategoryName", product);
		    }

		    if (product.getNewSubCategoryName() != null && !product.getNewSubCategoryName().isEmpty()) {
		        result4 = sqlSessionTemplate.update("productMapper.updateSubCategoryName", product);
		    }

		    return result1 + result2 + result3 + result4;
		}
	
	//상품 삭제
	public int deleteStock(String productId) {
	    return sqlSessionTemplate.delete("productMapper.deleteStock", productId);
	}
	public int deleteProduct(String productId) {
	    return sqlSessionTemplate.delete("productMapper.deleteProduct", productId);
	}
	}
