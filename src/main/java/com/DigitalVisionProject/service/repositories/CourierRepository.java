package com.DigitalVisionProject.service.repositories;

import com.DigitalVisionProject.service.models.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
}
