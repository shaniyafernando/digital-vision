package com.DigitalVisionProject.service.models;

import com.DigitalVisionProject.service.models.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="payments")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    private double amount;

    private Long userId;

    private Long  orderId;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private UUID invoiceNumber;

    public Payment() {
    }

    public Payment(LocalDate date, double amount, Long userId, Long orderId, PaymentType paymentType, UUID invoiceNumber) {
        this.date = date;
        this.amount = amount;
        this.userId = userId;
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.invoiceNumber = invoiceNumber;
    }

    public Payment(Long id, LocalDate date, double amount, Long orderId, PaymentType paymentType, UUID invoiceNumber) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.invoiceNumber = invoiceNumber;
    }

    public Payment(LocalDate date, double amount, Long orderId, PaymentType paymentType, UUID invoiceNumber) {
        this.date = date;
        this.amount = amount;
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.invoiceNumber = invoiceNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setInvoiceNumber(UUID invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public UUID getInvoiceNumber() {
        return UUID.randomUUID();
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }


}
