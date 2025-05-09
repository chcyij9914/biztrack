package com.erp.biztrack.subcategory.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.subcategory.model.dao.SubCategoryDao;
import com.erp.biztrack.subcategory.model.dto.SubCategory;

@Service("subcategoryService")
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryDao subcategoryDao;

	// 상품목록 작성 시 카테고리 가져오기
	@Override
	public ArrayList<SubCategory> selectAll() {
		return subcategoryDao.selectAll();
	}

	//서브카테고리 아이디 생성
	@Override
	public String getNextSubCategoryId() {
		return subcategoryDao.getNextSubCategoryId();
	}
	
	@Override
	public String insertAndGetId(String subCategoryName, String categoryId) {
	    // 1. 기존에 동일한 이름의 서브카테고리가 있는지 확인
	    SubCategory existing = subcategoryDao.selectByName(subCategoryName);
	    if (existing != null) {
	        return existing.getSubCategoryId(); // 이미 존재하면 그 ID 리턴
	    }

	    // 2. 없으면 새로 생성
	    String newSubCategoryId = subcategoryDao.getNextSubCategoryId();
	    subcategoryDao.insertSubCategory(newSubCategoryId, subCategoryName, categoryId);
	    return newSubCategoryId;
	}
}
