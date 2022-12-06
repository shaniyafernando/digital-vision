package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.CartItem;
import com.DigitalVisionProject.service.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem addCartItem(CartDTO cartDTO){
        CartItem cartItem = new CartItem( cartDTO.getProductId(), cartDTO.getQuantityAddedToCart());
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

    public List<CartItem> getCartItems(List<Long> ids){
        return cartItemRepository.findAllById(ids);
    }
    public CartItem getCartItem(Long id){
        return cartItemRepository.getReferenceById(id);
    }
}
