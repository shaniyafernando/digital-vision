package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.PaymentDTO;
import com.DigitalVisionProject.service.models.Address;
import com.DigitalVisionProject.service.models.Cart;
import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.models.enums.PaymentType;
import com.DigitalVisionProject.service.repositories.*;
import com.DigitalVisionProject.service.services.email.OrderConfirmationEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AddressRepository addressRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final OrderConfirmationEmailService orderConfirmationEmailService;

    private final DeliveryStatusService deliveryStatusService;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository,
                          AddressRepository addressRepository,
                          CartItemRepository cartItemRepository, CartRepository cartRepository, OrderConfirmationEmailService orderConfirmationEmailService,
                          DeliveryStatusService deliveryStatusService) {
        this.paymentRepository = paymentRepository;
        this.addressRepository = addressRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.orderConfirmationEmailService = orderConfirmationEmailService;
        this.deliveryStatusService = deliveryStatusService;
    }

    public Address updateBillingAddressForPayment(Long userId, String billingAddress){
        Address user = (Address) addressRepository.findAll().stream().filter(address -> address.getUserId().equals(userId));
        user.setBillingAddress(billingAddress);
        return addressRepository.save(user);
    }

    public String getBillingAddress(Long userId){
        Address user = (Address) addressRepository.findAll().stream().filter(address -> address.getUserId().equals(userId));
        return user.getBillingAddress();
    }

    public void pay(PaymentDTO payment){
        Payment newPayment = new Payment(
                LocalDate.now(),
                payment.getAmount(),
                payment.getOrderId(),
                PaymentType.valueOf(payment.getPaymentType()),
                UUID.randomUUID()
        );
        Payment savedPayment = paymentRepository.save(newPayment);

        Cart cart = cartRepository.getReferenceById(payment.getCartId());
        cartItemRepository.deleteAll(cart.getCartItems());
        cartRepository.delete(cart);

        orderConfirmationEmailService.sendOrderConfirmationEmail(savedPayment);
        deliveryStatusService.addDeliveryStatus(savedPayment);
    }




}

