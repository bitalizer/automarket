package com.example.automarket.service.impl;

import com.example.automarket.domain.model.User;
import com.example.automarket.repository.UserRepository;
import com.example.automarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Caching(
            evict = {
                    @CacheEvict(value = "users", allEntries = true)
            },
            put = {
                    @CachePut(value = "user-single", key = "#result.id")
            }
    )
    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    @Cacheable(value = "user-single", key = "#userId")
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @CachePut(value = "user-single", key = "#user.id")
    public User updateUser(User user, User newUser) {
        // Update the necessary fields of the user object
        // user.setName(newUser.getName());
        // ...
        return userRepository.save(user);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "users", allEntries = true),
                    @CacheEvict(value = "user-single", key = "#user.id")
            }
    )
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDetailsService userDetailsService() {

        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}