package com.DigitalVisionProject.service.models;

import com.DigitalVisionProject.service.models.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;
    private String title;
    private String description;
    private String category;
    private String colour;
    private String brand;
    private int price;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wish_list_id")
    private WishList wishList;

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }


    public Product() {}
    public Product(Long id, String image, String title, String description, String colour,
                   String brand,String category, int price, int quantity) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.colour = colour;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(String image, String title, String description, String colour, String brand
            ,String category, int price, int quantity) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.colour = colour;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
