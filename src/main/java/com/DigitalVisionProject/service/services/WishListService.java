package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.models.WishList;
import com.DigitalVisionProject.service.repositories.ProductRepository;
import com.DigitalVisionProject.service.repositories.UserRepository;
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
    private final ProductRepository productRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository, ProductRepository productRepository) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
    }

    public WishList addProductToWishList(Long userId, Long productId){
        Product product = productRepository.getReferenceById(productId);
        List<Product> newList = new ArrayList<>();
        newList.add(product);
        WishList wishList = wishListRepository.getReferenceById(userId);
        wishList.setProducts(newList);
        return wishListRepository.save(wishList);
    }

    public WishList removeProductFromWishList(Long userId, Long productId){
        WishList wishList = wishListRepository.getReferenceById(userId);
        Product product = productRepository.getReferenceById(productId);
        List<Product> existingProducts = wishList.getProducts();
        existingProducts.remove(product);
        wishList.setProducts(existingProducts);
        return wishListRepository.save(wishList);
    }

    public WishList getWishList(Long userId){
        return wishListRepository.getReferenceById(userId);
    }
}
