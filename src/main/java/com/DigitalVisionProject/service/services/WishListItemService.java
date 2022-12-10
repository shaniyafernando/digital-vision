package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.models.WishList;
import com.DigitalVisionProject.service.models.WishListItem;
import com.DigitalVisionProject.service.repositories.ProductRepository;
import com.DigitalVisionProject.service.repositories.UserRepository;
import com.DigitalVisionProject.service.repositories.WishListItemRepository;
import com.DigitalVisionProject.service.repositories.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListItemService {

    private final WishListItemRepository wishListItemRepository;


    public WishListItemService(WishListItemRepository wishListItemRepository) {
        this.wishListItemRepository = wishListItemRepository;
    }

    public WishListItem addWishListItem(Product product){
        WishListItem item = new WishListItem();
        item.setProduct(product);
        return wishListItemRepository.save(item);
    }

    public WishListItem getWishListItem(Long id){
        return wishListItemRepository.getReferenceById(id);
    }



}
