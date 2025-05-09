package com.erp.biztrack.category.service;

import java.util.ArrayList;

import com.erp.biztrack.category.model.dto.Category;

public interface CategoryService {

	//상품 등록 시 드롭다운 리스트
	ArrayList<Category>selectAll();
	
	//카테고리id 생성
    String getNextCategoryId();
    String insertAndGetId(String categoryName);

}
