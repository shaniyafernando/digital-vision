package com.DigitalVisionProject.service.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table
public class Courier implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courierId;
    private Long deliveryId;
    private String status;

    public Courier(Long courierId, Long deliveryId, String status) {
        this.courierId = courierId;
        this.deliveryId = deliveryId;
        this.status = status;
    }

    public Courier(Long deliveryId, String status) {
        this.deliveryId = deliveryId;
        this.status = status;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}