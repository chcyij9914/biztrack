package com.erp.biztrack.subcategory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erp.biztrack.category.service.CategoryService;
import com.erp.biztrack.product.model.service.ProductService;
import com.erp.biztrack.subcategory.model.service.SubCategoryService;

@Controller
@RequestMapping("/subcategory")
public class SubCategoryController {

	@Autowired
	private SubCategoryService subcategoryService;
}
