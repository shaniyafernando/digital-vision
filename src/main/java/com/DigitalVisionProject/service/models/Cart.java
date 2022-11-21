package com.DigitalVisionProject.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long productId;
    private int quantityAddedToCart;


    public Cart() {
    }

    @Autowired
    public Cart(Long id, Long userId, Long productId, int quantityAddedToCart) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantityAddedToCart = quantityAddedToCart;
    }

    @Autowired
    public Cart( Long userId, Long productId, int quantityAddedToCart) {
        this.userId = userId;
        this.productId = productId;
        this.quantityAddedToCart = quantityAddedToCart;
    }

    public double subTotal(double priceOfProduct,int quantityAddedToCart){
        return priceOfProduct * quantityAddedToCart;
    }

    public double totalPrice(double price){
        return price++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantityAddedToCart() {
        return quantityAddedToCart;
    }

    public void setQuantityAddedToCart(int quantityAddedToCart) {
        this.quantityAddedToCart = quantityAddedToCart;
    }
}
