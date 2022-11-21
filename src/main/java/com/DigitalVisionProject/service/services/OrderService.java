package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.OrderListDTO;
import com.DigitalVisionProject.service.models.*;
import com.DigitalVisionProject.service.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final CartRepository cartRepository;

    private final CartService cartService;

    private final PaymentService paymentService;


    @Autowired
    public OrderService(UserRepository userRepository, ProductRepository productRepository,
                        OrderRepository orderRepository, OrderedProductRepository orderedProductRepository,
                        CartRepository cartRepository,
                        CartService cartService, PaymentService paymentService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.paymentService = paymentService;
    }


    public List<Order> addOrderDetails(Long userId){
        List<Order> orderList = new ArrayList<>();
        OrderedProduct orderedProduct  = new OrderedProduct();
        List<Cart> cartList = cartRepository.findAll();
        cartList.forEach(cart -> {
            if(cart.getUserId().equals(userId)){
                Order newOrder = new Order();
                orderedProduct.setProductId(cart.getProductId());
                orderedProduct.setQuantityBought(cart.getQuantityAddedToCart());
                OrderedProduct savedOrderedProduct = orderedProductRepository.save(orderedProduct);
                newOrder.setOrderProductId(savedOrderedProduct.getId());
                newOrder.setUserId(userId);
                newOrder.setDeliveryFee(newOrder.deliveryCharge());
                double[] subTotal = cartService.calculateTotalPrice(userId);
                newOrder.setSubTotal(subTotal);
                orderRepository.save(newOrder);
                orderList.add(newOrder);
            }
        });
        return orderList;
    }

    public Order getOrdersById(Long id){
        return orderRepository.getReferenceById(id);
    }

    public OrderListDTO getOrders(Long userId){
        OrderListDTO orderListDTO = new OrderListDTO();
        orderListDTO.setUserId(userId);

        List<Long> orderIds = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        orders.forEach(order -> {
            if(order.getUserId().equals(userId)){
                orderIds.add(order.getOrderId());
            }
        });
        Order order = orderRepository.getReferenceById(userId);
        double[] deliveryCharge = order.getDeliveryFee();
        orderListDTO.setDeliveryCharge(deliveryCharge[0]);
        double[] subTotal = cartService.calculateTotalPrice(userId);
        orderListDTO.setSubTotal(subTotal[0]);
        double totalAmount = subTotal[0] + deliveryCharge[0];
        orderListDTO.setTotal(totalAmount);
        orderListDTO.setOrderIds(orderIds);
        return orderListDTO;
    }

    public void updateDeliveryAddressOfOrder(Long userId, String deliveryAddress){
        User user = userRepository.getReferenceById(userId);
        user.setDeliveryAddress(deliveryAddress);
    }

    public Long placeOrder(OrderListDTO orderListDTO){
        LocalDateTime dateOfOrder = LocalDateTime.now();
        return paymentService.addPayment(orderListDTO,dateOfOrder);
    }
}
