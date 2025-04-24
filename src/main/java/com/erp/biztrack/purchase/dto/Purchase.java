package com.erp.biztrack.purchase.dto;

import java.sql.Date;

public class Purchase {

	private String purchaseId; 
	private String documentId; 
	private String productCode; 
	private int purchaseQuantity; 
	private String vendorName; 
	private Date quoteDate; 
	private Date invoiceDate;
	
	public Purchase() {
		super();
	}

	public Purchase(String purchaseId, String documentId, String productCode, int purchaseQuantity,
			String vendorName, Date quoteDate, Date invoiceDate) {
		super();
		this.purchaseId = purchaseId;
		this.documentId = documentId;
		this.productCode = productCode;
		this.purchaseQuantity = purchaseQuantity;
		this.vendorName = vendorName;
		this.quoteDate = quoteDate;
		this.invoiceDate = invoiceDate;
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

	@Override
	public String toString() {
		return "PurchaseDto [purchaseId=" + purchaseId + ", documentId=" + documentId + ", productCode=" + productCode
				+ ", purchaseQuantity=" + purchaseQuantity + ", vendorName=" + vendorName + ", quoteDate=" + quoteDate
				+ ", invoiceDate=" + invoiceDate + "]";
	}	
	
	
	
}
