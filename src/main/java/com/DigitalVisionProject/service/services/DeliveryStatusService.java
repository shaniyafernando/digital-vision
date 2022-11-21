package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.PaymentDTO;
import com.DigitalVisionProject.service.models.Courier;
import com.DigitalVisionProject.service.models.DeliveryStatus;
import com.DigitalVisionProject.service.models.OrderStatus;
import com.DigitalVisionProject.service.repositories.DeliveryStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DeliveryStatusService {

    private final CourierService courierService;
    private final DeliveryStatusRepository deliveryStatusRepository;

    @Autowired
    public DeliveryStatusService(CourierService courierService, DeliveryStatusRepository deliveryStatusRepository) {
        this.courierService = courierService;
        this.deliveryStatusRepository= deliveryStatusRepository;
    }
    public DeliveryStatus addDeliveryStatus (PaymentDTO paymentDTO){
        DeliveryStatus delivery = new DeliveryStatus();
        delivery.setPaymentId(paymentDTO.getPaymentId());
        delivery.setStatus(OrderStatus.ORDER_PROCESSING.name());
        delivery.setDate(LocalDate.now());
        return deliveryStatusRepository.save(delivery);
    }
    public DeliveryStatus updateDeliveryStatus (Courier courier ){
        DeliveryStatus deliveryStatus = deliveryStatusRepository.getReferenceById(courier.getDeliveryId());
        deliveryStatus.setStatus(courier.getStatus());
        return deliveryStatusRepository.save(deliveryStatus);
    }

}
