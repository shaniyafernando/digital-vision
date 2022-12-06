package com.DigitalVisionProject.service.models;


import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    private Long id;

    private Long productId;

    private int quantityAddedToCart;


    public CartItem() {
    }

    public CartItem(Long productId, int quantityAddedToCart, Long cartId) {
        this.productId = productId;
        this.quantityAddedToCart = quantityAddedToCart;
    }

    public CartItem(Long id, Long productId, int quantityAddedToCart, Long cartId) {
        this.id = id;
        this.productId = productId;
        this.quantityAddedToCart = quantityAddedToCart;
    }

    public CartItem(Long productId, int quantityAddedToCart) {
        this.productId = productId;
        this.quantityAddedToCart = quantityAddedToCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
