package com.DigitalVisionProject.service.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long paymentId;
    private LocalDate paymentDate;
    private float paymentAmount;

    public Payment() {
    }

    private enum paymentType{CREDIT,PAYPAL};

    public Payment(Long paymentId, LocalDate paymentDate, float paymentAmount, Long orderId) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.orderId = orderId;
    }

    private Long orderId;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
