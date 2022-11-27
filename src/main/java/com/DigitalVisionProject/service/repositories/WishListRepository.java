package com.DigitalVisionProject.service.repositories;


import com.DigitalVisionProject.service.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
}
