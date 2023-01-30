package com.yashen.bestPos.controll.view.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {
    String no;
    String product;
    double price;
    int qty;
    double total;
    Button clearBtn;

    public PlaceOrderTM() {
    }

    public PlaceOrderTM(String no, String product, double price, int qty, double total, Button clearBtn) {
        this.no = no;
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.total = total;
        this.clearBtn = clearBtn;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getClearBtn() {
        return clearBtn;
    }

    public void setClearBtn(Button clearBtn) {
        this.clearBtn = clearBtn;
    }

    @Override
    public String toString() {
        return "PlaceOrderTM{" +
                "no='" + no + '\'' +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", total=" + total +
                ", clearBtn=" + clearBtn +
                '}';
    }
}
