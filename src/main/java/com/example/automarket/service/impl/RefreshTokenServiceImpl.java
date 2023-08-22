package com.example.automarket.service.impl;

import com.example.automarket.domain.TokenType;
import com.example.automarket.domain.model.RefreshToken;
import com.example.automarket.domain.model.User;
import com.example.automarket.repository.RefreshTokenRepository;
import com.example.automarket.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Optional<RefreshToken> findByTokenAndExpiredFalseAndRevokedFalse(String token) {
        return refreshTokenRepository.findByTokenAndExpiredFalseAndRevokedFalse(token);
    }

    public void saveUserToken(User user, String jwtToken) {
        var token = RefreshToken.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        refreshTokenRepository.save(token);
    }

    public void revokeAllUserTokens(User user) {
        var validUserTokens = refreshTokenRepository.findAllByUser_IdAndExpiredFalseAndRevokedFalse(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        refreshTokenRepository.saveAll(validUserTokens);
    }
}
