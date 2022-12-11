package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.WishList;
import com.DigitalVisionProject.service.models.WishListItem;
import com.DigitalVisionProject.service.repositories.WishListItemRepository;
import com.DigitalVisionProject.service.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListItemService {

    private final WishListItemRepository wishListItemRepository;
    private final WishListRepository wishListRepository;


    @Autowired
    public WishListItemService(WishListItemRepository wishListItemRepository, WishListRepository wishListRepository) {
        this.wishListItemRepository = wishListItemRepository;
        this.wishListRepository = wishListRepository;
    }

    public WishListItem addWishListItem(Product product){
        WishListItem item = new WishListItem();
        item.setProduct(product);
        return wishListItemRepository.save(item);
    }

    public void deleteWishListItem(Long wishListId, Long productId){
        WishList wishlist = wishListRepository.getReferenceById(wishListId);
        List<WishListItem> items = wishlist.getWishListItems();
        items.forEach(item -> {
            if(item.getProduct().getId().equals(productId)){
                wishListItemRepository.deleteById(item.getId());
            }
        });
    }

    public WishListItem getWishListItem(Long id){
        return wishListItemRepository.getReferenceById(id);
    }



}
