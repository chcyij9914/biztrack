package com.erp.biztrack.graph.model.dto;

public class Graph implements java.io.Serializable {
    private static final long serialVersionUID = 5014148433259873701L;

    // ---------------------- 제품별 영업이익 관련 필드 ----------------------
    private String productCode;         // 제품 코드
    private String productName;         // 제품명
    private int costPrice;              // 원가
    private int salePrice;              // 판매가
    private int outboundQuantity;       // 출고 수량
    private int profitPerUnit;          // 단위당 이익
    private int totalProfit;            // 총 이익
    private double profitMargin;        // 이익률 %

    // ---------------------- 거래건수 그래프/표 관련 필드 ----------------------
    private String clientName;          // 거래처 이름
    private int count;                  // 거래 건수
    private int totalAmount;            // 총 거래 금액
    private String lastTransactionDate; // 최근 거래일
    private String managerName;         // 담당자 이름
    private String managerPhone;        // 담당자 연락처
    private String clientStatus;        // 계약 상태

    // ---------------------- 생성자 ----------------------
    public Graph() {}

    public Graph(String productCode, String productName, int costPrice, int salePrice, int outboundQuantity,
                 int profitPerUnit, int totalProfit, double profitMargin) {
        this.productCode = productCode;
        this.productName = productName;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.outboundQuantity = outboundQuantity;
        this.profitPerUnit = profitPerUnit;
        this.totalProfit = totalProfit;
        this.profitMargin = profitMargin;
    }

    // ---------------------- Getter / Setter (제품) ----------------------
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getCostPrice() { return costPrice; }
    public void setCostPrice(int costPrice) { this.costPrice = costPrice; }

    public int getSalePrice() { return salePrice; }
    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public int getOutboundQuantity() { return outboundQuantity; }
    public void setOutboundQuantity(int outboundQuantity) { this.outboundQuantity = outboundQuantity; }

    public int getProfitPerUnit() { return profitPerUnit; }
    public void setProfitPerUnit(int profitPerUnit) { this.profitPerUnit = profitPerUnit; }

    public int getTotalProfit() { return totalProfit; }
    public void setTotalProfit(int totalProfit) { this.totalProfit = totalProfit; }

    public double getProfitMargin() { return profitMargin; }
    public void setProfitMargin(double profitMargin) { this.profitMargin = profitMargin; }

    public int getTotalRevenue() {
        return salePrice * outboundQuantity;
    }

    public int getTotalCost() {
        return costPrice * outboundQuantity;
    }

    // ---------------------- Getter / Setter (거래건수) ----------------------
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public int getTotalAmount() { return totalAmount; }
    public void setTotalAmount(int totalAmount) { this.totalAmount = totalAmount; }

    public String getLastTransactionDate() { return lastTransactionDate; }
    public void setLastTransactionDate(String lastTransactionDate) { this.lastTransactionDate = lastTransactionDate; }

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }

    public String getManagerPhone() { return managerPhone; }
    public void setManagerPhone(String managerPhone) { this.managerPhone = managerPhone; }

    public String getClientStatus() { return clientStatus; }
    public void setClientStatus(String clientStatus) { this.clientStatus = clientStatus; }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    // ---------------------- toString() ----------------------
    @Override
    public String toString() {
        if (productCode != null && productName != null) {
            // 제품 그래프
            return "[제품 그래프] 제품명: " + productName +
                   ", 출고수량: " + outboundQuantity +
                   ", 총이익: " + totalProfit +
                   ", 이익률: " + profitMargin + "%" +
                   ", 단위당이익: " + profitPerUnit;
        } else if (clientName != null) {
            // 거래건수 그래프
            return "[거래 그래프] 거래처: " + clientName +
                   ", 거래건수: " + count +
                   ", 총금액: " + totalAmount +
                   ", 최근거래일: " + lastTransactionDate +
                   ", 담당자: " + managerName +
                   ", 상태: " + clientStatus;
        } else {
            return "[Graph DTO] 내용 없음";
        }
    }
}
