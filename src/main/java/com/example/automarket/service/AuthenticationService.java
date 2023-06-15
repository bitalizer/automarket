package com.example.automarket.service;

import com.example.automarket.domain.dto.request.AuthenticationRequest;
import com.example.automarket.domain.dto.request.RegistrationRequest;
import com.example.automarket.domain.dto.response.JwtAuthenticationResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse register(RegistrationRequest request);

    JwtAuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}
