package com.erp.biztrack.purchase.model.dto;

import java.sql.Date;

public class Purchase implements java.io.Serializable{

	private static final long serialVersionUID = 1873562231343658677L;

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
	private String productId; 
	private int quantity; 

	//document_type 테이블 컬럼
	private String documentName; 
	private String transactionType; 

	//client 테이블 컬럼
	private String clientName;
	private String ceoName;
	private String businessNumber;
	private String categoryId;
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
	
	//approve 테이블 컬럼
	private String approveId;
	private String empId;
	private String approver1Id;
	private Date approve1Date;
	private String approve1Status;
	private String approver2Id;
	private Date approve2Date;
	private String approve2Status;
	
	public Purchase() {
		super();
	}

	public Purchase(String documentId, String documentTypeId, String clientId, String documentWriterId,
			String documentManagerId, String title, String remarks, Date createdDate, Date documentDate,
			String paymentMethod, String itemId, String productId, int quantity, String documentName,
			String transactionType, String clientName, String ceoName, String businessNumber, String categoryId,
			String companyPhone, String fax, String address, String url, String clientStatus, Date contractStartDate,
			Date contractEndDate, String firstManagerId, String currentManagerId, String directorName,
			String directorPhone, String email, String approveId, String empId, String approver1Id, Date approve1Date,
			String approve1Status, String approver2Id, Date approve2Date, String approve2Status) {
		super();
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
		this.productId = productId;
		this.quantity = quantity;
		this.documentName = documentName;
		this.transactionType = transactionType;
		this.clientName = clientName;
		this.ceoName = ceoName;
		this.businessNumber = businessNumber;
		this.categoryId = categoryId;
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
		this.approveId = approveId;
		this.empId = empId;
		this.approver1Id = approver1Id;
		this.approve1Date = approve1Date;
		this.approve1Status = approve1Status;
		this.approver2Id = approver2Id;
		this.approve2Date = approve2Date;
		this.approve2Status = approve2Status;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

	public String getApproveId() {
		return approveId;
	}

	public void setApproveId(String approveId) {
		this.approveId = approveId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getApprover1Id() {
		return approver1Id;
	}

	public void setApprover1Id(String approver1Id) {
		this.approver1Id = approver1Id;
	}

	public Date getApprove1Date() {
		return approve1Date;
	}

	public void setApprove1Date(Date approve1Date) {
		this.approve1Date = approve1Date;
	}

	public String getApprove1Status() {
		return approve1Status;
	}

	public void setApprove1Status(String approve1Status) {
		this.approve1Status = approve1Status;
	}

	public String getApprover2Id() {
		return approver2Id;
	}

	public void setApprover2Id(String approver2Id) {
		this.approver2Id = approver2Id;
	}

	public Date getApprove2Date() {
		return approve2Date;
	}

	public void setApprove2Date(Date approve2Date) {
		this.approve2Date = approve2Date;
	}

	public String getApprove2Status() {
		return approve2Status;
	}

	public void setApprove2Status(String approve2Status) {
		this.approve2Status = approve2Status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Purchase [documentId=" + documentId + ", documentTypeId=" + documentTypeId + ", clientId=" + clientId
				+ ", documentWriterId=" + documentWriterId + ", documentManagerId=" + documentManagerId + ", title="
				+ title + ", remarks=" + remarks + ", createdDate=" + createdDate + ", documentDate=" + documentDate
				+ ", paymentMethod=" + paymentMethod + ", itemId=" + itemId + ", productId=" + productId + ", quantity="
				+ quantity + ", documentName=" + documentName + ", transactionType=" + transactionType + ", clientName="
				+ clientName + ", ceoName=" + ceoName + ", businessNumber=" + businessNumber + ", categoryId="
				+ categoryId + ", companyPhone=" + companyPhone + ", fax=" + fax + ", address=" + address + ", url="
				+ url + ", clientStatus=" + clientStatus + ", contractStartDate=" + contractStartDate
				+ ", contractEndDate=" + contractEndDate + ", firstManagerId=" + firstManagerId + ", currentManagerId="
				+ currentManagerId + ", directorName=" + directorName + ", directorPhone=" + directorPhone + ", email="
				+ email + ", approveId=" + approveId + ", empId=" + empId + ", approver1Id=" + approver1Id
				+ ", approve1Date=" + approve1Date + ", approve1Status=" + approve1Status + ", approver2Id="
				+ approver2Id + ", approve2Date=" + approve2Date + ", approve2Status=" + approve2Status + "]";
	}

	
	
}
