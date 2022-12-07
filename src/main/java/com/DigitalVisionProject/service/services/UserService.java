package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.LoginRequest;
import com.DigitalVisionProject.service.exceptions.UserNotFoundException;
import com.DigitalVisionProject.service.models.Address;
import com.DigitalVisionProject.service.models.ConfirmationToken;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.models.enums.PaymentType;
import com.DigitalVisionProject.service.models.enums.Role;
import com.DigitalVisionProject.service.repositories.AddressRepository;
import com.DigitalVisionProject.service.repositories.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService{

    private final UserRepository appUserRepository;
    private final AddressRepository addressRepository;

    private final ConfirmationTokenService confirmationTokenService;

    public UserService(UserRepository appUserRepository,
                       ConfirmationTokenService confirmationTokenService, AddressRepository addressRepository) {
        this.appUserRepository = appUserRepository;
        this.confirmationTokenService = confirmationTokenService;
        this.addressRepository = addressRepository;
    }



    public String signUpUser(User appUser, Address address){
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail()).isPresent();

        if(userExists){
            throw new IllegalStateException("email already taken");
        }

        appUser.setPassword(appUser.getPassword());

        appUserRepository.save(appUser);

        address.setDeliveryAddress(address.getDeliveryAddress());
        address.setBillingAddress(address.getBillingAddress());
        address.setUserId(address.getUserId());
        addressRepository.save(address);

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

    public User signIn(LoginRequest loginRequest){
        boolean userExists = appUserRepository
                .findByEmail(loginRequest.getEmail()).isPresent();
        if(!userExists){
            throw new UserNotFoundException("User not found: "+ loginRequest.getEmail());
        }

        User appUser = (User) appUserRepository.findAll().stream().filter(
                user -> user.getEmail().equals(loginRequest.getEmail()));
        if(!appUser.getPassword().equals(loginRequest.getPassword()) ||
                !appUser.getUsername().equals(loginRequest.getUsername())){
            throw new UserNotFoundException("Invalid credentials");
        }
        return appUser;
    }

    public User updateUserRole(Long userId, String role){
        User user = appUserRepository.getReferenceById(userId);
        user.setRole(Role.valueOf(role));
        return appUserRepository.save(user);
    }

    public List<User> getUsers(){
        return appUserRepository.findAll();
    }
}
