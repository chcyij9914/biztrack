package com.erp.biztrack.inbound.model.dto;

public class DocumentItem {

	 private String itemId;             
	    private String productId;         
	    private String documentId;          
	    private int quantity;                
	    private int unitPrice;          
	    private String connectDocumentId;    

	    // Getter/Setter
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

	    public String getDocumentId() {
	        return documentId;
	    }

	    public void setDocumentId(String documentId) {
	        this.documentId = documentId;
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

	    public String getConnectDocumentId() {
	        return connectDocumentId;
	    }

	    public void setConnectDocumentId(String connectDocumentId) {
	        this.connectDocumentId = connectDocumentId;
	    }
}
