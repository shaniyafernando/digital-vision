package com.DigitalVisionProject.service.dtos;


public class PaymentDTO {

    private int amount;

    private Long cartId;

    private Long  orderId;

    private String paymentType;

    public PaymentDTO(int amount, Long cartId, Long orderId, String paymentType) {
        this.amount = amount;
        this.cartId = cartId;
        this.orderId = orderId;
        this.paymentType = paymentType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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
