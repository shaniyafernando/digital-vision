package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.PlaceOrderDTO;
import com.DigitalVisionProject.service.models.Address;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.models.CartItem;
import com.DigitalVisionProject.service.models.Order;
import com.DigitalVisionProject.service.services.CartItemService;
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
    private final CartItemService cartItemService;

    @Autowired
    public OrderController(OrderService orderService, CartItemService cartItemService) {
        this.orderService = orderService;
        this.cartItemService = cartItemService;
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

    @PutMapping()
    public ResponseEntity<Address> updateDeliveryAddressOfOrder(@RequestBody Address address){
        Address place = orderService.updateDeliveryAddressOfOrder(address.getId(), address.getDeliveryAddress());
        return new ResponseEntity<>(place,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable("id") Long id){
        Address address = orderService.getAddress(id);
        return new ResponseEntity<>(address ,HttpStatus.OK);
    }

    @PutMapping("/item")
    public ResponseEntity<CartItem> updateQuantityInCartItem(@RequestBody CartItem cartItem){
        CartItem item = cartItemService.updateQuantityInCartItem(cartItem);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }


}
