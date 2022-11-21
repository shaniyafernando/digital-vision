package com.DigitalVisionProject.service.repositories;

import com.DigitalVisionProject.service.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface CartRepository extends JpaRepository<Cart, Long> {

}
