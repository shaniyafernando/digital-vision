package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.models.CartItem;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.services.CartItemService;
import com.DigitalVisionProject.service.services.CartService;
import com.DigitalVisionProject.service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, CartItemService cartItemService, ProductService productService){
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<Cart> addFirstProductToCart(@RequestBody CartDTO cartDTO){
        Cart newCartRecord = cartService.addFirstProductToCart(cartDTO.getUserId(),
                cartDTO.getProductId(), cartDTO.getQuantityAddedToCart());
        return new ResponseEntity<>(newCartRecord,HttpStatus.CREATED);
    }

    @PostMapping("/existing")
    public ResponseEntity<Cart> addProductToCart(@RequestBody CartDTO cartDTO){
        Cart newCartRecord = cartService.addProductToExistingCart(cartDTO.getUserId(),
                cartDTO.getProductId(), cartDTO.getQuantityAddedToCart());
        return new ResponseEntity<>(newCartRecord,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable("id") Long id){
        cartItemService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Cart> getAllProductsInCart(@PathVariable("id") Long id){
        Cart cartList = cartService.getAllProductsInCart(id);
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<CartItem> getCartItem(@PathVariable("id") Long id){
        CartItem cartItem = cartItemService.getCartItem(id);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }







}
