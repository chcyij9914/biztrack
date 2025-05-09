package com.erp.biztrack.common;

import java.sql.Date;

public class ApproveDTO implements java.io.Serializable {
	private static final long serialVersionUID = 3387935404644815074L;
	
	private String approveId;										//	APPROVE_ID	VARCHAR2(30 BYTE)
	private String documentId;										//	DOCUMENT_ID	VARCHAR2(30 BYTE)
	private String empId;												//	EMP_ID	VARCHAR2(30 BYTE)
	private String firstApproverId;									//	APPROVER1_ID	VARCHAR2(30 BYTE)
	private java.sql.Date firstApproveDate;					//	APPROVE1_DATE	DATE
	private String firstApproveStatus;							//	APPROVE1_STATUS	VARCHAR2(30 BYTE)
	private String secondApproverId;							//	APPROVER2_ID	VARCHAR2(30 BYTE)
	private java.sql.Date secondApproveDate;				//	APPROVE2_DATE	DATE
	private String secondApproveStatus;						//	APPROVE2_STATUS	VARCHAR2(30 BYTE)
	private String documentTitle;
	private String firstApproverName;
	private String secondApproverName;
	private String firstApproverRoleName;
	private String secondApproverRoleName;
	
	public ApproveDTO() {
		super();
	}
	
	public ApproveDTO(String approveId, String documentId, String empId, String firstApproverId, Date firstApproveDate,
			String firstApproveStatus, String secondApproverId, Date secondApproveDate, String secondApproveStatus,
			String documentTitle, String firstApproverName, String secondApproverName, String firstApproverRoleName,
			String secondApproverRoleName) {
		super();
		this.approveId = approveId;
		this.documentId = documentId;
		this.empId = empId;
		this.firstApproverId = firstApproverId;
		this.firstApproveDate = firstApproveDate;
		this.firstApproveStatus = firstApproveStatus;
		this.secondApproverId = secondApproverId;
		this.secondApproveDate = secondApproveDate;
		this.secondApproveStatus = secondApproveStatus;
		this.documentTitle = documentTitle;
		this.firstApproverName = firstApproverName;
		this.secondApproverName = secondApproverName;
		this.firstApproverRoleName = firstApproverRoleName;
		this.secondApproverRoleName = secondApproverRoleName;
	}
	
	//getter and setters
	public String getApproveId() {
		return approveId;
	}
	
	public void setApproveId(String approveId) {
		this.approveId = approveId;
	}
	
	public String getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	public String getEmpId() {
		return empId;
	}
	
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public String getFirstApproverId() {
		return firstApproverId;
	}
	
	public void setFirstApproverId(String firstApproverId) {
		this.firstApproverId = firstApproverId;
	}
	
	public java.sql.Date getFirstApproveDate() {
		return firstApproveDate;
	}
	
	public void setFirstApproveDate(java.sql.Date firstApproveDate) {
		this.firstApproveDate = firstApproveDate;
	}
	
	public String getFirstApproveStatus() {
		return firstApproveStatus;
	}
	
	public void setFirstApproveStatus(String firstApproveStatus) {
		this.firstApproveStatus = firstApproveStatus;
	}
	
	public String getSecondApproverId() {
		return secondApproverId;
	}
	
	public void setSecondApproverId(String secondApproverId) {
		this.secondApproverId = secondApproverId;
	}
	
	public java.sql.Date getSecondApproveDate() {
		return secondApproveDate;
	}
	
	public void setSecondApproveDate(java.sql.Date secondApproveDate) {
		this.secondApproveDate = secondApproveDate;
	}
	
	public String getSecondApproveStatus() {
		return secondApproveStatus;
	}
	
	public void setSecondApproveStatus(String secondApproveStatus) {
		this.secondApproveStatus = secondApproveStatus;
	}
	
	public String getDocumentTitle() {
		return documentTitle;
	}
	
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	
	public String getFirstApproverName() {
		return firstApproverName;
	}
	
	public void setFirstApproverName(String firstApproverName) {
		this.firstApproverName = firstApproverName;
	}
	
	public String getSecondApproverName() {
		return secondApproverName;
	}
	
	public void setSecondApproverName(String secondApproverName) {
		this.secondApproverName = secondApproverName;
	}
	
	public String getFirstApproverRoleName() {
		return firstApproverRoleName;
	}
	
	public void setFirstApproverRoleName(String firstApproverRoleName) {
		this.firstApproverRoleName = firstApproverRoleName;
	}
	
	public String getSecondApproverRoleName() {
		return secondApproverRoleName;
	}
	
	public void setSecondApproverRoleName(String secondApproverRoleName) {
		this.secondApproverRoleName = secondApproverRoleName;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "ApproveDTO [approveId=" + approveId + ", documentId=" + documentId + ", empId=" + empId
				+ ", firstApproverId=" + firstApproverId + ", firstApproveDate=" + firstApproveDate
				+ ", firstApproveStatus=" + firstApproveStatus + ", secondApproverId=" + secondApproverId
				+ ", secondApproveDate=" + secondApproveDate + ", secondApproveStatus=" + secondApproveStatus
				+ ", documentTitle=" + documentTitle + ", firstApproverName=" + firstApproverName + ", secondApproverName="
				+ secondApproverName + ", firstApproverRoleName=" + firstApproverRoleName + ", secondApproverRoleName="
				+ secondApproverRoleName + "]";
	}
}
