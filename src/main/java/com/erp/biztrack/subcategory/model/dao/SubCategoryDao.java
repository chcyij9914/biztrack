package com.erp.biztrack.subcategory.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.subcategory.model.dto.SubCategory;

@Repository("subcategoryDao")
public class SubCategoryDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 상품목록 작성 시 카테고리 가져오기
	public ArrayList<SubCategory> selectAll() {
		List<SubCategory> list = sqlSessionTemplate.selectList("subCategoryMapper.selectAll");
		return (ArrayList<SubCategory>) list;
	}

	//서브카테고리 아이디 생성
	public String getNextSubCategoryId() {
		return sqlSessionTemplate.selectOne("subCategoryMapper.getNextSubCategoryId");
	}

	public int insertSubCategory(String subCategoryId, String subCategoryName, String categoryId) {
        Map<String, Object> map = new HashMap<>();
        map.put("subCategoryId", subCategoryId);
        map.put("subCategoryName", subCategoryName);
        map.put("categoryId", categoryId);
        return sqlSessionTemplate.insert("subCategoryMapper.insertSubCategory", map);
    }
	
	public SubCategory selectByName(String subCategoryName) {
	    return sqlSessionTemplate.selectOne("subCategoryMapper.selectByName", subCategoryName);
	}
}
