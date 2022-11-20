package com.DigitalVisionProject.service.repositories;

import com.DigitalVisionProject.service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserById(Long id);

    User findByEmail(String email);

}
