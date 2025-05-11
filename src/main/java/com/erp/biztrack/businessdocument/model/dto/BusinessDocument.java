package com.erp.biztrack.businessdocument.model.dto;

import java.sql.Date;

public class BusinessDocument implements java.io.Serializable{
	private static final long serialVersionUID = 3017689070795292403L;

	// Filed
	private String documentId;           // 문서 ID (예: O1, G1 등)
    private String documentTypeId;       // 문서 유형 (예: 'O' - 출고서, 'G' - 세금계산서)
    private String clientId;             // 거래처 ID
    private String clientName;           // 거래처명 (확장용)
    private String documentWriterId;     // 문서 작성자 ID (직원 사번)
    private String documentManagerId;    // 문서 담당자 ID (직원 사번)
    private String title;                // 문서 제목
    private String remarks;              // 비고 / 기타 사항
    private Date createdDate;            // 작성일
    private Date documentDate;           // 문서 기준일 (출고일, 계산일 등)
    private String paymentMethod;        // 결제수단 (예: 카드, 현금, 계좌이체)
    private String status;               // 문서 상태 (작성중, 승인대기, 승인완료 등)

    // 품목 정보도 포함 (단일 품목 기준)
    private String itemId;               // 품목 ID (예: DP1)
    private String productId;            // 상품 ID
    private String productName;          // 상품명 (확장용)
    private int quantity;                // 수량
    private Integer unitPrice;           // 단가 (세금계산서 전용)

    // 결재 관련 필드
    private String approver1Id;          // 1차 결재자 ID
    private String approver2Id;          // 2차 결재자 ID
    private String approve1Status;       // 1차 결재 상태 ('결재 대기', '승인', '반려')
    private String approve2Status;       // 2차 결재 상태
    private Date approve1Date;           // 1차 결재일
    private Date approve2Date;           // 2차 결재일
    
    // 세금계산서(Tax Invoice) 전용 필드 
    private java.sql.Date invoiceDate;        // 세금계산서 발행일
    private String supplierName;              // 공급자명 (우리 회사)
    private String supplierBusinessNumber;    // 공급자 사업자번호
    private String clientBusinessNumber;      // 거래처 사업자번호
    private String businessType;              // 업태
    private String businessItem;              // 종목
    private int totalAmount;                  // 공급가액 (세전 금액)
    private int vatAmount;                    // 부가세
    private int totalWithVat;                 // 합계금액 (세전 + 세금)
    private String representativeName;        // 대표자명
    private String address;                   // 주소
    private String email;                     // 이메일
	
    
    // 생성자
    public BusinessDocument() {
		super();
	}


	public BusinessDocument(String documentId, String documentTypeId, String clientId, String clientName,
			String documentWriterId, String documentManagerId, String title, String remarks, Date createdDate,
			Date documentDate, String paymentMethod, String status, String itemId, String productId, String productName,
			int quantity, Integer unitPrice, String approver1Id, String approver2Id, String approve1Status,
			String approve2Status, Date approve1Date, Date approve2Date, Date invoiceDate, String supplierName,
			String supplierBusinessNumber, String clientBusinessNumber, String businessType, String businessItem,
			int totalAmount, int vatAmount, int totalWithVat, String representativeName, String address, String email) {
		super();
		this.documentId = documentId;
		this.documentTypeId = documentTypeId;
		this.clientId = clientId;
		this.clientName = clientName;
		this.documentWriterId = documentWriterId;
		this.documentManagerId = documentManagerId;
		this.title = title;
		this.remarks = remarks;
		this.createdDate = createdDate;
		this.documentDate = documentDate;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.itemId = itemId;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.approver1Id = approver1Id;
		this.approver2Id = approver2Id;
		this.approve1Status = approve1Status;
		this.approve2Status = approve2Status;
		this.approve1Date = approve1Date;
		this.approve2Date = approve2Date;
		this.invoiceDate = invoiceDate;
		this.supplierName = supplierName;
		this.supplierBusinessNumber = supplierBusinessNumber;
		this.clientBusinessNumber = clientBusinessNumber;
		this.businessType = businessType;
		this.businessItem = businessItem;
		this.totalAmount = totalAmount;
		this.vatAmount = vatAmount;
		this.totalWithVat = totalWithVat;
		this.representativeName = representativeName;
		this.address = address;
		this.email = email;
	}


	// getters and setters
	
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Integer getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getApprover1Id() {
		return approver1Id;
	}


	public void setApprover1Id(String approver1Id) {
		this.approver1Id = approver1Id;
	}


	public String getApprover2Id() {
		return approver2Id;
	}


	public void setApprover2Id(String approver2Id) {
		this.approver2Id = approver2Id;
	}


	public String getApprove1Status() {
		return approve1Status;
	}


	public void setApprove1Status(String approve1Status) {
		this.approve1Status = approve1Status;
	}


	public String getApprove2Status() {
		return approve2Status;
	}


	public void setApprove2Status(String approve2Status) {
		this.approve2Status = approve2Status;
	}


	public Date getApprove1Date() {
		return approve1Date;
	}


	public void setApprove1Date(Date approve1Date) {
		this.approve1Date = approve1Date;
	}


	public Date getApprove2Date() {
		return approve2Date;
	}


	public void setApprove2Date(Date approve2Date) {
		this.approve2Date = approve2Date;
	}


	public java.sql.Date getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(java.sql.Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getSupplierBusinessNumber() {
		return supplierBusinessNumber;
	}


	public void setSupplierBusinessNumber(String supplierBusinessNumber) {
		this.supplierBusinessNumber = supplierBusinessNumber;
	}


	public String getClientBusinessNumber() {
		return clientBusinessNumber;
	}


	public void setClientBusinessNumber(String clientBusinessNumber) {
		this.clientBusinessNumber = clientBusinessNumber;
	}


	public String getBusinessType() {
		return businessType;
	}


	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}


	public String getBusinessItem() {
		return businessItem;
	}


	public void setBusinessItem(String businessItem) {
		this.businessItem = businessItem;
	}


	public int getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}


	public int getVatAmount() {
		return vatAmount;
	}


	public void setVatAmount(int vatAmount) {
		this.vatAmount = vatAmount;
	}


	public int getTotalWithVat() {
		return totalWithVat;
	}


	public void setTotalWithVat(int totalWithVat) {
		this.totalWithVat = totalWithVat;
	}


	public String getRepresentativeName() {
		return representativeName;
	}


	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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
		return "BusinessDocument [documentId=" + documentId + ", documentTypeId=" + documentTypeId + ", clientId="
				+ clientId + ", clientName=" + clientName + ", documentWriterId=" + documentWriterId
				+ ", documentManagerId=" + documentManagerId + ", title=" + title + ", remarks=" + remarks
				+ ", createdDate=" + createdDate + ", documentDate=" + documentDate + ", paymentMethod=" + paymentMethod
				+ ", status=" + status + ", itemId=" + itemId + ", productId=" + productId + ", productName="
				+ productName + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", approver1Id=" + approver1Id
				+ ", approver2Id=" + approver2Id + ", approve1Status=" + approve1Status + ", approve2Status="
				+ approve2Status + ", approve1Date=" + approve1Date + ", approve2Date=" + approve2Date
				+ ", invoiceDate=" + invoiceDate + ", supplierName=" + supplierName + ", supplierBusinessNumber="
				+ supplierBusinessNumber + ", clientBusinessNumber=" + clientBusinessNumber + ", businessType="
				+ businessType + ", businessItem=" + businessItem + ", totalAmount=" + totalAmount + ", vatAmount="
				+ vatAmount + ", totalWithVat=" + totalWithVat + ", representativeName=" + representativeName
				+ ", address=" + address + ", email=" + email + "]";
	}
}
