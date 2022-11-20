package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllOrders(){
        List<Payment> payments = paymentService.findAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    //    @GetMapping("/find/{id}")
//    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id){
//        UserEntity userEntity = userService.findUserById(id);
//        return new ResponseEntity<>(userEntity,HttpStatus.OK);
//    }
//
    @PostMapping("/add")
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment){
        paymentService.addPayment(payment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}