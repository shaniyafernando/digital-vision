package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.*;
import com.DigitalVisionProject.service.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final CartRepository cartRepository;

    private final CartService cartService;

    private final PaymentService paymentService;


    @Autowired
    public OrderService(AddressRepository addressRepository, ProductRepository productRepository,
                        OrderRepository orderRepository, OrderedProductRepository orderedProductRepository,
                        CartRepository cartRepository,
                        CartService cartService, PaymentService paymentService) {
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.paymentService = paymentService;
    }


    public Order addOrderDetails(Cart cart){
        Order newOrder = new Order();
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        cart.getCartItems().forEach(item ->
        {
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setProduct(item.getProduct());
            orderedProduct.setQuantityBought(item.getQuantityAddedToCart());
            orderedProducts.add(orderedProduct);
        });
        newOrder.setOrderProducts(orderedProducts);
        newOrder.setSubTotal(cart.getTotal());
        newOrder.setUserId(cart.getId());
        return orderRepository.save(newOrder);
    }

    public Order getOrderById(Long id){
        return orderRepository.getReferenceById(id);
    }


    public void updateDeliveryAddressOfOrder(Long userId, String deliveryAddress){
        Address user = addressRepository.getReferenceById(userId);
        user.setDeliveryAddress(deliveryAddress);
        addressRepository.save(user);
    }

    public Order placeOrder(Order order){
        Order orderPlaced = orderRepository.getReferenceById(order.getId());
        orderPlaced.setDate(LocalDate.now());
        return orderRepository.save(order);
    }
}
