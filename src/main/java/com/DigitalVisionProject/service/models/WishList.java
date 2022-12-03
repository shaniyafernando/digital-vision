package com.DigitalVisionProject.service.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class WishList  extends User{

    @OneToMany
    private List<Product> products;

    public WishList() {
    }


    public WishList(Long id, List<Product> products) {
        super(id);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
