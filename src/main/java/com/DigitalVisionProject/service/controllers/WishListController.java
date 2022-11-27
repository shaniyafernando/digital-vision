package com.DigitalVisionProject.service.controllers;


import com.DigitalVisionProject.service.dtos.WishListDTO;
import com.DigitalVisionProject.service.exceptions.ApiResponse;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.WishList;
import com.DigitalVisionProject.service.services.UserService;
import com.DigitalVisionProject.service.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/wishlist")
public class WishListController {

    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PutMapping("/new")
    public ResponseEntity<WishList> addProductToWishList(@RequestBody WishListDTO wishListDTO){
        WishList wishList = wishListService.addProductToWishList(wishListDTO.getUserId(), wishListDTO.getProductId());
        return new ResponseEntity<>(wishList,HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<WishList> removeProductFromWishList(@RequestBody WishListDTO wishListDTO){
        WishList wishList = wishListService.removeProductFromWishList(wishListDTO.getUserId(), wishListDTO.getProductId());
        return new ResponseEntity<>(wishList,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<WishList> removeProductFromWishList(@RequestBody Map<String, Object> payload){
        int userId = (int) payload.get("userId");
        WishList wishList = wishListService.getWishList((long) userId);
        return new ResponseEntity<>(wishList,HttpStatus.OK);
    }

}
