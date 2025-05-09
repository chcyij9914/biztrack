package com.erp.biztrack.subcategory.model.dto;

public class SubCategory implements java.io.Serializable{

	private static final long serialVersionUID = 2317672528476854935L;

	private String subCategoryId;
	private String subCategoryName;
	private String categoryId;
	private String categoryName;

	
	public SubCategory() {
		super();
	}


	public SubCategory(String subCategoryId, String subCategoryName, String categoryId, String categoryName) {
		super();
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}


	public String getSubCategoryId() {
		return subCategoryId;
	}


	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}


	public String getSubCategoryName() {
		return subCategoryName;
	}


	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}


	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "SubCategory [subCategoryId=" + subCategoryId + ", subCategoryName=" + subCategoryName + ", categoryId="
				+ categoryId + ", categoryName=" + categoryName + "]";
	}
	
	
}
