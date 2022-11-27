package com.DigitalVisionProject.service.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="payments")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private LocalDate date;

    private double amount;

    @OneToOne()
    @JoinColumn(name="order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private UUID invoiceNumber;

    public Payment() {
    }

    public Payment(LocalDate date, double amount, Order order, PaymentType paymentType, UUID invoiceNumber) {
        this.date = date;
        this.amount = amount;
        this.order = order;
        this.paymentType = paymentType;
        this.invoiceNumber = invoiceNumber;
    }

    public Payment(Long id, LocalDate date, double amount, Order order, PaymentType paymentType, UUID invoiceNumber) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.order = order;
        this.paymentType = paymentType;
        this.invoiceNumber = invoiceNumber;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public UUID getInvoiceNumber() {
        return UUID.randomUUID();
    }

    public void setInvoiceNumber() {
        this.invoiceNumber = getInvoiceNumber();
    }
}
