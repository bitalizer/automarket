package com.example.automarket.service.impl;

import com.example.automarket.domain.Role;
import com.example.automarket.domain.dto.request.AuthenticationRequest;
import com.example.automarket.domain.dto.request.RegistrationRequest;
import com.example.automarket.domain.dto.request.TokenRefreshRequest;
import com.example.automarket.domain.dto.response.JwtAuthenticationResponse;
import com.example.automarket.domain.model.User;
import com.example.automarket.repository.UserRepository;
import com.example.automarket.service.AuthenticationService;
import com.example.automarket.service.JwtService;
import com.example.automarket.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final RefreshTokenService refreshTokenService;

	private final AuthenticationManager authenticationManager;

	@Override
	public JwtAuthenticationResponse register(RegistrationRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}

		var user = User.builder()
			.name(request.getName())
			.email(request.getEmail())
			.password(passwordEncoder.encode(request.getPassword()))
			.role(Role.USER)
			.enabled(true)
			.build();

		var savedUser = userRepository.save(user);
		var accessToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);

		refreshTokenService.saveUserToken(savedUser, refreshToken);

		return JwtAuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
	}

	@Override
	public JwtAuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		var user = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
		var accessToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);

		refreshTokenService.revokeAllUserTokens(user);

		if (refreshTokenService.findByTokenAndExpiredFalseAndRevokedFalse(refreshToken).isEmpty()) {
			refreshTokenService.saveUserToken(user, refreshToken);
		}

		return JwtAuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
	}

	@Override
	public JwtAuthenticationResponse refreshToken(TokenRefreshRequest request) {

		String refreshToken = request.getRefreshToken();

		String userEmail = jwtService.extractUsername(refreshToken);

		var user = userRepository.findByEmail(userEmail)
			.orElseThrow(() -> new AuthenticationCredentialsNotFoundException("E-mail not found!"));

		if (!jwtService.isTokenValid(refreshToken, user)) {
			throw new AuthenticationCredentialsNotFoundException("Invalid token!");
		}

		refreshTokenService.findByTokenAndExpiredFalseAndRevokedFalse(refreshToken)
			.orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Refresh token is not in database!"));

		var accessToken = jwtService.generateToken(user);

		return JwtAuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
	}

}