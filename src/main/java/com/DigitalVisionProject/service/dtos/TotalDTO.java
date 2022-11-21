package com.DigitalVisionProject.service.dtos;

public class TotalDTO {

    private double[] deliveryCharge;
    private double[] subTotal;

    public TotalDTO() {}

    public double[] getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double[] deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public double[] getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double[] subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return deliveryCharge[0] + subTotal[0];
    }

    public void setTotal(double total) {
    }
}
