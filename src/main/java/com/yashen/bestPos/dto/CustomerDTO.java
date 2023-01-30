package com.yashen.bestPos.dto;

public class CustomerDTO {
    private String cusId;
    private String cusName;
    private String cusAddress;
    private String cusContactNo;
    private String cusEmail;

    public CustomerDTO() {
    }

    public CustomerDTO(String cusId, String cusName, String cusAddress, String cusContactNo, String cusEmail) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusContactNo = cusContactNo;
        this.cusEmail = cusEmail;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusContactNo() {
        return cusContactNo;
    }

    public void setCusContactNo(String cusContactNo) {
        this.cusContactNo = cusContactNo;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }
}
