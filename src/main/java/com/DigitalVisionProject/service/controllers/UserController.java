package com.DigitalVisionProject.service.controllers;

import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userEntities = userService.findAllUsers();
        return new ResponseEntity<>(userEntities, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateEmployee(@RequestBody User user){
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
