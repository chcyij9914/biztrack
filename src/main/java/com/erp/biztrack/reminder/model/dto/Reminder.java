package com.erp.biztrack.reminder.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class Reminder implements Serializable {
    private static final long serialVersionUID = 5521677998029748532L;

    // Field
    private String reminderId;
    private String productName;
    private String clientName;
    private String clientEmail; 
    private String companyPhone;
    private String address;
    private Integer outboundQuantity;
    private Integer transactionCount;
    private String receiptConfirmation;
    private Date reminderDate;
    private String mailContent;

    // 생성자
    public Reminder() {}

    public Reminder(String reminderId, String productName, String clientName, String clientEmail,
                    String companyPhone, String address, Integer outboundQuantity,
                    Integer transactionCount, String receiptConfirmation, Date reminderDate,
                    String mailContent) {
        this.reminderId = reminderId;
        this.productName = productName;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.companyPhone = companyPhone;
        this.address = address;
        this.outboundQuantity = outboundQuantity;
        this.transactionCount = transactionCount;
        this.receiptConfirmation = receiptConfirmation;
        this.reminderDate = reminderDate;
        this.mailContent = mailContent;
    }

    // Getter & Setter
    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

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

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOutboundQuantity() {
        return outboundQuantity;
    }

    public void setOutboundQuantity(Integer outboundQuantity) {
        this.outboundQuantity = outboundQuantity;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
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

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Reminder [reminderId=" + reminderId + ", productName=" + productName +
               ", clientName=" + clientName + ", clientEmail=" + clientEmail +
               ", companyPhone=" + companyPhone + ", address=" + address +
               ", outboundQuantity=" + outboundQuantity + ", transactionCount=" + transactionCount +
               ", receiptConfirmation=" + receiptConfirmation + ", reminderDate=" + reminderDate +
               ", mailContent=" + mailContent + "]";
    }
}
