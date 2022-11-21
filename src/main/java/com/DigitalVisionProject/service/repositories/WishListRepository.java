package com.DigitalVision.service.repositories;

import com.DigitalVision.service.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findAllByUserId(Long userId);
    WishList findByProductId(Long productId);
}
