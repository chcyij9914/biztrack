package com.erp.biztrack.product.model.dto;

import java.sql.Date;

public class Product implements java.io.Serializable{

	private static final long serialVersionUID = 900705517172374203L;
	
	private String productCode;
	private String productName;
	private String categoryID;
	private String subcategoryID;
	private int costPrice;
	private int salePrice;
	
	public Product() {
		super();
	}

	public Product(String productCode, String productName, String categoryID, String subcategoryID, int costPrice,
			int salePrice) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.categoryID = categoryID;
		this.subcategoryID = subcategoryID;
		this.costPrice = costPrice;
		this.salePrice = salePrice;
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

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(String subcategoryID) {
		this.subcategoryID = subcategoryID;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", categoryID=" + categoryID
				+ ", subcategoryID=" + subcategoryID + ", costPrice=" + costPrice + ", salePrice=" + salePrice + "]";
	}
	
	
	
	
	
}
