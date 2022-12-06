package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.PlaceOrderDTO;
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
    private final CartItemRepository cartItemRepository;

    private final CartService cartService;

    private final PaymentService paymentService;


    @Autowired
    public OrderService(AddressRepository addressRepository, ProductRepository productRepository,
                        OrderRepository orderRepository, OrderedProductRepository orderedProductRepository,
                        CartRepository cartRepository,
                        CartItemRepository cartItemRepository, CartService cartService, PaymentService paymentService) {
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
        this.paymentService = paymentService;
    }


    public PlaceOrderDTO addOrderDetails(Cart cart){
        Order newOrder = new Order();
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        cart.getCartItems().forEach(item ->{
            OrderedProduct orderedProduct = new OrderedProduct();
            orderedProduct.setProductId(item.getProductId());
            orderedProduct.setQuantityBought(item.getQuantityAddedToCart());
            OrderedProduct savedOrderedProduct = orderedProductRepository.save(orderedProduct);
            orderedProducts.add(savedOrderedProduct);


            Product product = productRepository.getReferenceById(item.getProductId());
            int updateInventory = product.getQuantity() - item.getQuantityAddedToCart();
            product.setQuantity(updateInventory);
        });

        newOrder.setOrderProducts(orderedProducts);
        newOrder.setSubTotal(cart.getTotal());
        newOrder.setDeliveryFee(20);
        newOrder.setUserId(cart.getUserId());
        newOrder.setDate(LocalDate.now());
        Order order = orderRepository.save(newOrder);
        return new PlaceOrderDTO(order.getId(), cart.getId());
    }

    public Order getOrderById(Long id){
        return orderRepository.getReferenceById(id);
    }


    public void updateDeliveryAddressOfOrder(Long userId, String deliveryAddress){
        Address user = (Address) addressRepository.findAll().stream().filter(address -> address.getUserId().equals(userId));
        user.setDeliveryAddress(deliveryAddress);
        addressRepository.save(user);
    }

    public String getDeliveryAddress(Long userId){
        Address user = (Address) addressRepository.findAll().stream().filter(address -> address.getUserId().equals(userId));
        return user.getDeliveryAddress();
    }


}
