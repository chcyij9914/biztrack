package com.erp.biztrack.product.model.dto;

public class Product implements java.io.Serializable{

	private static final long serialVersionUID = 900705517172374203L;
	
	private String productCode;
	private String productName;
	private String categoryId;
	private String subCategoryId;
	private int costPrice;
	private int salePrice;
	private String categoryName;
	private String subCategoryName;
	private String newCategoryName;
	private String newSubCategoryName;
	public Product() {
		super();
	}
	public Product(String productCode, String productName, String categoryId, String subCategoryId, int costPrice,
			int salePrice, String categoryName, String subCategoryName, String newCategoryName,
			String newSubCategoryName) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.costPrice = costPrice;
		this.salePrice = salePrice;
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
		this.newCategoryName = newCategoryName;
		this.newSubCategoryName = newSubCategoryName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public int getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getNewCategoryName() {
		return newCategoryName;
	}
	public void setNewCategoryName(String newCategoryName) {
		this.newCategoryName = newCategoryName;
	}
	public String getNewSubCategoryName() {
		return newSubCategoryName;
	}
	public void setNewSubCategoryName(String newSubCategoryName) {
		this.newSubCategoryName = newSubCategoryName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", categoryId=" + categoryId
				+ ", subCategoryId=" + subCategoryId + ", costPrice=" + costPrice + ", salePrice=" + salePrice
				+ ", categoryName=" + categoryName + ", subCategoryName=" + subCategoryName + ", newCategoryName="
				+ newCategoryName + ", newSubCategoryName=" + newSubCategoryName + "]";
	}
	
	
	
}
