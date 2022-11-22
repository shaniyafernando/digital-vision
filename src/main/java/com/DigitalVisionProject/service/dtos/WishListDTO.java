package com.DigitalVisionProject.service.dtos;

import com.DigitalVisionProject.service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class WishListDTO {
    private Long userId;
    private Product product;

    @Autowired
    public WishListDTO(Long userId, Product product) {
        this.userId = userId;
        this.product = product;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
