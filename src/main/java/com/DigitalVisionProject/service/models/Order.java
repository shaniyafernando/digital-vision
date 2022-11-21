package com.DigitalVisionProject.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.mapping.Array;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Long orderProductId;
    private Long userId;
    @Transient
    private double[] subTotal;
    @Transient
    private double[] deliveryFee;

    private double total;

    public double[] deliveryCharge(){
        return new double[]{20.00};
    }

    public Order(Long orderId, Long orderProductId, Long userId, double[] subTotal, double[] deliveryFee, double total) {
        this.orderId = orderId;
        this.orderProductId = orderProductId;
        this.userId = userId;
        this.subTotal = subTotal;
        this.deliveryFee = deliveryFee;
        this.total = total;
    }

    public Order(Long orderProductId, Long userId, double[] subTotal, double[] deliveryFee, double total) {
        this.orderProductId = orderProductId;
        this.userId = userId;
        this.subTotal = subTotal;
        this.deliveryFee = deliveryFee;
        this.total = total;
    }

    public Order(){}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double[] getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double[] subTotal) {
        this.subTotal = subTotal;
    }

    public double[] getDeliveryFee() {
        return deliveryCharge();
    }

    public void setDeliveryFee(double[] deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getTotal() {
        return subTotal[0] + deliveryFee[0];
    }

    public void setTotal(double total) {
        this.total = total;
    }
}