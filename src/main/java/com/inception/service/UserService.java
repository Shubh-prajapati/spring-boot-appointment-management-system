package com.inception.service;

import com.inception.exception.UserException;
import com.inception.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User> getAllUser();
    void deleteUser(Long id) throws Exception;
    User updateUser(Long id, User user) throws UserException;
}
