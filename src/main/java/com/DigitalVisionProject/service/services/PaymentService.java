package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.Address;
import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.repositories.AddressRepository;
import com.DigitalVisionProject.service.repositories.PaymentRepository;
import com.DigitalVisionProject.service.repositories.UserRepository;
import com.DigitalVisionProject.service.services.email.OrderConfirmationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AddressRepository addressRepository;
    private final OrderConfirmationEmailService orderConfirmationEmailService;

    private final DeliveryStatusService deliveryStatusService;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, AddressRepository addressRepository,
                          OrderConfirmationEmailService orderConfirmationEmailService, DeliveryStatusService deliveryStatusService) {
        this.paymentRepository = paymentRepository;
        this.addressRepository = addressRepository;
        this.orderConfirmationEmailService = orderConfirmationEmailService;
        this.deliveryStatusService = deliveryStatusService;
    }

    public Address updateBillingAddressForPayment(Long userId, String billingAddress){
        Address user = addressRepository.getReferenceById(userId);
        user.setBillingAddress(billingAddress);
        return addressRepository.save(user);
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

