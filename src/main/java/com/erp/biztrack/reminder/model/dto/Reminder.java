package com.erp.biztrack.reminder.model.dto;

import java.sql.Date;

public class Reminder implements java.io.Serializable{
	private static final long serialVersionUID = 6955573652849944482L;
	
		// Field
	    private String productName;         	// 물품명
	    private String clientName;          		// 거래처명
	    private String clientPhone;         		// 거래처번호
	    private String clientAddress;       		// 거래처 주소
	    private int purchaseQuantity;       	// 구매 수량
	    private int transactionCount;       	// 거래 건수
	    private String receiptConfirmation; 	// 수신 확인 여부 (Y/N)
	    private Date reminderDate;          	// 리마인더 생성일
		
	
	    // Constructor
	    public Reminder() {
			super();
		}


		public Reminder(String productName, String clientName, String clientPhone, String clientAddress,
				int purchaseQuantity, int transactionCount, String receiptConfirmation, Date reminderDate) {
			super();
			this.productName = productName;
			this.clientName = clientName;
			this.clientPhone = clientPhone;
			this.clientAddress = clientAddress;
			this.purchaseQuantity = purchaseQuantity;
			this.transactionCount = transactionCount;
			this.receiptConfirmation = receiptConfirmation;
			this.reminderDate = reminderDate;
		}

		 // Getters and Setters
		public String getProductName() {
			return productName;
		}


		public void setProductName(String productName) {
			this.productName = productName;
		}


		public String getClientName() {
			return clientName;
		}


		public void setClientName(String clientName) {
			this.clientName = clientName;
		}


		public String getClientPhone() {
			return clientPhone;
		}


		public void setClientPhone(String clientPhone) {
			this.clientPhone = clientPhone;
		}


		public String getClientAddress() {
			return clientAddress;
		}


		public void setClientAddress(String clientAddress) {
			this.clientAddress = clientAddress;
		}


		public int getPurchaseQuantity() {
			return purchaseQuantity;
		}


		public void setPurchaseQuantity(int purchaseQuantity) {
			this.purchaseQuantity = purchaseQuantity;
		}


		public int getTransactionCount() {
			return transactionCount;
		}


		public void setTransactionCount(int transactionCount) {
			this.transactionCount = transactionCount;
		}


		public String getReceiptConfirmation() {
			return receiptConfirmation;
		}


		public void setReceiptConfirmation(String receiptConfirmation) {
			this.receiptConfirmation = receiptConfirmation;
		}


		public Date getReminderDate() {
			return reminderDate;
		}


		public void setReminderDate(Date reminderDate) {
			this.reminderDate = reminderDate;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}


		@Override
		public String toString() {
			return "Reminder [productName=" + productName + ", clientName=" + clientName + ", clientPhone="
					+ clientPhone + ", clientAddress=" + clientAddress + ", purchaseQuantity=" + purchaseQuantity
					+ ", transactionCount=" + transactionCount + ", receiptConfirmation=" + receiptConfirmation
					+ ", reminderDate=" + reminderDate + "]";
		}
}
