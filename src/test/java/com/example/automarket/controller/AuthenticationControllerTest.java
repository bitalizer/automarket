package com.example.automarket.controller;

import com.example.automarket.domain.dto.request.AuthenticationRequest;
import com.example.automarket.domain.dto.request.RegistrationRequest;
import com.example.automarket.domain.dto.request.TokenRefreshRequest;
import com.example.automarket.domain.dto.response.JwtAuthenticationResponse;
import com.example.automarket.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = { AuthenticationController.class })
@ActiveProfiles({ "test" })
@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {

	@Autowired
	private AuthenticationController authenticationController;

	@MockBean
	private AuthenticationService authenticationService;

	/**
	 * Method under test: {@link AuthenticationController#register(RegistrationRequest)}
	 */
	@Test
	void testRegister() throws Exception {
		JwtAuthenticationResponse buildResult = JwtAuthenticationResponse.builder()
			.accessToken("ABC123")
			.refreshToken("ABC123")
			.build();
		when(authenticationService.register(Mockito.any())).thenReturn(buildResult);

		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setEmail("jane.doe@example.org");
		registrationRequest.setName("Name");
		registrationRequest.setPassword("iloveyou");
		String content = (new ObjectMapper()).writeValueAsString(registrationRequest);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		MockMvcBuilders.standaloneSetup(authenticationController)
			.build()
			.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.content()
				.string("{\"access_token\":\"ABC123\",\"refresh_token\":\"ABC123\"}"));
	}

	@Test
	void testRegister_MissingEmail() throws Exception {
		// Prepare a registration request with missing email
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setEmail(""); // Empty email
		registrationRequest.setName("Name");
		registrationRequest.setPassword("iloveyou");
		String content = (new ObjectMapper()).writeValueAsString(registrationRequest);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);

		// Perform the registration request and expect a bad request (400) response
		MockMvcBuilders.standaloneSetup(authenticationController)
			.build()
			.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void testRegister_InvalidEmail() throws Exception {
		// Prepare a registration request with missing email
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setEmail("invalid-email"); // Invalid email
		registrationRequest.setName("Name");
		registrationRequest.setPassword("iloveyou");
		String content = (new ObjectMapper()).writeValueAsString(registrationRequest);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);

		// Perform the registration request and expect a bad request (400) response
		MockMvcBuilders.standaloneSetup(authenticationController)
			.build()
			.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void testRegister_MissingPassword() throws Exception {
		// Prepare a registration request with missing password
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setEmail("jane.doe@example.org");
		registrationRequest.setName("Name");
		registrationRequest.setPassword(""); // Empty password
		String content = (new ObjectMapper()).writeValueAsString(registrationRequest);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);

		// Perform the registration request and expect a bad request (400) response
		MockMvcBuilders.standaloneSetup(authenticationController)
			.build()
			.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	/**
	 * Method under test:
	 * {@link AuthenticationController#authenticate(AuthenticationRequest)}
	 */
	@Test
	void testAuthenticate() throws Exception {
		JwtAuthenticationResponse buildResult = JwtAuthenticationResponse.builder()
			.accessToken("ABC123")
			.refreshToken("ABC123")
			.build();
		when(authenticationService.authenticate(Mockito.any())).thenReturn(buildResult);

		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setEmail("jane.doe@example.org");
		authenticationRequest.setPassword("iloveyou");
		String content = (new ObjectMapper()).writeValueAsString(authenticationRequest);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/authenticate")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		MockMvcBuilders.standaloneSetup(authenticationController)
			.build()
			.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.content()
				.string("{\"access_token\":\"ABC123\",\"refresh_token\":\"ABC123\"}"));
	}

	/**
	 * Method under test:
	 * {@link AuthenticationController#refreshToken(TokenRefreshRequest)}
	 */
	@Test
	void testRefreshToken() throws Exception {
		JwtAuthenticationResponse buildResult = JwtAuthenticationResponse.builder()
			.accessToken("ABC123")
			.refreshToken("ABC123")
			.build();
		when(authenticationService.refreshToken(Mockito.any())).thenReturn(buildResult);

		TokenRefreshRequest tokenRefreshRequest = new TokenRefreshRequest();
		tokenRefreshRequest.setRefreshToken("ABC123");
		String content = (new ObjectMapper()).writeValueAsString(tokenRefreshRequest);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/refresh-token")
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		MockMvcBuilders.standaloneSetup(authenticationController)
			.build()
			.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.content()
				.string("{\"access_token\":\"ABC123\",\"refresh_token\":\"ABC123\"}"));
	}

}
