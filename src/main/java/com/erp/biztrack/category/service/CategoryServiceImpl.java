package com.erp.biztrack.category.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.category.model.dao.CategoryDao;
import com.erp.biztrack.category.model.dto.Category;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	// 상품목록 작성 시 카테고리 가져오기
	@Override
	public ArrayList<Category> selectAll() {
		return categoryDao.selectAll();
	}

	// 카테고리Id 생성
	@Override
	public String getNextCategoryId() {
		return categoryDao.getNextCategoryId();
	}

	@Override
	public String insertAndGetId(String categoryName) {
		// 1. 기존 카테고리 중에 같은 이름이 있는지 확인
		Category existing = categoryDao.selectByName(categoryName);

		if (existing != null) {
			return existing.getCategoryId(); // 이미 존재하면 그 ID 리턴
		}

		// 2. 없으면 새로 생성
		String newCategoryId = categoryDao.getNextCategoryId();
		categoryDao.insertCategory(newCategoryId, categoryName);
		return newCategoryId;
	}
}