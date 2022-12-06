package com.DigitalVisionProject.service.dtos;

public class SearchDTO {

   private double minPrice;
   private double maxPrice;
   private String type;

    public SearchDTO(double minPrice, double maxPrice, String type) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.type = type;
    }

    SearchDTO(){}

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
