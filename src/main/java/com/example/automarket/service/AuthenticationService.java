package com.example.automarket.service;

import com.example.automarket.domain.dto.request.AuthenticationRequest;
import com.example.automarket.domain.dto.request.RegistrationRequest;
import com.example.automarket.domain.dto.request.TokenRefreshRequest;
import com.example.automarket.domain.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse register(RegistrationRequest request);

    JwtAuthenticationResponse authenticate(AuthenticationRequest request);

    JwtAuthenticationResponse refreshToken(TokenRefreshRequest request);
}
