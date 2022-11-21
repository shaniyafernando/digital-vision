package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.OrderListDTO;
import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.models.PaymentType;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.repositories.PaymentRepository;
import com.DigitalVisionProject.service.repositories.UserRepository;
import com.DigitalVisionProject.service.services.email.OrderConfirmationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final OrderConfirmationEmailService orderConfirmationEmailService;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository, OrderConfirmationEmailService orderConfirmationEmailService) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.orderConfirmationEmailService = orderConfirmationEmailService;
    }

    public Long addPayment(OrderListDTO orderListDTO, LocalDateTime date){
        Payment savedPaymentRecord = new Payment();
        savedPaymentRecord.setDateOfOrder(date);
        savedPaymentRecord.setUserId(orderListDTO.getUserId());
        savedPaymentRecord.setPaymentAmount(orderListDTO.getTotal());
        Payment payment = paymentRepository.save(savedPaymentRecord);
        return payment.getPaymentId();
    }

    public User updateBillingAddressForPayment(Long userId, String billingAddress){
        User user = userRepository.getReferenceById(userId);
        user.setBillingAddress(billingAddress);
        return userRepository.save(user);
    }

    public void pay(String paymentType,Long id){
        Payment payment = paymentRepository.getReferenceById(id);
        payment.setPaymentType(PaymentType.valueOf(paymentType));
        Payment updatedPayment = paymentRepository.save(payment);
        orderConfirmationEmailService.sendOrderConfirmationEmail(updatedPayment);
    }




}

