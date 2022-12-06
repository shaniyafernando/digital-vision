package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.*;
import com.DigitalVisionProject.service.repositories.ProductRepository;
import com.DigitalVisionProject.service.repositories.UserRepository;
import com.DigitalVisionProject.service.repositories.WishListItemRepository;
import com.DigitalVisionProject.service.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WishListService {
    private final WishListRepository wishListRepository;
    private final WishListItemService wishListItemService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository,
                            WishListItemService wishListItemService, UserRepository userRepository,
                           ProductRepository productRepository) {
        this.wishListRepository = wishListRepository;
        this.wishListItemService = wishListItemService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public WishList addProductToWishList(Long userId, Long productId){
        Product product = productRepository.getReferenceById(productId);
        WishListItem wishListItem = wishListItemService.addWishListItem(product);

        WishList wishList = (WishList) wishListRepository.findAll().stream().filter(
                list -> list.getUserId().equals(userId));

        if(!wishListRepository.existsById(wishList.getId())){
            WishList newWishList = new WishList();
            List<WishListItem> items = new ArrayList<>();
            items.add(wishListItem);
            newWishList.setWishListItems(items);
            newWishList.setUserId(userId);
            wishListRepository.save(newWishList);
        }

        List<WishListItem> items = wishList.getWishListItems();
        items.add(wishListItem);
        wishList.setWishListItems(items);
        return wishListRepository.save(wishList);
    }

    public WishList removeProductFromWishList(Long wishlistId, Long wishListItemId){
        WishListItem item = wishListItemService.getWishListItem(wishListItemId);
        WishList wishList = wishListRepository.getReferenceById(wishlistId);
        List<WishListItem> currentList = wishList.getWishListItems();
        currentList.remove(item);
        wishList.setWishListItems(currentList);
        wishListItemService.deleteWishListItem(wishListItemId);
        return wishListRepository.save(wishList);
    }

    public WishList getWishList(Long userId) {
        return (WishList) wishListRepository.findAll().stream().filter(list -> list.getUserId().equals(userId));

    }
}
