package com.example.automarket.service;

import com.example.automarket.domain.model.User;

public interface TokenService {
    void saveUserToken(User user, String jwtToken);
    void revokeAllUserTokens(User user);
}