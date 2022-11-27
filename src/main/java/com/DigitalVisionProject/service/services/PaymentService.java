package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.OrderListDTO;
import com.DigitalVisionProject.service.dtos.PaymentDTO;
import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.models.PaymentType;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.repositories.PaymentRepository;
import com.DigitalVisionProject.service.repositories.UserRepository;
import com.DigitalVisionProject.service.services.email.OrderConfirmationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final OrderConfirmationEmailService orderConfirmationEmailService;

    private final DeliveryStatusService deliveryStatusService;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository, OrderConfirmationEmailService orderConfirmationEmailService, DeliveryStatusService deliveryStatusService) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.orderConfirmationEmailService = orderConfirmationEmailService;
        this.deliveryStatusService = deliveryStatusService;
    }

    public User updateBillingAddressForPayment(Long userId, String billingAddress){
        User user = userRepository.getReferenceById(userId);
        user.setBillingAddress(billingAddress);
        return userRepository.save(user);
    }

    public void pay(Payment payment){
        Payment newPayment = new Payment(
                LocalDate.now(),
                payment.getAmount(),
                payment.getOrder(),
                payment.getPaymentType(),
                payment.getInvoiceNumber()
        );
        Payment savedPayment = paymentRepository.save(newPayment);
        orderConfirmationEmailService.sendOrderConfirmationEmail(savedPayment);
        deliveryStatusService.addDeliveryStatus(savedPayment);
    }




}

