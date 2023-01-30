package com.yashen.bestPos.dto;

public class ProductDTO {
    private String pId;
    private String pDesc;
    private double pPrice;
    private int pQty;

    public ProductDTO() {
    }

    public ProductDTO(String pId, String pDesc, double pPrice, int pQty) {
        this.pId = pId;
        this.pDesc = pDesc;
        this.pPrice = pPrice;
        this.pQty = pQty;
    }

    public String getId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public double getPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public int getQty() {
        return pQty;
    }

    public void setpQty(int pQty) {
        this.pQty = pQty;
    }
}
