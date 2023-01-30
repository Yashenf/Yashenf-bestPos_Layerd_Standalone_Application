package com.yashen.bestPos.entity;

public class OrderDetails implements SuperEntity{
    private String ordId;
    private String productId;
    private int qty;

    public OrderDetails() {
    }

    public OrderDetails(String ordId, String productId, int qty) {
        this.ordId = ordId;
        this.productId = productId;
        this.qty = qty;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "ordId='" + ordId + '\'' +
                ", productId='" + productId + '\'' +
                ", qty=" + qty +
                '}';
    }
}
