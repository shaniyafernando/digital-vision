package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping()
    public ResponseEntity<Cart> addProductToCart(@RequestBody CartDTO cartDTO){
        Cart newCartRecord = cartService.addProductToCart(cartDTO);
        return new ResponseEntity<>(newCartRecord,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable("id") @RequestBody Long id){
        cartService.removeProductFromCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }







}
