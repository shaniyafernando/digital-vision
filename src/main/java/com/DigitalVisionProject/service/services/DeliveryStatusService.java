package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.Courier;
import com.DigitalVisionProject.service.models.DeliveryStatus;
import com.DigitalVisionProject.service.models.enums.OrderStatus;
import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.repositories.CourierRepository;
import com.DigitalVisionProject.service.repositories.DeliveryStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryStatusService {

    private final CourierRepository courierRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;

    @Autowired
    public DeliveryStatusService( CourierRepository courierRepository,
                                  DeliveryStatusRepository deliveryStatusRepository) {
        this.courierRepository = courierRepository;
        this.deliveryStatusRepository= deliveryStatusRepository;
    }
    public void addDeliveryStatus (Payment payment){
        DeliveryStatus delivery = new DeliveryStatus();
        delivery.setPaymentId(payment.getId());
        delivery.setStatus(OrderStatus.ORDER_PROCESSING.name());
        delivery.setDate(LocalDate.now());
        delivery.setUserId(payment.getUserId());
        DeliveryStatus saved = deliveryStatusRepository.save(delivery);
        addNewCourier(saved);
    }
    public DeliveryStatus updateDeliveryStatus (Courier courier ){
        DeliveryStatus deliveryStatus =
                deliveryStatusRepository.getReferenceById(courier.getDeliveryId());
        deliveryStatus.setStatus(courier.getStatus());
        return deliveryStatusRepository.save(deliveryStatus);
    }

    public DeliveryStatus getDeliveryStatus(Long paymentId){
        List<DeliveryStatus> list = deliveryStatusRepository.findAll();
        DeliveryStatus status = new DeliveryStatus();
        list.forEach(deliveryStatus -> {
            if(deliveryStatus.getPaymentId().equals(paymentId)){
                status.setId(deliveryStatus.getId());
                status.setStatus(deliveryStatus.getStatus());
                status.setDate(deliveryStatus.getDate());
            }
        });
        return status;
    }

    public void addNewCourier(DeliveryStatus deliveryStatus){
        Courier courier = new Courier(deliveryStatus.getId(), deliveryStatus.getStatus());
        courierRepository.save(courier);
    }

    public Courier updateStatus(String Status,Long courierID){
        Courier courier = courierRepository.getReferenceById(courierID);
        courier.setStatus(OrderStatus.valueOf(Status).name());
        Courier saved = courierRepository.save(courier);
        updateDeliveryStatus(saved);
        return saved;
    }

}
