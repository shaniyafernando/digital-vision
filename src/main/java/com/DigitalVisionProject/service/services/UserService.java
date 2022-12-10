package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.dtos.LoginRequest;
import com.DigitalVisionProject.service.exceptions.UserNotFoundException;
import com.DigitalVisionProject.service.models.Address;
import com.DigitalVisionProject.service.models.ConfirmationToken;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.models.enums.Role;
import com.DigitalVisionProject.service.repositories.AddressRepository;
import com.DigitalVisionProject.service.repositories.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService{

    private final UserRepository appUserRepository;
    private final AddressRepository addressRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public UserService(UserRepository appUserRepository,
                       ConfirmationTokenService confirmationTokenService, AddressRepository addressRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.confirmationTokenService = confirmationTokenService;
        this.addressRepository = addressRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    public String signUpUser(User appUser, Address address){
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail()).isPresent();

        if(userExists){
            throw new IllegalStateException("email already taken");
        }

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        User savedAppUser = appUserRepository.save(appUser);

        address.setDeliveryAddress(address.getDeliveryAddress());
        address.setBillingAddress(address.getBillingAddress());
        address.setUserId(savedAppUser.getId());
        addressRepository.save(address);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                savedAppUser
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
            throw new UserNotFoundException("User not found: " + loginRequest.getEmail());
        }

        List<User> users = appUserRepository.findAll();

        User appUser = new User();
        users.forEach(
                user -> {
                    if(user.getEmail().equals(loginRequest.getEmail())){
                        appUser.setId(user.getId());
                        appUser.setEmail(user.getEmail());
                        appUser.setPassword(user.getPassword());
                        appUser.setUsername(user.getUsername());
                        appUser.setRole(user.getRole());
                    }
                });

        boolean passwordMatches = bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword());
        boolean usernameMatches = appUser.getUsername().equals(loginRequest.getUsername());

        if(!usernameMatches || !passwordMatches){
            throw new UserNotFoundException("Invalid credentials");
        }
        return appUser;
    }

    public User updateUserRole(Long userId, String role){
        User user = appUserRepository.getReferenceById(userId);
        user.setRole(Role.valueOf(role));
        return appUserRepository.save(user);
    }

    public User getUser(Long id){
        return appUserRepository.getReferenceById(id);
    }

    public List<User> getUsers(){
        return appUserRepository.findAll();
    }
}
