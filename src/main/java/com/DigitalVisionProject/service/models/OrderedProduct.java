package com.DigitalVisionProject.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table
public class OrderedProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne()
    @JoinColumn(name="product")
    private Product product;

    private int quantityBought;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_order_id")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderedProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(int quantityBought) {
        this.quantityBought = quantityBought;
    }
}