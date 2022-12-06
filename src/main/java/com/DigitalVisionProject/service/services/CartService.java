package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.models.CartItem;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.repositories.CartRepository;
import com.DigitalVisionProject.service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemService cartItemService;


    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService,
                       ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productRepository = productRepository;
    }

    public Cart addProductToCart(CartDTO cartDTO){
        CartItem cartItem = cartItemService.addCartItem(cartDTO);

        Cart cart = (Cart) cartRepository.findAll().stream().filter(
                cart1 -> cart1.getUserId().equals(cartDTO.getUserId()));

        if(!cartRepository.existsById(cart.getId())){
            Cart newCart = new Cart();
            List<CartItem> items = new ArrayList<>();
            items.add(cartItem);
            newCart.setCartItems(items);
            newCart.setUserId(cartDTO.getUserId());
            cartRepository.save(newCart);
        }

        List<CartItem> items = cart.getCartItems();
        items.add(cartItem);
        cart.setCartItems(items);
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long cartId, Long  cartItemId){
        Cart cart = cartRepository.getReferenceById(cartId);
        List<CartItem> items = cart.getCartItems();
        CartItem cartItem = cartItemService.getCartItem(cartItemId);
        items.remove(cartItem);
        cartItemService.deleteCartItem(cartItem.getId());
        cart.setCartItems(items);
        return cartRepository.save(cart);
    }

    public Cart getAllProductsInCart(Long userId){
        Cart cart = (Cart) cartRepository.findAll().stream().filter(cart1 -> cart1.getUserId().equals(userId));
        int total = calculateTotalPrice(cart.getCartItems());
        cart.setTotal(total);
        return cartRepository.save(cart);
    }


    public int calculateTotalPrice(List<CartItem> cartItems){
        List<Integer> subTotals = new ArrayList<>();
        int total = 0;
        cartItems.forEach(cartItem -> {
            Product product = productRepository.getReferenceById(cartItem.getProductId());
            int subtotal = product.getPrice() * cartItem.getQuantityAddedToCart();
            subTotals.add(subtotal);
        });
        for (int i = 0; i < subTotals.size() ; i++) {
            total = subTotals.get(i) + subTotals.get(i + 1);
        }
        return total;
    }






}
