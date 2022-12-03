package com.DigitalVisionProject.service.models;


import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    private Long id;

    @OneToOne
    private Product product;

    private int quantityAddedToCart;

    @ManyToOne
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public CartItem() {
    }

    public CartItem(Long id, Product product, int quantityAddedToCart) {
        this.id = id;
        this.product = product;
        this.quantityAddedToCart = quantityAddedToCart;
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

    public int getQuantityAddedToCart() {
        return quantityAddedToCart;
    }

    public void setQuantityAddedToCart(int quantityAddedToCart) {
        this.quantityAddedToCart = quantityAddedToCart;
    }
}
