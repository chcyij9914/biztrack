package com.erp.biztrack.client.model.dto;

import java.sql.Date;

public class Client implements java.io.Serializable{
	private static final long serialVersionUID = -8641928397507077733L;
	
	//Filed
	private String clientId;							//	CLIENT_ID	VARCHAR2(30 BYTE)
	private String clientName;						//	CLIENT_NAME	VARCHAR2(50 BYTE)
	private String ceoName;						//	CEO_NAME	VARCHAR2(30 BYTE)
	private String businessNumber;				//	BUSINESS_NUMBER	VARCHAR2(50 BYTE)
	private String productCode;					//	PRODUCT_CODE	VARCHAR2(30 BYTE)
	private String categoryName; 				// CATEGORY_NAME	VARCHAR2(30 BYTE) 상품명
	private String companyPhone;				//	COMPANY_PHONE	VARCHAR2(20 BYTE)
	private String fax;									//	FAX	VARCHAR2(30 BYTE)
	private String address;							//	ADDRESS	VARCHAR2(200 BYTE)
	private String url;									//	URL	VARCHAR2(300 BYTE)
	private String clientStatus;						//	CLIENT_STATUS	VARCHAR2(50 BYTE)
	private java.sql.Date contractStartDate;	//	CONTRACT_START_DATE	DATE
	private java.sql.Date contractEndDate;	//	CONTRACT_END_DATE	DATE
	private String firstManagerId;					//	FIRST_MANAGER_ID	VARCHAR2(30 BYTE)
	private String firstManagerName;  			// 최초 계약자명
	private String firstManagerJob;				// 최초 계약자 담당자 직급
	private String currentManagerId;				//	CURRENT_MANAGER_ID	VARCHAR2(30 BYTE)
	private String currentManagerName; 		// 현재 관리 담당자명
	private String currentManagerJob;			// 현재 관리 담담자 직급
	private String directorName;					//	DIRECTOR_NAME	VARCHAR2(30 BYTE)
	private String directorPhone;					//	DIRECTOR_PHONE	VARCHAR2(20 BYTE)
	private String email;								//	EMAIL	VARCHAR2(100 BYTE)
	
	//Constructor
	public Client() {
		super();
	}

	public Client(String clientId, String clientName, String ceoName, String businessNumber, String productCode,
			String categoryName, String companyPhone, String fax, String address, String url, String clientStatus,
			Date contractStartDate, Date contractEndDate, String firstManagerId, String firstManagerName,
			String firstManagerJob, String currentManagerId, String currentManagerName, String currentManagerJob,
			String directorName, String directorPhone, String email) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.ceoName = ceoName;
		this.businessNumber = businessNumber;
		this.productCode = productCode;
		this.categoryName = categoryName;
		this.companyPhone = companyPhone;
		this.fax = fax;
		this.address = address;
		this.url = url;
		this.clientStatus = clientStatus;
		this.contractStartDate = contractStartDate;
		this.contractEndDate = contractEndDate;
		this.firstManagerId = firstManagerId;
		this.firstManagerName = firstManagerName;
		this.firstManagerJob = firstManagerJob;
		this.currentManagerId = currentManagerId;
		this.currentManagerName = currentManagerName;
		this.currentManagerJob = currentManagerJob;
		this.directorName = directorName;
		this.directorPhone = directorPhone;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", ceoName=" + ceoName
				+ ", businessNumber=" + businessNumber + ", productCode=" + productCode + ", categoryName="
				+ categoryName + ", companyPhone=" + companyPhone + ", fax=" + fax + ", address=" + address + ", url="
				+ url + ", clientStatus=" + clientStatus + ", contractStartDate=" + contractStartDate
				+ ", contractEndDate=" + contractEndDate + ", firstManagerId=" + firstManagerId + ", firstManagerName="
				+ firstManagerName + ", firstManagerJob=" + firstManagerJob + ", currentManagerId=" + currentManagerId
				+ ", currentManagerName=" + currentManagerName + ", currentManagerJob=" + currentManagerJob
				+ ", directorName=" + directorName + ", directorPhone=" + directorPhone + ", email=" + email + "]";
	}

	//getters and setters
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public java.sql.Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(java.sql.Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public java.sql.Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(java.sql.Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getFirstManagerId() {
		return firstManagerId;
	}

	public void setFirstManagerId(String firstManagerId) {
		this.firstManagerId = firstManagerId;
	}

	public String getFirstManagerName() {
		return firstManagerName;
	}

	public void setFirstManagerName(String firstManagerName) {
		this.firstManagerName = firstManagerName;
	}

	public String getFirstManagerJob() {
		return firstManagerJob;
	}

	public void setFirstManagerJob(String firstManagerJob) {
		this.firstManagerJob = firstManagerJob;
	}

	public String getCurrentManagerId() {
		return currentManagerId;
	}

	public void setCurrentManagerId(String currentManagerId) {
		this.currentManagerId = currentManagerId;
	}

	public String getCurrentManagerName() {
		return currentManagerName;
	}

	public void setCurrentManagerName(String currentManagerName) {
		this.currentManagerName = currentManagerName;
	}

	public String getCurrentManagerJob() {
		return currentManagerJob;
	}

	public void setCurrentManagerJob(String currentManagerJob) {
		this.currentManagerJob = currentManagerJob;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
