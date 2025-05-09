package com.erp.biztrack.common;

import java.sql.Date;

public class DocumentDTO implements java.io.Serializable {
	private static final long serialVersionUID = -7970793635367626682L;

	private String documentId;              // DOCUMENT_ID VARCHAR2(30)
	private String documentTypeId;          // DOCUMENT_TYPE_ID VARCHAR2(30)
	private String documentName;            // DOCUMENT_NAME (조인된 문서 이름)
	private String transactionType;         // TRANSACTION_TYPE (출고, 입고, 구매 등)
	private String clientId;                // CLIENT_ID VARCHAR2(30)
	private String clientName;              // CLIENT_NAME VARCHAR2(30)
	private String documentWriter;          // DOCUMENT_WRITER_ID VARCHAR2(30)
	private String documentManagerId;       // DOCUMENT_MANAGER_ID VARCHAR2(30)
	private String title;                   // TITLE VARCHAR2(100)
	private String remarks;                 // REMARKS VARCHAR2(1000)
	private java.sql.Date createdDate;      // CREATED_DATE DATE
	private java.sql.Date documentDate;     // DOCUMENT_DATE DATE
	private String paymentMethod;           // PAYMENT_METHOD VARCHAR2(30)
	private String status;                  // 결재 상태 (작성중, 승인 등)

	// Constructor
	public DocumentDTO() {
		super();
	}

	public DocumentDTO(String documentId, String documentTypeId, String documentName, String transactionType,
			String clientId, String clientName, String documentWriter, String documentManagerId, String title,
			String remarks, Date createdDate, Date documentDate, String paymentMethod, String status) {
		super();
		this.documentId = documentId;
		this.documentTypeId = documentTypeId;
		this.documentName = documentName;
		this.transactionType = transactionType;
		this.clientId = clientId;
		this.clientName = clientName;
		this.documentWriter = documentWriter;
		this.documentManagerId = documentManagerId;
		this.title = title;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.documentDate = documentDate;
		this.paymentMethod = paymentMethod;
		this.status = status;
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDocumentWriter() {
		return documentWriter;
	}

	public void setDocumentWriter(String documentWriter) {
		this.documentWriter = documentWriter;
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

	public java.sql.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.sql.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.sql.Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(java.sql.Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DocumentDTO [documentId=" + documentId + ", documentTypeId=" + documentTypeId + ", documentName="
				+ documentName + ", transactionType=" + transactionType + ", clientId=" + clientId + ", clientName="
				+ clientName + ", documentWriter=" + documentWriter + ", documentManagerId=" + documentManagerId
				+ ", title=" + title + ", remarks=" + remarks + ", createdDate=" + createdDate + ", documentDate="
				+ documentDate + ", paymentMethod=" + paymentMethod + ", status=" + status + "]";
	}
}
