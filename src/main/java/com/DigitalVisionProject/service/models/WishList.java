package com.DigitalVisionProject.service.models;


import javax.persistence.*;

@Entity
@Table(name = "wishlist")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long userId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    private Long productId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    public WishList() {
    }

    public WishList(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
