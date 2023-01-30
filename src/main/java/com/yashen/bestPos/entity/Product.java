package com.yashen.bestPos.entity;

public class Product implements SuperEntity{
    private String pId;
    private String pDesc;
    private double pPrice;
    private int pQty;

    public Product() {
    }

    public Product(String pId, String pDesc, double pPrice, int pQty) {
        this.pId = pId;
        this.pDesc = pDesc;
        this.pPrice = pPrice;
        this.pQty = pQty;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public int getpQty() {
        return pQty;
    }

    public void setpQty(int pQty) {
        this.pQty = pQty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pId='" + pId + '\'' +
                ", pDesc='" + pDesc + '\'' +
                ", pPrice='" + pPrice + '\'' +
                ", pQty='" + pQty + '\'' +
                '}';
    }
}
