package com.erp.biztrack.purchase.exception;

public class PurchaseException extends RuntimeException {

    public PurchaseException(String message) {
        super(message);
    }

    public PurchaseException(String message, Throwable cause) {
        super(message, cause);
    }
}