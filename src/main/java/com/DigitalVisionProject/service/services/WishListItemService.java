package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.WishListItem;
import com.DigitalVisionProject.service.repositories.WishListItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListItemService {

    private final WishListItemRepository wishListItemRepository;


    public WishListItemService(WishListItemRepository wishListItemRepository) {
        this.wishListItemRepository = wishListItemRepository;
    }

    public WishListItem addWishListItem(Product product){
        WishListItem item = new WishListItem();
        return wishListItemRepository.save(item);
    }

    public WishListItem getWishListItem(Long id){
        return wishListItemRepository.getReferenceById(id);
    }

    public void deleteWishListItem(Long id){
        wishListItemRepository.deleteById(id);
    }

}
