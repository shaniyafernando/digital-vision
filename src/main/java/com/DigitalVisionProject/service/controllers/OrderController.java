package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.models.Order;
import com.DigitalVisionProject.service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<Order> checkout(@RequestBody Cart cart){
        Order newOrder = orderService.addOrderDetails(cart);
        return new ResponseEntity<>(newOrder,HttpStatus.OK);
    }

//    @PostMapping("/list")
//    public ResponseEntity<OrderListDTO> getOrderDetails(@RequestBody Map<String, Object> payload){
//        int id = (int) payload.get("id");
//        System.out.println(id);
//        OrderListDTO orders = orderService.getOrders((long) id);
//        System.out.println(orders);
//        return new ResponseEntity<>(orders,HttpStatus.OK);
//    }

    @PostMapping("/one")
    public ResponseEntity<Order> getOrderList(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("id");
        Order order = orderService.getOrderById( (long)id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateDeliveryAddressOfOrder(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("id");
        String address = (String) payload.get("deliveryAddress");
        orderService.updateDeliveryAddressOfOrder((long) id,address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){
        Order placedOrder = orderService.placeOrder(order);
        return new ResponseEntity<>(placedOrder,HttpStatus.OK);
    }
}
