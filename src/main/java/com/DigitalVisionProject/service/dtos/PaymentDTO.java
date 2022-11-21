package com.DigitalVisionProject.service.dtos;

import java.util.List;

public class PaymentDTO extends OrderListDTO {

    private String paymentType;

    private Long paymentId;

    public PaymentDTO(Long userId, List<Long> orderIds, double total, double deliveryCharge, double subTotal, String paymentType, Long paymentId) {
        super(userId, orderIds, total, deliveryCharge, subTotal);
        this.paymentType = paymentType;
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}
