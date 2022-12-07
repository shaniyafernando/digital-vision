package com.DigitalVisionProject.service.models;

import com.DigitalVisionProject.service.models.enums.Role;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String billingAddress;
    private String deliveryAddress;
    private Long userId;


    public Address(String billingAddress, String deliveryAddress) {
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
    }

    public Address(Long id, Long userId,String billingAddress, String deliveryAddress) {
        this.id = id;
        this.userId = userId;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
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

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
