package com.DigitalVisionProject.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table
public class DeliveryStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private Long paymentId;
    private String status;

    private LocalDate date;

    public DeliveryStatus(Long paymentId, String status, LocalDate date) {
        this.paymentId = paymentId;
        this.status = status;
        this.date = date;
    }

    public DeliveryStatus(Long id, Long paymentId, String status, LocalDate date) {
        this.id = id;
        this.paymentId = paymentId;
        this.status = status;
        this.date = date;
    }

    public DeliveryStatus() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
