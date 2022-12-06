package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.models.ConfirmationToken;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.repositories.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService{

    private final UserRepository appUserRepository;

    private final ConfirmationTokenService confirmationTokenService;

    public UserService(UserRepository appUserRepository,
                       ConfirmationTokenService confirmationTokenService) {
        this.appUserRepository = appUserRepository;
        this.confirmationTokenService = confirmationTokenService;
    }



    public String signUpUser(User appUser){
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail()).isPresent();

        if(userExists){
            throw new IllegalStateException("email already taken");
        }

        appUser.setPassword(appUser.getPassword());

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser

        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public void deleteUser(Long id){
        appUserRepository.deleteById(id);
    }
}
