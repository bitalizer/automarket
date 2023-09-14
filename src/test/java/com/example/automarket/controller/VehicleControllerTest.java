package com.example.automarket.controller;

import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.service.VehicleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {

	@Autowired
	private VehicleController vehicleController;

	@MockBean
	private VehicleService vehicleService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	static void setUp() {

	}

	/**
	 * Method under test: {@link VehicleController#getListingById(Long)}
	 */
	@Test
	void testGetListingById() throws Exception {
		Optional<VehicleListingResponse> emptyResult = Optional.empty();
		when(vehicleService.getListingById(anyLong())).thenReturn(emptyResult);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/vehicles/{id}", 1L);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(vehicleController)
			.build()
			.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@WithMockUser
	@Test
	void shouldGetEmptyArrayWhenNoListingsExists() throws Exception {
		mockMvc.perform(get("/api/v1/vehicles"))
			.andExpect(status().is(200))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.length()").value(0))
			.andDo(print())
			.andReturn();
	}

	/**
	 * Method under test: {@link VehicleController#deleteListingById(Long)}
	 */
	@Test
	void testDeleteListingById() throws Exception {
		Optional<VehicleListingResponse> emptyResult = Optional.empty();
		when(vehicleService.getListingById(Mockito.<Long>any())).thenReturn(emptyResult);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/vehicles/{id}", 1L);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(vehicleController)
			.build()
			.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
    void testGetAllVehicles() throws Exception {
        when(vehicleService.getAllListings()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/vehicles");
        MockMvcBuilders.standaloneSetup(vehicleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

}