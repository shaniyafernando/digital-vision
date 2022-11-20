package com.DigitalVisionProject.service.dtos;

import com.DigitalVisionProject.service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class CartDTO {

    private Product product;
    private Long userId;
    private int quantityAddedToCart;

    @Autowired
    public CartDTO(Product product, Long userId, int quantitySelected) {
        this.product = product;
        this.userId = userId;
        this.quantityAddedToCart = quantitySelected;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getQuantityAddedToCart() {
        return quantityAddedToCart;
    }

    public void setQuantityAddedToCart(int quantityAddedToCart) {
        this.quantityAddedToCart = quantityAddedToCart;
    }
}
