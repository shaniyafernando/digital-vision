package com.DigitalVisionProject.service.controllers;


import com.DigitalVisionProject.service.dtos.WishListDTO;
import com.DigitalVisionProject.service.models.WishList;
import com.DigitalVisionProject.service.services.WishListItemService;
import com.DigitalVisionProject.service.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/wishlist")
public class WishListController {

    private final WishListService wishListService;

    private final WishListItemService wishListItemService;

    @Autowired
    public WishListController(WishListService wishListService, WishListItemService wishListItemService) {
        this.wishListService = wishListService;
        this.wishListItemService = wishListItemService;
    }

    @PostMapping("/new")
    public ResponseEntity<WishList> addProductToNewWishList(@RequestBody WishListDTO wishListDTO){
        WishList wishList = wishListService.addFirstProductToWishList(wishListDTO.getUserId(), wishListDTO.getProductId());
        return new ResponseEntity<>(wishList,HttpStatus.OK);
    }

    @PostMapping("/existing")
    public ResponseEntity<WishList> addProductToExistingWishList(@RequestBody WishListDTO wishListDTO){
        WishList wishList = wishListService.addProductToExistingWishList(wishListDTO.getUserId(), wishListDTO.getProductId());
        return new ResponseEntity<>(wishList,HttpStatus.OK);
    }

    @DeleteMapping("/{productId}/{userId}")
    public ResponseEntity<?> removeProductFromWishList(
            @PathVariable("productId") Long productId,@PathVariable("userId") Long userId){
        wishListService.removeProductFromWishList(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/item/{wishListId}/{productId}")
    public ResponseEntity<?> deleteWishListItem(
            @PathVariable("productId") Long productId,@PathVariable("wishListId") Long wishListId){
        wishListItemService.deleteWishListItem(wishListId,productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/{userId}")
    public ResponseEntity<WishList> getWishList(@PathVariable("userId") Long userId){
        WishList wishList = wishListService.getWishList(userId);
        return new ResponseEntity<>(wishList,HttpStatus.OK);
    }

}
