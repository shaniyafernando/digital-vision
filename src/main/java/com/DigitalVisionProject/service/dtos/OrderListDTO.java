package com.DigitalVisionProject.service.dtos;

import java.util.List;

public class OrderListDTO {
    private Long userId;
    private List<Long> orderIds;
    private double total;
    private double deliveryCharge;
    private double subTotal;


    public OrderListDTO(){}

    public OrderListDTO(Long userId, List<Long> orderIds, double total, double deliveryCharge, double subTotal) {
        this.userId = userId;
        this.orderIds = orderIds;
        this.total = total;
        this.deliveryCharge = deliveryCharge;
        this.subTotal = subTotal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
