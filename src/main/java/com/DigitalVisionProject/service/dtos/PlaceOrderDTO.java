package com.DigitalVisionProject.service.dtos;

public class PlaceOrderDTO {

    private Long orderId;
    private Long cartId;

    public PlaceOrderDTO(Long orderId, Long cartId) {
        this.orderId = orderId;
        this.cartId = cartId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
