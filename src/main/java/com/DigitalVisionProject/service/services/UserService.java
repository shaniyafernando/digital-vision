package com.DigitalVisionProject.service.services;

import com.DigitalVisionProject.service.exceptions.UserNotFoundException;
import com.DigitalVisionProject.service.models.User;
import com.DigitalVisionProject.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return userRepository.save(newUser);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

//    to update the user table, you need to find the user in the database and then set the updated values.
//    reference product service
    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User findUserById(Long id){
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User by Id "+id
                +" was not found"));
    }

//    public UserDTO login(LoginDTO loginDetails){
//
//        return null;
//    }
}
