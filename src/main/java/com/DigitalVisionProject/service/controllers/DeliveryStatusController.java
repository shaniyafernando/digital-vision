package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.models.Courier;
import com.DigitalVisionProject.service.models.DeliveryStatus;
import com.DigitalVisionProject.service.services.DeliveryStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-status")
public class DeliveryStatusController {

   private final DeliveryStatusService deliveryStatusService;

   public DeliveryStatusController(DeliveryStatusService deliveryStatusService) {
        this.deliveryStatusService = deliveryStatusService;
   }



    @PutMapping()
    public  ResponseEntity<DeliveryStatus> updateStatus(@RequestBody Courier courier){
        DeliveryStatus updateStatus = deliveryStatusService.updateDeliveryStatus(courier);
        return new ResponseEntity<>(updateStatus, HttpStatus.OK);
    }

}
