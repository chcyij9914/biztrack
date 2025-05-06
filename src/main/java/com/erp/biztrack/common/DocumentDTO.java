package com.erp.biztrack.common;

import java.sql.Date;

public class DocumentDTO implements java.io.Serializable {
	private static final long serialVersionUID = -7970793635367626682L;

	private String documentId; 					// DOCUMENT_ID VARCHAR2(30 BYTE)
	private String documentType; 				// DOCUMENT_TYPE VARCHAR2(30 BYTE)
	private String clientId; 						// CLIENT_ID VARCHAR2(30 BYTE)
	private String clientName; 					// CLIENT_NAME VARCHAR2(30 BYTE)
	private String documentWriter; 				// DOCUMENT_WRITER_ID VARCHAR2(30 BYTE)
	private String documentManagerId; 		// DOCUMENT_MANAGER_ID VARCHAR2(30 BYTE)
	private String title;								//	TITLE	VARCHAR2(100 BYTE)
	private String remarks;						//	REMARKS	VARCHAR2(4000 BYTE)
	private java.sql.Date createdDate;			//	CREATED_DATE	DATE
	private String status;  						// 문서 결재 상태 (작성중, 반려됨 등)

	// Constructor
	public DocumentDTO() {
		super();
	}
	
	public DocumentDTO(String documentId, String documentType, String clientId, String clientName,
			String documentWriter, String documentManagerId, String title, String remarks, Date createdDate,
			String status) {
		super();
		this.documentId = documentId;
		this.documentType = documentType;
		this.clientId = clientId;
		this.clientName = clientName;
		this.documentWriter = documentWriter;
		this.documentManagerId = documentManagerId;
		this.title = title;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.status = status;
	}

	// getters and setters
	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
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
		return "DocumentDTO [documentId=" + documentId + ", documentType=" + documentType + ", clientId=" + clientId
				+ ", clientName=" + clientName + ", documentWriter=" + documentWriter + ", documentManagerId="
				+ documentManagerId + ", title=" + title + ", remarks=" + remarks + ", createdDate=" + createdDate
				+ ", status=" + status + "]";
	}
}
