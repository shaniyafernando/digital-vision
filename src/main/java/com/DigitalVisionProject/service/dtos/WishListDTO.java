package com.DigitalVisionProject.service.dtos;

import com.DigitalVisionProject.service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class WishListDTO {
    private Long userId;
    private Long productId;

    public WishListDTO(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
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
}
