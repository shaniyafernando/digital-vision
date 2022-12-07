package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.models.Courier;
import com.DigitalVisionProject.service.services.DeliveryStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courier")
@Service
public class CourierController {

    private final DeliveryStatusService deliveryStatusService;

    public CourierController(DeliveryStatusService deliveryStatusService) {
        this.deliveryStatusService = deliveryStatusService;
    }

    @PutMapping()
    public  ResponseEntity<Courier> updateStatus(@RequestBody String status, Long id){
        Courier updateStatus = deliveryStatusService.updateStatus(status,id);
        return new ResponseEntity<>(updateStatus, HttpStatus.OK);
    }


}
