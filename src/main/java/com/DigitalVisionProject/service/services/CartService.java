package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.CartDTO;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.models.CartItem;
import com.DigitalVisionProject.service.models.Product;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.repositories.CartRepository;
import com.DigitalVisionProject.service.repositories.ProductRepository;
import com.DigitalVisionProject.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemService cartItemService;

    private final UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService,
                       ProductRepository productRepository, UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart addFirstProductToCart(Long userId, Long productId, int quantity){
        Cart cart = new Cart();
        List<CartItem> items = cart.getCartItems();
        CartItem cartItem = cartItemService.addCartItem(productId,quantity);
        items.add(cartItem);
        cart.setCartItems(items);
        Cart savedCart = cartRepository.save(cart);

        User user = userRepository.getReferenceById(userId);
        user.setCartId(savedCart.getId());
        userRepository.save(user);

        return savedCart;
    }
    public Cart addProductToExistingCart(Long userId,Long productId,int quantity){
        User user = userRepository.getReferenceById(userId);
        Cart cart = cartRepository.getReferenceById(user.getCartId());

        List<CartItem> items = cart.getCartItems();
        CartItem newItem = cartItemService.addCartItem(productId,quantity);
        items.add(newItem);
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
        User user = userRepository.getReferenceById(userId);
        Cart cart = cartRepository.getReferenceById(user.getCartId());
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


    public void deleteCart(Long userId){
        User user = userRepository.getReferenceById(userId);
        Cart cart = cartRepository.getReferenceById(user.getCartId());
        for (CartItem item: cart.getCartItems()) {
            cartItemService.deleteCartItem(item.getId());
        }
        cartRepository.deleteById(cart.getId());
        user.setCartId(null);
        userRepository.save(user);
    }



}
