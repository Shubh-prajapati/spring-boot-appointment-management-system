package com.inception.controller;

import com.inception.model.User;
import com.inception.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity<User> createdUser(@RequestBody @Valid User user) {
        User createUser= userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);

    }
    @GetMapping("/api/user")
    public  ResponseEntity<List<User>> getUser() {
        List<User> user=userService.getAllUser();
        return  new ResponseEntity<>(user, HttpStatus.OK);

    }
    @GetMapping("/api/user/{userId}")
    public  ResponseEntity<User> getUserById(@PathVariable("userId") Long id) throws Exception {
        User user =userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {
        User updateUser=userService.updateUser(id,user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);

    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id)throws Exception{
        userService.deleteUser(id);
        return new ResponseEntity<>("User Deleted",HttpStatus.ACCEPTED);

    }


}
