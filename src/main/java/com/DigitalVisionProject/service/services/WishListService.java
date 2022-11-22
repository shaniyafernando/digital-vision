package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.WishList;
import com.DigitalVisionProject.service.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WishListService {
    private final WishListRepository wishListRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }


    //Create Wishlist
    public void createWishList(WishList wishList){
        wishListRepository.save(wishList);
    }

    //ReadWishList
    public List<WishList> readWishList(Long userId){
        return wishListRepository.findAllByUserId(userId);
    }

    public void deleteProductFromWishList(Long productId){
        WishList wishList = wishListRepository.findByProductId(productId);
        wishListRepository.deleteById(wishList.getId());
    }
}
