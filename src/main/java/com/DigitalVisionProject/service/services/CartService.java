package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.repositories.CartRepository;
import com.DigitalVisionProject.service.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CartService(CartRepository cartRepository, OrderRepository orderRepository){
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    public Cart addProductToCart(CartDTO cartDTO){
        Cart cart = new Cart();
        cart.setUserId(cartDTO.getUserId());
        cart.setProductId(cartDTO.getProduct().getId());
        cart.setQuantityAddedToCart(cartDTO.getQuantityAddedToCart());
        return cartRepository.save(cart);
    }

    public void removeProductFromCart(Long cartId){
        cartRepository.deleteById(cartId);
    }



}
