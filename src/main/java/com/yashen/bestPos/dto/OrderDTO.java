package com.yashen.bestPos.dto;

import java.sql.Date;

public class OrderDTO {
    private String ordId;
    private String customerId;
    private Date ordDate;

    public OrderDTO() {
    }

    public OrderDTO(String ordId, String customerId, Date ordDate) {
        this.ordId = ordId;
        this.customerId = customerId;
        this.ordDate = ordDate;
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
        return "OrderDTO{" +
                "ordId='" + ordId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", ordDate=" + ordDate +
                '}';
    }
}
