package com.erp.biztrack.graph.model.dto;

public class Graph implements java.io.Serializable{
	private static final long serialVersionUID = 5014148433259873701L;

	// Field
	private String productCode;       	 // 제품 코드
    private String productName;        // 제품명
    private int costPrice;            		 // 원가
    private int salePrice;             		 // 판매가
    private int outboundQuantity;      // 출고 수량 (총합)
    private int profitPerUnit;         	 // 단위당 이익
    private int totalProfit;           		 // 총 이익
    private double profitMargin;       	 // 이익률 (%)
    
    
    // Constructor
    public Graph() {
		super();
	}


	public Graph(String productCode, String productName, int costPrice, int salePrice, int outboundQuantity,
			int profitPerUnit, int totalProfit, double profitMargin) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.costPrice = costPrice;
		this.salePrice = salePrice;
		this.outboundQuantity = outboundQuantity;
		this.profitPerUnit = profitPerUnit;
		this.totalProfit = totalProfit;
		this.profitMargin = profitMargin;
	}


	// getters and setters
	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}


	public int getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}


	public int getOutboundQuantity() {
		return outboundQuantity;
	}


	public void setOutboundQuantity(int outboundQuantity) {
		this.outboundQuantity = outboundQuantity;
	}


	public int getProfitPerUnit() {
		return profitPerUnit;
	}


	public void setProfitPerUnit(int profitPerUnit) {
		this.profitPerUnit = profitPerUnit;
	}


	public int getTotalProfit() {
		return totalProfit;
	}


	public void setTotalProfit(int totalProfit) {
		this.totalProfit = totalProfit;
	}


	public double getProfitMargin() {
		return profitMargin;
	}


	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Graph [productCode=" + productCode + ", productName=" + productName + ", costPrice=" + costPrice
				+ ", salePrice=" + salePrice + ", outboundQuantity=" + outboundQuantity + ", profitPerUnit="
				+ profitPerUnit + ", totalProfit=" + totalProfit + ", profitMargin=" + profitMargin + "]";
	}
	
    
}
