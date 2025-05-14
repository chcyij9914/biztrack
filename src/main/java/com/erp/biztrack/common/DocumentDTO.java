package com.erp.biztrack.common;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DocumentDTO implements java.io.Serializable {
	private static final long serialVersionUID = -7970793635367626682L;

	private String documentId;              			// DOCUMENT_ID VARCHAR2(30)
	private String documentTypeId;          		// DOCUMENT_TYPE_ID VARCHAR2(30)
	private String documentName;           	 	// DOCUMENT_NAME (조인된 문서 이름)
	private String transactionType;         			// TRANSACTION_TYPE (출고, 입고, 구매 등)
	private String clientId;                				// CLIENT_ID VARCHAR2(30)
	private String clientName;              			// CLIENT_NAME VARCHAR2(30)
	private String documentWriterId;          		// DOCUMENT_WRITER_ID VARCHAR2(30)
	private String documentWriterJobTitle;
	private String documentWriterName;
	private String documentManagerId;       		// DOCUMENT_MANAGER_ID VARCHAR2(30)
	private String documentManagerJobTitle;
	private String documentManagerName;
	private String title;                   				// TITLE VARCHAR2(100)
	private String remarks;                 			// REMARKS VARCHAR2(1000)
	private java.sql.Date createdDate;      			// CREATED_DATE DATE
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private java.sql.Date documentDate;     		// DOCUMENT_DATE DATE
	private String paymentMethod;           		// PAYMENT_METHOD VARCHAR2(30)
	private String status;                 		 		// 결재 상태 (작성중, 승인 등)
	private List<DocumentItemDTO> items;
	private Integer totalAmount;						//총합계
	private java.sql.Date updatedDate;				//수정일
	private String connectDocumentId;      //   CONNECT_DOCUMENT_ID   VARCHAR2(30 BYTE)
	private String deptId;  // 로그인 사용자의 부서 ID (조회 조건 전달용)

	// Constructor
	public DocumentDTO() {
		super();
	}

	public DocumentDTO(String documentId, String documentTypeId, String documentName, String transactionType,
			String clientId, String clientName, String documentWriterId, String documentWriterJobTitle,
			String documentWriterName, String documentManagerId, String documentManagerJobTitle,
			String documentManagerName, String title, String remarks, Date createdDate, Date documentDate,
			String paymentMethod, String status, List<DocumentItemDTO> items, Integer totalAmount, Date updatedDate,
			String connectDocumentId, String deptId) {
		super();
		this.documentId = documentId;
		this.documentTypeId = documentTypeId;
		this.documentName = documentName;
		this.transactionType = transactionType;
		this.clientId = clientId;
		this.clientName = clientName;
		this.documentWriterId = documentWriterId;
		this.documentWriterJobTitle = documentWriterJobTitle;
		this.documentWriterName = documentWriterName;
		this.documentManagerId = documentManagerId;
		this.documentManagerJobTitle = documentManagerJobTitle;
		this.documentManagerName = documentManagerName;
		this.title = title;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.documentDate = documentDate;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.items = items;
		this.totalAmount = totalAmount;
		this.updatedDate = updatedDate;
		this.connectDocumentId = connectDocumentId;
		this.deptId = deptId;
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

	public String getDocumentWriterId() {
		return documentWriterId;
	}

	public void setDocumentWriterId(String documentWriterId) {
		this.documentWriterId = documentWriterId;
	}

	public String getDocumentWriterJobTitle() {
		return documentWriterJobTitle;
	}

	public void setDocumentWriterJobTitle(String documentWriterJobTitle) {
		this.documentWriterJobTitle = documentWriterJobTitle;
	}

	public String getDocumentWriterName() {
		return documentWriterName;
	}

	public void setDocumentWriterName(String documentWriterName) {
		this.documentWriterName = documentWriterName;
	}

	public String getDocumentManagerId() {
		return documentManagerId;
	}

	public void setDocumentManagerId(String documentManagerId) {
		this.documentManagerId = documentManagerId;
	}

	public String getDocumentManagerJobTitle() {
		return documentManagerJobTitle;
	}

	public void setDocumentManagerJobTitle(String documentManagerJobTitle) {
		this.documentManagerJobTitle = documentManagerJobTitle;
	}

	public String getDocumentManagerName() {
		return documentManagerName;
	}

	public void setDocumentManagerName(String documentManagerName) {
		this.documentManagerName = documentManagerName;
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

	public List<DocumentItemDTO> getItems() {
		return items;
	}

	public void setItems(List<DocumentItemDTO> items) {
		this.items = items;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public java.sql.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.sql.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getConnectDocumentId() {
		return connectDocumentId;
	}

	public void setConnectDocumentId(String connectDocumentId) {
		this.connectDocumentId = connectDocumentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "DocumentDTO [documentId=" + documentId + ", documentTypeId=" + documentTypeId + ", documentName="
				+ documentName + ", transactionType=" + transactionType + ", clientId=" + clientId + ", clientName="
				+ clientName + ", documentWriterId=" + documentWriterId + ", documentWriterJobTitle="
				+ documentWriterJobTitle + ", documentWriterName=" + documentWriterName + ", documentManagerId="
				+ documentManagerId + ", documentManagerJobTitle=" + documentManagerJobTitle + ", documentManagerName="
				+ documentManagerName + ", title=" + title + ", remarks=" + remarks + ", createdDate=" + createdDate
				+ ", documentDate=" + documentDate + ", paymentMethod=" + paymentMethod + ", status=" + status
				+ ", items=" + items + ", totalAmount=" + totalAmount + ", updatedDate=" + updatedDate
				+ ", connectDocumentId=" + connectDocumentId + ", deptId=" + deptId + "]";
	}
}
