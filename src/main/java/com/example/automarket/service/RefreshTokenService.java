package com.example.automarket.service;

import com.example.automarket.domain.model.RefreshToken;
import com.example.automarket.domain.model.User;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByTokenAndExpiredFalseAndRevokedFalse(String token);

    void saveUserToken(User user, String jwtToken);

    void revokeAllUserTokens(User user);
}