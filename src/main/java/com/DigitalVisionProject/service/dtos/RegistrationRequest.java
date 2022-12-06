package com.DigitalVisionProject.service.dtos;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String username;
    private final String email;
    private final String password;

    private final String billingAddress;
    private final String deliveryAddress;

    public RegistrationRequest( String username, String email, String password, String billingAddress, String deliveryAddress) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
    }


    public String getUsername() {
        return username;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
