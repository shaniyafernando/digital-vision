package com.DigitalVisionProject.service.dtos;

import com.DigitalVisionProject.service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class CartDTO {

    private Long productId;
    private int quantityAddedToCart;

    private Long userId;

    public CartDTO(Long productId, int quantityAddedToCart) {
        this.productId = productId;
        this.quantityAddedToCart = quantityAddedToCart;
    }

    public Long getUserId() {
        return userId;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
