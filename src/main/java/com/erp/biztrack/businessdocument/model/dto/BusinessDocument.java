package com.erp.biztrack.businessdocument.model.dto;

import java.sql.Date;
import java.util.List;

import com.erp.biztrack.common.DocumentItemDTO;

public class BusinessDocument implements java.io.Serializable {
    private static final long serialVersionUID = 3017689070795292403L;

    // [1] 공통 문서 정보
    private String documentId;
    private String documentTypeId;
    private String title;
    private String remarks;
    private Date createdDate;
    private Date documentDate;
    private String paymentMethod;
    private String status;

    // [2] 거래처 정보
    private String clientId;
    private String clientName;
    private String clientBusinessNumber;
    private String representativeName;
    private String address;
    private String email;

    // [3] 작성자 / 담당자 정보
    private String documentWriterId;
    private String documentManagerId;
    private String writerName;

    // [4] 출고서 전용 필드
    private String itemId;
    private String productId;
    private String productName;
    private int quantity;
    private int productQuantity;
    private int outboundTotalAmount;

    // [5] 세금계산서 전용 필드
    private java.sql.Date invoiceDate;
    private String supplierName;
    private String supplierBusinessNumber;
    private String businessType;
    private String businessItem;
    private Integer unitPrice;
    private int totalAmount;
    private int vatAmount;
    private int totalWithVat;

    // [6] 결재 정보
    private String approver1Id;
    private String approver2Id;
    private String approve1Status;
    private String approve2Status;
    private Date approve1Date;
    private Date approve2Date;

    // [7] 출고서 품목 리스트
    private List<DocumentItemDTO> items;

    // [8] 결재자 리스트
    private List<BusinessDocument> approvers;

    // [9] 결재자 개별 정보 (JSP 출력용)
    private String empId;
    private String rank;
    private String position;
    private String deptName;
    private int level;

    public BusinessDocument() {}

    // === Getter/Setter ===
    public String getDocumentId() { return documentId; }
    public void setDocumentId(String documentId) { this.documentId = documentId; }

    public String getDocumentTypeId() { return documentTypeId; }
    public void setDocumentTypeId(String documentTypeId) { this.documentTypeId = documentTypeId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public Date getDocumentDate() { return documentDate; }
    public void setDocumentDate(Date documentDate) { this.documentDate = documentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getClientBusinessNumber() { return clientBusinessNumber; }
    public void setClientBusinessNumber(String clientBusinessNumber) { this.clientBusinessNumber = clientBusinessNumber; }

    public String getRepresentativeName() { return representativeName; }
    public void setRepresentativeName(String representativeName) { this.representativeName = representativeName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDocumentWriterId() { return documentWriterId; }
    public void setDocumentWriterId(String documentWriterId) { this.documentWriterId = documentWriterId; }

    public String getDocumentManagerId() { return documentManagerId; }
    public void setDocumentManagerId(String documentManagerId) { this.documentManagerId = documentManagerId; }

    public String getWriterName() { return writerName; }
    public void setWriterName(String writerName) { this.writerName = writerName; }

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getProductQuantity() { return productQuantity; }
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }

    public int getOutboundTotalAmount() { return outboundTotalAmount; }
    public void setOutboundTotalAmount(int outboundTotalAmount) { this.outboundTotalAmount = outboundTotalAmount; }

    public java.sql.Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(java.sql.Date invoiceDate) { this.invoiceDate = invoiceDate; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getSupplierBusinessNumber() { return supplierBusinessNumber; }
    public void setSupplierBusinessNumber(String supplierBusinessNumber) { this.supplierBusinessNumber = supplierBusinessNumber; }

    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }

    public String getBusinessItem() { return businessItem; }
    public void setBusinessItem(String businessItem) { this.businessItem = businessItem; }

    public Integer getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Integer unitPrice) { this.unitPrice = unitPrice; }

    public int getTotalAmount() { return totalAmount; }
    public void setTotalAmount(int totalAmount) { this.totalAmount = totalAmount; }

    public int getVatAmount() { return vatAmount; }
    public void setVatAmount(int vatAmount) { this.vatAmount = vatAmount; }

    public int getTotalWithVat() { return totalWithVat; }
    public void setTotalWithVat(int totalWithVat) { this.totalWithVat = totalWithVat; }

    public String getApprover1Id() { return approver1Id; }
    public void setApprover1Id(String approver1Id) { this.approver1Id = approver1Id; }

    public String getApprover2Id() { return approver2Id; }
    public void setApprover2Id(String approver2Id) { this.approver2Id = approver2Id; }

    public String getApprove1Status() { return approve1Status; }
    public void setApprove1Status(String approve1Status) { this.approve1Status = approve1Status; }

    public String getApprove2Status() { return approve2Status; }
    public void setApprove2Status(String approve2Status) { this.approve2Status = approve2Status; }

    public Date getApprove1Date() { return approve1Date; }
    public void setApprove1Date(Date approve1Date) { this.approve1Date = approve1Date; }

    public Date getApprove2Date() { return approve2Date; }
    public void setApprove2Date(Date approve2Date) { this.approve2Date = approve2Date; }

    public List<DocumentItemDTO> getItems() { return items; }
    public void setItems(List<DocumentItemDTO> items) { this.items = items; }

    public List<BusinessDocument> getApprovers() { return approvers; }
    public void setApprovers(List<BusinessDocument> approvers) { this.approvers = approvers; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public static long getSerialversionuid() { return serialVersionUID; }

    @Override
    public String toString() {
        return "BusinessDocument [documentId=" + documentId
            + ", documentTypeId=" + documentTypeId
            + ", title=" + title
            + ", remarks=" + remarks
            + ", createdDate=" + createdDate
            + ", documentDate=" + documentDate
            + ", paymentMethod=" + paymentMethod
            + ", status=" + status
            + ", clientId=" + clientId
            + ", clientName=" + clientName
            + ", clientBusinessNumber=" + clientBusinessNumber
            + ", representativeName=" + representativeName
            + ", address=" + address
            + ", email=" + email
            + ", documentWriterId=" + documentWriterId
            + ", documentManagerId=" + documentManagerId
            + ", writerName=" + writerName
            + ", itemId=" + itemId
            + ", productId=" + productId
            + ", productName=" + productName
            + ", quantity=" + quantity
            + ", productQuantity=" + productQuantity
            + ", outboundTotalAmount=" + outboundTotalAmount
            + ", invoiceDate=" + invoiceDate
            + ", supplierName=" + supplierName
            + ", supplierBusinessNumber=" + supplierBusinessNumber
            + ", businessType=" + businessType
            + ", businessItem=" + businessItem
            + ", unitPrice=" + unitPrice
            + ", totalAmount=" + totalAmount
            + ", vatAmount=" + vatAmount
            + ", totalWithVat=" + totalWithVat
            + ", approver1Id=" + approver1Id
            + ", approver2Id=" + approver2Id
            + ", approve1Status=" + approve1Status
            + ", approve2Status=" + approve2Status
            + ", approve1Date=" + approve1Date
            + ", approve2Date=" + approve2Date
            + ", empId=" + empId
            + ", rank=" + rank
            + ", position=" + position
            + ", deptName=" + deptName
            + ", level=" + level
            + "]";
    }
}
