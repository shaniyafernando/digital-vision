package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.OrderDTO;
import com.DigitalVisionProject.service.dtos.OrderListDTO;
import com.DigitalVisionProject.service.dtos.TotalDTO;
import com.DigitalVisionProject.service.models.*;
import com.DigitalVisionProject.service.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public OrderService(UserRepository userRepository, ProductRepository productRepository,
                        OrderRepository orderRepository, OrderedProductRepository orderedProductRepository, CartRepository cartRepository,
                        CartService cartService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
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

    public OrderListDTO getOrders(Long userId){
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderListOfUser = new ArrayList<>();
        double[] subTotal = cartService.calculateTotalPrice(userId);
        TotalDTO total = new TotalDTO();
        OrderListDTO orderListDTO = new OrderListDTO(orderListOfUser,total);
        total.setSubTotal(subTotal);
        orders.forEach(order -> {
            if(order.getUserId().equals(userId)){
                OrderedProduct orderedProduct = orderedProductRepository.getReferenceById(order.getOrderProductId());
                Product product = productRepository.getReferenceById(orderedProduct.getProductId());
                User user = userRepository.getReferenceById(userId);
                OrderDTO orderDTO = new OrderDTO(
                        order.getOrderId(),
                        user,
                        product,
                        orderedProduct.getQuantityBought());
                total.setDeliveryCharge(order.getDeliveryFee());
                orderListOfUser.add(orderDTO);
            }
        });
        return orderListDTO;
    }

    public void updateDeliveryAddressOfOrder(Long userId, String deliveryAddress){
        User user = userRepository.getReferenceById(userId);
        user.setDeliveryAddress(deliveryAddress);
    }
}
