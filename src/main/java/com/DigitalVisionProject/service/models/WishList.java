package com.DigitalVisionProject.service.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="wish_list")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    private List<WishListItem> wishListItems = new ArrayList<>();
    private Long userId;

    public WishList() {
    }

    public List<WishListItem> getWishListItems() {
        return wishListItems;
    }

    public void setWishListItems(List<WishListItem> wishListItems) {
        this.wishListItems = wishListItems;
    }

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
}