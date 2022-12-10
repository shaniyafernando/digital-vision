package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.LoginRequest;
import com.DigitalVisionProject.service.dtos.RegistrationRequest;
import com.DigitalVisionProject.service.dtos.TokenResponse;
import com.DigitalVisionProject.service.dtos.UserDTO;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.services.RegistrationService;
import com.DigitalVisionProject.service.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/authentication")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegistrationRequest request){
        TokenResponse token = registrationService.register(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/token={token}")
    public ResponseEntity<TokenResponse> confirm(@PathVariable("token") String token){
        TokenResponse confirmation = registrationService.confirmToken(token);
        return new ResponseEntity<>(confirmation, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest){
        User user = userService.signIn(loginRequest);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/role")
    public ResponseEntity<User> updateUserRole(@RequestBody UserDTO userdto){
        User user = userService.updateUserRole(userdto.getId(),userdto.getRole());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
