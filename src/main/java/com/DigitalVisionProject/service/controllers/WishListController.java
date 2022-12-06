package com.DigitalVisionProject.service.controllers;


import com.DigitalVisionProject.service.dtos.WishListDTO;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.WishList;
import com.DigitalVisionProject.service.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/wishlist")
public class WishListController {

    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/new")
    public ResponseEntity<WishList> addProductToWishList(@RequestBody WishListDTO wishListDTO){
        WishList wishList = wishListService.addProductToWishList(wishListDTO.getUserId(), wishListDTO.getProductId());
        return new ResponseEntity<>(wishList,HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<WishList> removeProductFromWishList(@RequestBody Map<String, Object> payload){
        int wishlistId = (int) payload.get("wishlistId");
        int productId = (int) payload.get("productId");
        WishList wishList = wishListService.removeProductFromWishList((long) wishlistId,(long) productId);
        return new ResponseEntity<>(wishList,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<WishList> getWishList(@PathVariable("userId") Long userId){
        WishList wishList = wishListService.getWishList(userId);
        return new ResponseEntity<>(wishList,HttpStatus.OK);
    }

}
