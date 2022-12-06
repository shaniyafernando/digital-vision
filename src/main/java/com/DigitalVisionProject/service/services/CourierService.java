package com.DigitalVisionProject.service.services;


import com.DigitalVisionProject.service.models.Courier;
import com.DigitalVisionProject.service.models.DeliveryStatus;
import com.DigitalVisionProject.service.models.enums.OrderStatus;
import com.DigitalVisionProject.service.repositories.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierService {
    private final CourierRepository courierRepository;

    @Autowired
    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public void addNewCourier(DeliveryStatus deliveryStatus){
        Courier courier = new Courier(deliveryStatus.getId(), deliveryStatus.getStatus());
        courierRepository.save(courier);
    }

    public Courier updateStatus(String Status,Long courierID){
        Courier courier = courierRepository.getReferenceById(courierID);
        courier.setStatus(OrderStatus.valueOf(Status).name());
        return courierRepository.save(courier);
    }


}
