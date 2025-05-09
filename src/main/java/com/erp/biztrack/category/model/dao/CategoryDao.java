package com.erp.biztrack.category.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.category.model.dto.Category;

@Repository("categoryDao")
public class CategoryDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 상품목록 작성 시 카테고리 가져오기
	public ArrayList<Category> selectAll() {
		List<Category> list = sqlSessionTemplate.selectList("categoryMapper.selectAll");
		return (ArrayList<Category>) list;
	}

	//상품 등록시 카테고리 아이디 생성
	public String getNextCategoryId() {
		return sqlSessionTemplate.selectOne("categoryMapper.getNextCategoryId");
	}
	 public int insertCategory(String categoryId, String categoryName) {
	        Map<String, Object> map = new HashMap<>();
	        map.put("categoryId", categoryId);
	        map.put("categoryName", categoryName);
	        return sqlSessionTemplate.insert("categoryMapper.insertCategory", map);
	    }
	 
	 public Category selectByName(String categoryName) {
		    return sqlSessionTemplate.selectOne("categoryMapper.selectByName", categoryName);
		}
}
