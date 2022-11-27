package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.models.CartItem;
import com.DigitalVisionProject.service.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;


    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService){
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
    }

    public Cart addProductToCart(CartDTO cartDTO){
        CartItem cartItem = cartItemService.addCartItem(cartDTO);
        Cart cart = cartRepository.getReferenceById(cartDTO.getUserId());
        List<CartItem> cartItemList = cart.getCartItems();
        cartItemList.add(cartItem);
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long userId, Long cartItemId){
        Cart cart = cartRepository.getReferenceById(userId);
        List<CartItem> items = new ArrayList<>();
        cart.getCartItems().forEach(
                item -> {
                    if(item.getId().equals(cartItemId)){
                        cartItemService.deleteCartItem(item.getId());
                    }
                    items.add(item);
                }

        );

        cart.setCartItems(items);
        return cartRepository.save(cart);
    }

    public Cart getAllProductsInCart(Long userId){
        Cart cart = cartRepository.getReferenceById(userId);
        double total = calculateTotalPrice(userId);
        cart.setTotal(total);
        return cartRepository.save(cart);
    }


    public double calculateTotalPrice(Long userId){
        Cart cart = cartRepository.getReferenceById(userId);
        return cart.calculateTotalPrice(cart.getCartItems());
    }






}
