package com.DigitalVisionProject.service.models;

import javax.persistence.Entity;

@Entity
public class Address extends User {

    private String billingAddress;
    private String deliveryAddress;

    public Address() {
        super();
    }

    public Address(String billingAddress, String deliveryAddress) {
        super();
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
    }

    public Address(String firstName, String lastName, String email, String password, Role role, String billingAddress, String deliveryAddress) {
        super(firstName, lastName, email, password, role);
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
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
