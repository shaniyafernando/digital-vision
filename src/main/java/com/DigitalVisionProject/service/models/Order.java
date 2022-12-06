package com.DigitalVisionProject.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderedProduct> orderProducts = new ArrayList<>();

    private Long userId;

    private LocalDate date;

    private double subTotal;

    private int deliveryFee;

    public Order() {}



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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getDeliveryFee() {
        return 150;
    }

    public List<OrderedProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderedProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }


    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }


}