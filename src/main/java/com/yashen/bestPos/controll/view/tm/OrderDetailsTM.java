package com.yashen.bestPos.controll.view.tm;

public class OrderDetailsTM {
    String product;
    int qty;
    double price;

    public OrderDetailsTM() {
    }

    public OrderDetailsTM(String product, int qty, double price) {
        this.product = product;
        this.qty = qty;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
