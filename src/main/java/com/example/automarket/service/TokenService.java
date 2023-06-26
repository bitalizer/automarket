package com.example.automarket.service;

import com.example.automarket.domain.model.Token;
import com.example.automarket.domain.model.User;

import java.util.Optional;

public interface TokenService {

    Optional<Token> findByToken(String token);

    void saveUserToken(User user, String jwtToken);

    void revokeAllUserTokens(User user);
}