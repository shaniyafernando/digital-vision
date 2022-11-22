package com.DigitalVisionProject.service.controllers;


import com.DigitalVisionProject.service.exceptions.ApiResponse;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.models.WishList;
import com.DigitalVisionProject.service.services.UserService;
import com.DigitalVisionProject.service.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/wishlist")
public class WishListController {
    private UserService userService;
    //Hardcoded need to get it from session
    private Long uId = 9L;
    private WishListService wishListService;

    @Autowired
    public WishListController(UserService userService, WishListService wishListService) {
        this.userService = userService;
        this.wishListService = wishListService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product){
        User user = userService.findUserById(uId);
        WishList wishList = new WishList(user.getId(),product.getId());
        wishListService.createWishList(wishList);

        return new ResponseEntity<>(new ApiResponse(true,"Added to WishList"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> removeFromWishList(@RequestBody Product product){
        wishListService.deleteProductFromWishList(product.getId());
        return new ResponseEntity<>(new ApiResponse(true,"Removed from WishList"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getWishList(@RequestBody Product product){
        wishListService.readWishList(uId);
        return new ResponseEntity<>(new ApiResponse(true,"get WishList"), HttpStatus.OK);
    }
}
