package com.yashen.bestPos.dto;

public class DeliveryStatusDTO {
    private String deliveryTrackingNo;
    private String deliveryAgency;
    private String  orderId;
    private double deliveryCharge;

    public DeliveryStatusDTO() {
    }

    public DeliveryStatusDTO(String deliveryTrackingNo, String deliveryAgency, String orderId, double deliveryCharge) {
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

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }
}
