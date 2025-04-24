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
	private String companyPhone;				//	COMPANY_PHONE	VARCHAR2(20 BYTE)
	private String fax;									//	FAX	VARCHAR2(30 BYTE)
	private String address;							//	ADDRESS	VARCHAR2(200 BYTE)
	private String url;									//	URL	VARCHAR2(300 BYTE)
	private String clientStatus;						//	CLIENT_STATUS	VARCHAR2(50 BYTE)
	private java.sql.Date contractStartDate;	//	CONTRACT_START_DATE	DATE
	private java.sql.Date conractEndDate;		//	CONTRACT_END_DATE	DATE
	private String firstManagerId;					//	FIRST_MANAGER_ID	VARCHAR2(30 BYTE)
	private String currentManagerId;				//	CURRENT_MANAGER_ID	VARCHAR2(30 BYTE)
	private String directorName;					//	DIRECTOR_NAME	VARCHAR2(30 BYTE)
	private String directorPhone;					//	DIRECTOR_PHONE	VARCHAR2(20 BYTE)
	private String email;								//	EMAIL	VARCHAR2(100 BYTE)
	
	//Constructor
	public Client() {
		super();
	}

	public Client(String clientId, String clientName, String ceoName, String businessNumber, String productCode,
			String companyPhone, String fax, String address, String url, String clientStatus, Date contractStartDate,
			Date conractEndDate, String firstManagerId, String currentManagerId, String directorName,
			String directorPhone, String email) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.ceoName = ceoName;
		this.businessNumber = businessNumber;
		this.productCode = productCode;
		this.companyPhone = companyPhone;
		this.fax = fax;
		this.address = address;
		this.url = url;
		this.clientStatus = clientStatus;
		this.contractStartDate = contractStartDate;
		this.conractEndDate = conractEndDate;
		this.firstManagerId = firstManagerId;
		this.currentManagerId = currentManagerId;
		this.directorName = directorName;
		this.directorPhone = directorPhone;
		this.email = email;
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

	public java.sql.Date getConractEndDate() {
		return conractEndDate;
	}

	public void setConractEndDate(java.sql.Date conractEndDate) {
		this.conractEndDate = conractEndDate;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", ceoName=" + ceoName
				+ ", businessNumber=" + businessNumber + ", productCode=" + productCode + ", companyPhone="
				+ companyPhone + ", fax=" + fax + ", address=" + address + ", url=" + url + ", clientStatus="
				+ clientStatus + ", contractStartDate=" + contractStartDate + ", conractEndDate=" + conractEndDate
				+ ", firstManagerId=" + firstManagerId + ", currentManagerId=" + currentManagerId + ", directorName="
				+ directorName + ", directorPhone=" + directorPhone + ", email=" + email + "]";
	}	
}
