package com.DigitalVisionProject.service.models;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cart extends User {

    @OneToMany
    private List<CartItem> cartItems;

    private double total;

    public Cart() {
    }

    public double calculatePriceForTheNumberOfPiecesBoughtForCartItem(CartItem cartItem) {
        return cartItem.getQuantityAddedToCart() * cartItem.getProduct().getPrice();
    }

    public double calculateTotalPrice(List<CartItem> cartItems) {
        double sum = 0.0;
        for (CartItem cartItem : cartItems) {
            double amount = calculatePriceForTheNumberOfPiecesBoughtForCartItem(cartItem);
            sum += amount;
        }
        return sum;
    }

    public Cart(Long id, List<CartItem> cartItems, double total) {
        super(id);
        this.cartItems = cartItems;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}