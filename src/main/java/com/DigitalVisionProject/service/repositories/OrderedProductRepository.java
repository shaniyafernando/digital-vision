package com.DigitalVisionProject.service.repositories;

import com.DigitalVisionProject.service.models.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface OrderedProductRepository extends JpaRepository<OrderedProduct,Long> {
}
