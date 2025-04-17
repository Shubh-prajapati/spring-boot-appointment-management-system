package com.inception.controller;

import com.inception.model.User;
import com.inception.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/user")
    public User cretedUser(@RequestBody @Valid User user) {
        return userRepository.save(user);

    }
    @GetMapping("/api/user")
    public List<User> getUser() {
        return userRepository.findAll();

    }
    @GetMapping("/api/user/{userId}")
    public User getUserById(@PathVariable("userId") Long id) throws Exception {
        Optional<User> otp =userRepository.findById(id);
        if(otp.isPresent()){
            return otp.get();
        }
     throw new Exception("user not found");
    }

    @PutMapping("/api/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {
        Optional<User> otp=userRepository.findById(id);
        if(otp.isEmpty()){
            throw new Exception("user not found with id"+id);
        }
        User existingUser=otp.get();

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        
        return userRepository.save(existingUser);

    }

    @DeleteMapping("/api/user/{id}")
    public String deleteUserById(@PathVariable Long id)throws Exception{
        Optional<User> otp=userRepository.findById(id);
        if(otp.isEmpty()){
            throw new Exception("user not exits with id"+id);
        }
        userRepository.deleteById(otp.get().getId());
        return "User Deleted";
    }






}
