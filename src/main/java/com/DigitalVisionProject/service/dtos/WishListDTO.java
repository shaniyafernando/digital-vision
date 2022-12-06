package com.DigitalVisionProject.service.dtos;


public class WishListDTO {
    private Long userId;
    private Long productId;

    public WishListDTO(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public WishListDTO(){}

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
