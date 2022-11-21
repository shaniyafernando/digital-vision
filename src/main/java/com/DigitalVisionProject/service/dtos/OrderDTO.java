package com.DigitalVisionProject.service.dtos;

import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.User;

public class OrderDTO {

    private Long id;
    private User user;
    private Product product;
    private int quantityBought;


    public OrderDTO(Long id, User user, Product product, int quantityBought) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.quantityBought = quantityBought;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
