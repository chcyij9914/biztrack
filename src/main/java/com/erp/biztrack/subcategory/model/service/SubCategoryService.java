package com.erp.biztrack.subcategory.model.service;

import java.util.ArrayList;

import com.erp.biztrack.subcategory.model.dto.SubCategory;

public interface SubCategoryService {

	ArrayList<SubCategory>selectAll();
	
	//서브카테고리 아이디 생성
    String getNextSubCategoryId();

	String insertAndGetId(String subCategoryName, String categoryId);

}
