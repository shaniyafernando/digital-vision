package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.dtos.LoginRequest;
import com.DigitalVisionProject.service.dtos.RegistrationRequest;
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

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request){
        String token = registrationService.register(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token){
        String confirmation = registrationService.confirmToken(token);
        return new ResponseEntity<>(confirmation, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest){
        User user = userService.signIn(loginRequest);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/role")
    public ResponseEntity<User> updateUserRole(@RequestBody Map<String, Object> payload){
        int userId = (int) payload.get("userId");
        String role = (String) payload.get("role");
        User user = userService.updateUserRole((long) userId,role);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
