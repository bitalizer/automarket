package com.example.automarket.service;

import com.example.automarket.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    void createNewUser(User user);

    Optional<User> getUserById(Long userId);
    User updateUser(User user, User newUser);
    void deleteUser(User user);
    UserDetailsService userDetailsService();
}