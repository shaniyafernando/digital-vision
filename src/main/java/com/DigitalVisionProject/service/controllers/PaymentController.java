package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.PaymentDTO;
import com.DigitalVisionProject.service.models.Address;
import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.models.enums.PaymentType;
import com.DigitalVisionProject.service.repositories.PaymentRepository;
import com.DigitalVisionProject.service.services.PaymentService;
import com.DigitalVisionProject.service.services.email.OrderConfirmationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderConfirmationEmailService orderConfirmationEmailService;
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentController(PaymentService paymentService, OrderConfirmationEmailService orderConfirmationEmailService,
                             PaymentRepository paymentRepository) {
        this.paymentService = paymentService;
        this.orderConfirmationEmailService = orderConfirmationEmailService;
        this.paymentRepository = paymentRepository;
    }

    @PutMapping()
    public ResponseEntity<Address> updateBillingAddressForPayment(@RequestBody Address address){
        Address updatedUser = paymentService.updateBillingAddressForPayment(
                address.getId(),address.getBillingAddress());
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Payment> pay(@RequestBody PaymentDTO payment){
        Payment payed = paymentService.pay(payment);
        return new ResponseEntity<>(payed,HttpStatus.OK);
    }

//    for testing only
    @PostMapping("/confirm")
    public ResponseEntity<?> orderConfirmation(@RequestBody Payment payment){
        orderConfirmationEmailService.sendOrderConfirmationEmail(payment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBillingAddressOfOrder(@PathVariable("id") Long id){
        String address = paymentService.getBillingAddress(id);
        return new ResponseEntity<>(address ,HttpStatus.OK);
    }

}