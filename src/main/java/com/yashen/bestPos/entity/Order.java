package com.yashen.bestPos.entity;

import java.sql.Date;

public class Order implements SuperEntity{
    private String ordId;
    private String customerId;
    private Date ordDate;

    public Order() {
    }

    public Order(String ordId, String customerId, Date ordDate) {
        this.ordId = ordId;
        this.customerId = customerId;
        this.ordDate = ordDate;
    }

    public Order(String ordId, String customerId) {
        this.ordId = ordId;
        this.customerId = customerId;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
    }


    @Override
    public String toString() {
        return "Order{" +
                "ordId='" + ordId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", ordDate=" + ordDate +
                '}';
    }
}
