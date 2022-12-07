package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.PlaceOrderDTO;
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
    public ResponseEntity<PlaceOrderDTO> checkout(@RequestBody Cart cart){
        PlaceOrderDTO newOrder = orderService.addOrderDetails(cart);
        return new ResponseEntity<>(newOrder,HttpStatus.OK);
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<String> getDeliveryAddressOfOrder(@PathVariable("id") Long id){
        String address = orderService.getDeliveryAddress(id);
        return new ResponseEntity<>(address ,HttpStatus.OK);
    }

}
