package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.repositories.PaymentRepository;
import com.DigitalVisionProject.service.services.email.OrderConfirmationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderConfirmationEmailService orderConfirmationEmailService;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, OrderConfirmationEmailService orderConfirmationEmailService) {
        this.paymentRepository = paymentRepository;
        this.orderConfirmationEmailService = orderConfirmationEmailService;
    }

    public void addPayment(Payment payment){
        Payment savedPaymentRecord = paymentRepository.save(payment);
        orderConfirmationEmailService.sendOrderConfirmationEmail(savedPaymentRecord);
    }

    public List<Payment> findAllPayments(){
        return paymentRepository.findAll();
    }
}

