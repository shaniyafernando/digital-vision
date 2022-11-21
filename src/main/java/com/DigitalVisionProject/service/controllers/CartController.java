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
    public ResponseEntity<?> removeProductFromCart(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("id");
        cartService.removeProductFromCart((long) id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/products")
    public ResponseEntity<List<Cart>> getAllProductsInCart(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("id");
        List<Cart> cartList = cartService.getAllProductsInCart((long) id);
        System.out.println((long) id);
        System.out.println(cartList);
        ResponseEntity<List<Cart>> response = new ResponseEntity<>(cartList, HttpStatus.OK);
        System.out.println(response);
        return response;
    }

    @PutMapping()
    public ResponseEntity<Cart> updateQuantityAddToCart(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("cartId");
        int quantity = (int) payload.get("quantity");
        Cart cart = cartService.updateQuantityAddToCart((long) id, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/total")
    public ResponseEntity<double[]> getTotalPrice(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("userId");
        double[] totalPrice = cartService.calculateTotalPrice((long) id);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

}
