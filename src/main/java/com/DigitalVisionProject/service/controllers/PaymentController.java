package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.OrderListDTO;
import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.models.PaymentType;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateBillingAddressForPayment(@RequestBody Map<String, Object> payload){
        int userId = (int) payload.get("userId");
        String billingAddress = (String) payload.get("billingAddress");
        User updatedUser = paymentService.updateBillingAddressForPayment((long) userId,billingAddress);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> pay(@RequestBody Map<String, Object> payload){
        int id = (int) payload.get("id");
        String paymentType = (String) payload.get("paymentType");
        paymentService.pay( paymentType, (long) id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}