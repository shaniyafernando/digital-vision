package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService cartService;


    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping()
    public ResponseEntity<Cart> addProductToCart(@RequestBody CartDTO cartDTO){
        Cart newCartRecord = cartService.addProductToCart(cartDTO);
        return new ResponseEntity<>(newCartRecord,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> removeProductFromCart(@RequestBody Map<String, Object> payload){
        int userId = (int) payload.get("userId");
        int cartItemId = (int) payload.get("cartItemId");
        Cart cart = cartService.removeProductFromCart((long) userId,(long) cartItemId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @PostMapping("/products")
    public ResponseEntity<Cart> getAllProductsInCart(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("id");
        Cart cartList = cartService.getAllProductsInCart((long) id);
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }







}
