package com.DigitalVisionProject.service.models;

import javax.persistence.*;

@Entity
public class WishListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public WishListItem() {
    }

    public WishListItem(Long id, Product product) {
        this.id = id;
        this.product = product;
    }

    public WishListItem(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
