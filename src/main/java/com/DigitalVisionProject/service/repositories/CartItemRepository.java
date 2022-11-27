package com.DigitalVisionProject.service.repositories;

import com.DigitalVisionProject.service.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
