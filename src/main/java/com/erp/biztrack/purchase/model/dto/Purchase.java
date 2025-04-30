package com.erp.biztrack.purchase.model.dto;

import java.sql.Date;

public class Purchase implements java.io.Serializable{

	private static final long serialVersionUID = 1873562231343658677L;

	//purchase 테이블 컬럼
	private String purchaseId; 
	private String documentId; 
	private String productCode; 
	private int purchaseQuantity; 
	private String vendorName; 
	private Date quoteDate; 
	private Date invoiceDate;
	
	//join한 테이블 컬럼
	private String documentType;
	private String clientId;
	private String documentWriterId;
	private String documentManagerId;
	private String approveId;
	private String empId;
	private String approver1Id;
	private Date approve1Date;
	private String approve1Status;
	private String approver2Id;
	private Date Dateapprove2Date;
	private String approve2Status;
	
	
	
	public Purchase() {
		super();
	}
	
	
	public Purchase(String purchaseId, String documentId, String productCode, int purchaseQuantity, String vendorName,
			Date quoteDate, Date invoiceDate, String documentType, String clientId, String documentWriterId,
			String documentManagerId, String approveId, String empId, String approver1Id, Date approve1Date,
			String approve1Status, String approver2Id, Date dateapprove2Date, String approve2Status) {
		super();
		this.purchaseId = purchaseId;
		this.documentId = documentId;
		this.productCode = productCode;
		this.purchaseQuantity = purchaseQuantity;
		this.vendorName = vendorName;
		this.quoteDate = quoteDate;
		this.invoiceDate = invoiceDate;
		this.documentType = documentType;
		this.clientId = clientId;
		this.documentWriterId = documentWriterId;
		this.documentManagerId = documentManagerId;
		this.approveId = approveId;
		this.empId = empId;
		this.approver1Id = approver1Id;
		this.approve1Date = approve1Date;
		this.approve1Status = approve1Status;
		this.approver2Id = approver2Id;
		Dateapprove2Date = dateapprove2Date;
		this.approve2Status = approve2Status;
	}
	
	
	
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Date getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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
	public Date getDateapprove2Date() {
		return Dateapprove2Date;
	}
	public void setDateapprove2Date(Date dateapprove2Date) {
		Dateapprove2Date = dateapprove2Date;
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
		return "Purchase [purchaseId=" + purchaseId + ", documentId=" + documentId + ", productCode=" + productCode
				+ ", purchaseQuantity=" + purchaseQuantity + ", vendorName=" + vendorName + ", quoteDate=" + quoteDate
				+ ", invoiceDate=" + invoiceDate + ", documentType=" + documentType + ", clientId=" + clientId
				+ ", documentWriterId=" + documentWriterId + ", documentManagerId=" + documentManagerId + ", approveId="
				+ approveId + ", empId=" + empId + ", approver1Id=" + approver1Id + ", approve1Date=" + approve1Date
				+ ", approve1Status=" + approve1Status + ", approver2Id=" + approver2Id + ", Dateapprove2Date="
				+ Dateapprove2Date + ", approve2Status=" + approve2Status + "]";
	}
	
	
	
	
	
	
	
}
