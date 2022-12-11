package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.models.DeliveryStatus;
import com.DigitalVisionProject.service.models.Payment;
import com.DigitalVisionProject.service.services.DeliveryStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/delivery-status")
public class DeliveryStatusController {

   private final DeliveryStatusService deliveryStatusService;

   public DeliveryStatusController(DeliveryStatusService deliveryStatusService) {
        this.deliveryStatusService = deliveryStatusService;
   }

    @GetMapping("/{paymentId}")
    public  ResponseEntity<DeliveryStatus> getDeliveryStatus(@PathVariable("paymentId") Long paymentId){
        DeliveryStatus updateStatus = deliveryStatusService.getDeliveryStatus(paymentId);
        return new ResponseEntity<>(updateStatus, HttpStatus.OK);
    }

    //testing only
    @PostMapping
    public  ResponseEntity<DeliveryStatus> addDeliveryStatus(@RequestBody Payment payment){
        deliveryStatusService.addDeliveryStatus(payment);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
