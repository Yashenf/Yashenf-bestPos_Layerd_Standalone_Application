package com.yashen.bestPos.entity;

public class DeliveryCompany implements SuperEntity{
    private String comId;
    private String comName;
    private String comContact;
    private String comEmail;

    public DeliveryCompany() {
    }

    public DeliveryCompany(String comId, String comName, String comContact, String comEmail) {
        this.comId = comId;
        this.comName = comName;
        this.comContact = comContact;
        this.comEmail = comEmail;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComContact() {
        return comContact;
    }

    public void setComContact(String comContact) {
        this.comContact = comContact;
    }

    public String getComEmail() {
        return comEmail;
    }

    public void setComEmail(String comEmail) {
        this.comEmail = comEmail;
    }

    @Override
    public String toString() {
        return "DeliveryCompany{" +
                "comId='" + comId + '\'' +
                ", comName='" + comName + '\'' +
                ", comContact='" + comContact + '\'' +
                ", comEmail='" + comEmail + '\'' +
                '}';
    }
}
