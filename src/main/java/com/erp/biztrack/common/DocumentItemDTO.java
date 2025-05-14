package com.erp.biztrack.common;

public class DocumentItemDTO implements java.io.Serializable {
   private static final long serialVersionUID = -4538342099506220448L;
   
   private String itemId;                  //   ITEM_ID   VARCHAR2(30 BYTE)
   private String productId;               //   PRODUCT_ID   VARCHAR2(30 BYTE)
   private String productName;
   private String documentId;            //   DOCUMENT_ID   VARCHAR2(30 BYTE)
   private int quantity;                  //   QUANTITY   NUMBER
   private int salePrice;
   private int unitPrice;                  //   UNIT_PRICE   NUMBER(10,0)
   private int amount;
   
   public DocumentItemDTO() {
      super();
   }

   public DocumentItemDTO(String itemId, String productId, String productName, String documentId, int quantity,
         int salePrice, int unitPrice, int amount) {
      super();
      this.itemId = itemId;
      this.productId = productId;
      this.productName = productName;
      this.documentId = documentId;
      this.quantity = quantity;
      this.salePrice = salePrice;
      this.unitPrice = unitPrice;
      this.amount = amount;
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

   public int getSalePrice() {
      return salePrice;
   }

   public void setSalePrice(int salePrice) {
      this.salePrice = salePrice;
   }

   public int getUnitPrice() {
      return unitPrice;
   }

   public void setUnitPrice(int unitPrice) {
      this.unitPrice = unitPrice;
   }

   public int getAmount() {
      return amount;
   }

   public void setAmount(int amount) {
      this.amount = amount;
   }

   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   @Override
   public String toString() {
      return "DocumentItemDTO [itemId=" + itemId + ", productId=" + productId + ", productName=" + productName
            + ", documentId=" + documentId + ", quantity=" + quantity + ", salePrice=" + salePrice + ", unitPrice="
            + unitPrice + ", amount=" + amount + "]";
   }
}
