package com.DigitalVisionProject.service.dtos;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private  String username;
    private  String email;
    private  String password;

    private  String billingAddress;
    private  String deliveryAddress;

    public RegistrationRequest( String username, String email, String password,
                                String billingAddress, String deliveryAddress) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
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
