package com.DigitalVisionProject.service.dtos;


public class PaymentDTO {

    private int amount;

    private Long userId;

    private Long  orderId;

    private String paymentType;

    public PaymentDTO(int amount, Long userId, Long orderId, String paymentType) {
        this.amount = amount;
        this.userId = userId;
        this.orderId = orderId;
        this.paymentType = paymentType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
