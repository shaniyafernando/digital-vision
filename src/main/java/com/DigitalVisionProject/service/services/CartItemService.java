package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.CartItem;
import com.DigitalVisionProject.service.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem addCartItem(CartDTO cartDTO){
        CartItem cartItem = new CartItem(cartDTO.getUserId(),
                cartDTO.getProduct(), cartDTO.getQuantityAddedToCart());
        return  cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long cartItemId){
        cartItemRepository.deleteById(cartItemId);
    }

    public CartItem updateQuantityInCartItem(CartItem cartItem){
        CartItem item = cartItemRepository.getReferenceById(cartItem.getId());
        item.setQuantityAddedToCart(cartItem.getQuantityAddedToCart());
        return cartItemRepository.save(item);
    }
}
