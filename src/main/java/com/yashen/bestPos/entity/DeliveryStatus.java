package com.yashen.bestPos.entity;

public class DeliveryStatus implements SuperEntity{
    private String deliveryTrackingNo;
    private String deliveryAgency;
    private String  orderId;
    private double deliveryCharge;

    public DeliveryStatus() {
    }

    public DeliveryStatus(String deliveryTrackingNo, String deliveryAgency, String orderId, double deliveryCharge) {
        this.deliveryTrackingNo = deliveryTrackingNo;
        this.deliveryAgency = deliveryAgency;
        this.orderId = orderId;
        this.deliveryCharge = deliveryCharge;
    }

    public String getDeliveryTrackingNo() {
        return deliveryTrackingNo;
    }

    public void setDeliveryTrackingNo(String deliveryTrackingNo) {
        this.deliveryTrackingNo = deliveryTrackingNo;
    }

    public String getDeliveryAgency() {
        return deliveryAgency;
    }

    public void setDeliveryAgency(String deliveryAgency) {
        this.deliveryAgency = deliveryAgency;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getDeliveryPrice() {
        return deliveryCharge;
    }

    public void setDeliveryPrice(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    @Override
    public String toString() {
        return "DeliveryStatus{" +
                "deliveryTrackingNo='" + deliveryTrackingNo + '\'' +
                ", deliveryAgency=" + deliveryAgency +
                ", orderId='" + orderId + '\'' +
                ", status='" + deliveryCharge + '\'' +
                '}';
    }
}
