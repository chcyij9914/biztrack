package com.erp.biztrack.product.model.dto;

public class Product implements java.io.Serializable{

	private static final long serialVersionUID = 900705517172374203L;
	
	//product 테이블 컬럼
	private String productId;
	private String productName;
	private String categoryId;
	private String subCategoryId;
	private int unitPrice;
	private int salePrice;
	
	//category &	subcategory 테이블 컬럼
	private String categoryName;
	private String subCategoryName;
	private String newCategoryName;
	private String newSubCategoryName;
	
	//stock 테이블 컬럼
	private String stockId;
	private String stock;
	
	public Product() {
		super();
	}

	public Product(String productId, String productName, String categoryId, String subCategoryId, int unitPrice,
			int salePrice, String categoryName, String subCategoryName, String newCategoryName,
			String newSubCategoryName, String stockId, String stock) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.unitPrice = unitPrice;
		this.salePrice = salePrice;
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
		this.newCategoryName = newCategoryName;
		this.newSubCategoryName = newSubCategoryName;
		this.stockId = stockId;
		this.stock = stock;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", categoryId=" + categoryId
				+ ", subCategoryId=" + subCategoryId + ", unitPrice=" + unitPrice + ", salePrice=" + salePrice
				+ ", categoryName=" + categoryName + ", subCategoryName=" + subCategoryName + ", newCategoryName="
				+ newCategoryName + ", newSubCategoryName=" + newSubCategoryName + ", stockId=" + stockId + ", stock="
				+ stock + "]";
	}

	
}
