package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.models.Courier;
import com.DigitalVisionProject.service.services.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courier")
@Service
public class CourierController {

    private CourierService courierService;

    @Autowired
    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }



    @PutMapping()
    public  ResponseEntity<Courier> updateStatus(@RequestBody String status, Long id){
        Courier updateStatus = courierService.updateStatus(status,id);
        return new ResponseEntity<>(updateStatus, HttpStatus.OK);
    }


}
