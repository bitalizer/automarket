package com.example.automarket.controller;

import com.example.automarket.domain.dto.request.AuthenticationRequest;
import com.example.automarket.domain.dto.request.RegistrationRequest;
import com.example.automarket.domain.dto.request.TokenRefreshRequest;
import com.example.automarket.domain.dto.response.JwtAuthenticationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationControllerIntegrationTest {

    private static RegistrationRequest registrationRequest;
    private static String refreshToken; // Store the refresh token
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        registrationRequest = new RegistrationRequest();
        registrationRequest.setName("John Doe");
        registrationRequest.setEmail("valid-email@example.com");
        registrationRequest.setPassword("password");
    }

    @Order(1)
    @Test
    void testRegister_Success() throws Exception {
        // Prepare registration request with valid email


        // Perform the registration request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.access_token").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.refresh_token").isString());
    }

    @Test
    void testRegister_InvalidEmail() throws Exception {
        // Prepare registration request with invalid email
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setName("John Doe");
        registrationRequest.setEmail("invalid-email");
        registrationRequest.setPassword("password");

        // Perform the registration request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testAuthenticate_NonExistingEmail() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("non-existing-email@example.com");
        authenticationRequest.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void testAuthenticate_IncorrectPassword() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(registrationRequest.getEmail());
        authenticationRequest.setPassword("incorrect-password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Order(2)
    @Test
    void testAuthenticate_Success() throws Exception {

        // Prepare authentication request
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(registrationRequest.getEmail());
        authenticationRequest.setPassword(registrationRequest.getPassword());


        // Perform the authentication request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.access_token").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.refresh_token").isString())
                .andDo(result -> {
                    // Store the refresh token for later use
                    JwtAuthenticationResponse jwtResponse = objectMapper.readValue(result.getResponse().getContentAsString(), JwtAuthenticationResponse.class);
                    refreshToken = jwtResponse.getRefreshToken();
                });
    }

    @Order(3)
    @Test
    void testRefreshToken_Success() throws Exception {
        TokenRefreshRequest tokenRefreshRequest = TokenRefreshRequest.builder()
                .refreshToken(refreshToken)
                .build();// Mock request body

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/refresh-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tokenRefreshRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.refresh_token").value(tokenRefreshRequest.getRefreshToken()));
    }
}