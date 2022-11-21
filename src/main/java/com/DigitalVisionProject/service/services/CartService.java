package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.repositories.CartRepository;
import com.DigitalVisionProject.service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;


    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart addProductToCart(CartDTO cartDTO){
        Cart cart = new Cart();
        cart.setUserId(cartDTO.getUserId());
        cart.setProductId(cartDTO.getProduct().getId());
        cart.setQuantityAddedToCart(cartDTO.getQuantityAddedToCart());
        return cartRepository.save(cart);
    }

    public void removeProductFromCart(Long id){
        cartRepository.deleteById(id);
    }

    public List<Cart> getAllProductsInCart(Long userId){
        List<Cart> carts = cartRepository.findAll();
        List<Cart> cartListOfUser = new ArrayList<>();
        for (Cart cart: carts) {
            if(userId.equals(cart.getUserId())){
                cartListOfUser.add(cart);
            }
        }
        return cartListOfUser;
    }

    public Cart updateQuantityAddToCart(Long cartId, int quantity){
        Cart cart = cartRepository.getReferenceById(cartId);
        cart.setQuantityAddedToCart(quantity);
        return cart;
    }

    public double[] calculateTotalPrice(Long userId){
        List<Cart> productsInUserCart = getAllProductsInCart(userId);
        final double[] total = {0.0};
        productsInUserCart.forEach(cart -> {
            Product product = productRepository.getReferenceById(cart.getProductId());
            double subTotal =  cart.subTotal(product.getPrice(),cart.getQuantityAddedToCart());
            total[0] = cart.totalPrice(subTotal);
        });
        return total;
    }






}
