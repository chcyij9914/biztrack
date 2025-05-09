package com.erp.biztrack.product.model.dto;

import java.sql.Date;
import java.util.List;
import java.util.Map;

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
	
	//document 테이블 컬럼
		private String documentId; 
		private String documentTypeId; 
		private String clientId;
		private String documentWriterId; 
		private String documentManagerId; 
		private String title; 
		private String remarks; 
		private Date createdDate;
		private Date documentDate;
		private String paymentMethod;

		//document_item 테이블 컬럼
		private String itemId; 
		private int quantity; 

		//document_type 테이블 컬럼
		private String documentName; 
		private String transactionType; 

		//client 테이블 컬럼
		private String clientName;
		private String ceoName;
		private String businessNumber;
		private String companyPhone;
		private String fax;
		private String address;
		private String url;
		private String clientStatus;
		private Date contractStartDate;
		private Date contractEndDate;
		private String firstManagerId;
		private String currentManagerId;
		private String directorName;
		private String directorPhone;
		private String email;	
		private List<Map<String, Object>> historyList;

	
	public Product() {
		super();
	}


	public Product(String productId, String productName, String categoryId, String subCategoryId, int unitPrice,
			int salePrice, String categoryName, String subCategoryName, String newCategoryName,
			String newSubCategoryName, String stockId, String stock, String documentId, String documentTypeId,
			String clientId, String documentWriterId, String documentManagerId, String title, String remarks,
			Date createdDate, Date documentDate, String paymentMethod, String itemId, int quantity, String documentName,
			String transactionType, String clientName, String ceoName, String businessNumber, String companyPhone,
			String fax, String address, String url, String clientStatus, Date contractStartDate, Date contractEndDate,
			String firstManagerId, String currentManagerId, String directorName, String directorPhone, String email,
			List<Map<String, Object>> historyList) {
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
		this.documentId = documentId;
		this.documentTypeId = documentTypeId;
		this.clientId = clientId;
		this.documentWriterId = documentWriterId;
		this.documentManagerId = documentManagerId;
		this.title = title;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.documentDate = documentDate;
		this.paymentMethod = paymentMethod;
		this.itemId = itemId;
		this.quantity = quantity;
		this.documentName = documentName;
		this.transactionType = transactionType;
		this.clientName = clientName;
		this.ceoName = ceoName;
		this.businessNumber = businessNumber;
		this.companyPhone = companyPhone;
		this.fax = fax;
		this.address = address;
		this.url = url;
		this.clientStatus = clientStatus;
		this.contractStartDate = contractStartDate;
		this.contractEndDate = contractEndDate;
		this.firstManagerId = firstManagerId;
		this.currentManagerId = currentManagerId;
		this.directorName = directorName;
		this.directorPhone = directorPhone;
		this.email = email;
		this.historyList = historyList;
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


	public String getDocumentId() {
		return documentId;
	}


	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}


	public String getDocumentTypeId() {
		return documentTypeId;
	}


	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public String getDocumentWriterId() {
		return documentWriterId;
	}


	public void setDocumentWriterId(String documentWriterId) {
		this.documentWriterId = documentWriterId;
	}


	public String getDocumentManagerId() {
		return documentManagerId;
	}


	public void setDocumentManagerId(String documentManagerId) {
		this.documentManagerId = documentManagerId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getDocumentDate() {
		return documentDate;
	}


	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getDocumentName() {
		return documentName;
	}


	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public String getCeoName() {
		return ceoName;
	}


	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}


	public String getBusinessNumber() {
		return businessNumber;
	}


	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}


	public String getCompanyPhone() {
		return companyPhone;
	}


	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getClientStatus() {
		return clientStatus;
	}


	public void setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
	}


	public Date getContractStartDate() {
		return contractStartDate;
	}


	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}


	public Date getContractEndDate() {
		return contractEndDate;
	}


	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}


	public String getFirstManagerId() {
		return firstManagerId;
	}


	public void setFirstManagerId(String firstManagerId) {
		this.firstManagerId = firstManagerId;
	}


	public String getCurrentManagerId() {
		return currentManagerId;
	}


	public void setCurrentManagerId(String currentManagerId) {
		this.currentManagerId = currentManagerId;
	}


	public String getDirectorName() {
		return directorName;
	}


	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}


	public String getDirectorPhone() {
		return directorPhone;
	}


	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Map<String, Object>> getHistoryList() {
		return historyList;
	}


	public void setHistoryList(List<Map<String, Object>> historyList) {
		this.historyList = historyList;
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
				+ stock + ", documentId=" + documentId + ", documentTypeId=" + documentTypeId + ", clientId=" + clientId
				+ ", documentWriterId=" + documentWriterId + ", documentManagerId=" + documentManagerId + ", title="
				+ title + ", remarks=" + remarks + ", createdDate=" + createdDate + ", documentDate=" + documentDate
				+ ", paymentMethod=" + paymentMethod + ", itemId=" + itemId + ", quantity=" + quantity
				+ ", documentName=" + documentName + ", transactionType=" + transactionType + ", clientName="
				+ clientName + ", ceoName=" + ceoName + ", businessNumber=" + businessNumber + ", companyPhone="
				+ companyPhone + ", fax=" + fax + ", address=" + address + ", url=" + url + ", clientStatus="
				+ clientStatus + ", contractStartDate=" + contractStartDate + ", contractEndDate=" + contractEndDate
				+ ", firstManagerId=" + firstManagerId + ", currentManagerId=" + currentManagerId + ", directorName="
				+ directorName + ", directorPhone=" + directorPhone + ", email=" + email + ", historyList="
				+ historyList + "]";
	}

	
	
}
