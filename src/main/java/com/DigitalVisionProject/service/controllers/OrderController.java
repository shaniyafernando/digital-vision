package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.OrderDTO;
import com.DigitalVisionProject.service.dtos.OrderListDTO;
import com.DigitalVisionProject.service.models.Order;
import com.DigitalVisionProject.service.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<List<Order>> checkout(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("userId");
        System.out.println(id);
        List<Order> newOrders = orderService.addOrderDetails((long) id);
        System.out.println(newOrders);
        return new ResponseEntity<>(newOrders,HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<OrderListDTO> getOrderDetails(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("id");
        System.out.println(id);
        OrderListDTO orders = orderService.getOrders((long) id);
        System.out.println(orders);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateDeliveryAddressOfOrder(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("id");
        String address = (String) payload.get("deliveryAddress");
        orderService.updateDeliveryAddressOfOrder((long) id,address);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
