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
    private final WishListItemRepository wishListItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository,
                           WishListItemService wishListItemService, WishListItemRepository wishListItemRepository, UserRepository userRepository,
                           ProductRepository productRepository) {
        this.wishListRepository = wishListRepository;
        this.wishListItemService = wishListItemService;
        this.wishListItemRepository = wishListItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public WishList addFirstProductToWishList(Long userId, Long productId){
        Product product = productRepository.getReferenceById(productId);
        User user = userRepository.getReferenceById(userId);

        WishList newWishList = new WishList();

        List<WishListItem> newItems = new ArrayList<>();
        WishListItem newItem = wishListItemService.addWishListItem(product);
        newItems.add(newItem);

        newWishList.setWishListItems(newItems);
        WishList savedWishList = wishListRepository.save(newWishList);

        user.setWishListId(savedWishList.getId());
        userRepository.save(user);
        return savedWishList;
    }

    public WishList addProductToExistingWishList(Long userId, Long productId){
        User user = userRepository.getReferenceById(userId);
        WishList wishList = wishListRepository.getReferenceById(user.getWishListId());
        Product product = productRepository.getReferenceById(productId);

        WishListItem newWishListItem = wishListItemService.addWishListItem(product);
        List<WishListItem> items = wishList.getWishListItems();
        items.add(newWishListItem);
        wishList.setWishListItems(items);
        return wishListRepository.save(wishList);
    }

    public void removeProductFromWishList(Long userId, Long productId){
        Product product = productRepository.getReferenceById(productId);
        User user = userRepository.getReferenceById(userId);
        WishList wishList = wishListRepository.getReferenceById(user.getWishListId());
        List<WishListItem> items = new ArrayList<>();
        wishList.getWishListItems().forEach(
                wishListItem -> {
                    if(wishListItem.getProduct().equals(product)){
                        wishListItemRepository.deleteById(wishListItem.getId());
                    }else{
                        items.add(wishListItem);
                    }
                }
        );
        wishList.setWishListItems(items);
        WishList save = wishListRepository.save(wishList);

        if(save.getWishListItems().isEmpty()){
            wishListRepository.deleteById(wishList.getId());
            user.setWishListId(null);
            userRepository.save(user);
        }

    }

    public WishList getWishList(Long userId) {
        User user = userRepository.getReferenceById(userId);
        return wishListRepository.getReferenceById(user.getWishListId());
    }
}
