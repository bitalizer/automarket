package com.example.automarket.controller;

import com.example.automarket.domain.Color;
import com.example.automarket.domain.dto.request.CarListingRequest;
import com.example.automarket.domain.dto.request.VehicleListingRequest;
import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.domain.model.listing.ConditionType;
import com.example.automarket.domain.model.listing.vehicle.DriveType;
import com.example.automarket.domain.model.listing.vehicle.FuelType;
import com.example.automarket.domain.model.listing.vehicle.VehicleCategory;
import com.example.automarket.domain.model.listing.vehicle.VehicleSubCategory;
import com.example.automarket.service.VehicleService;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = { VehicleController.class })
@ActiveProfiles({ "test" })
@ExtendWith(SpringExtension.class)
class VehicleControllerTest {

	@Autowired
	private VehicleController vehicleController;

	@MockBean
	private VehicleService vehicleService;

	/*
	 * / Method under test: {@link VehicleController#getListingById(Long)}
	 */
	@Test
	void testGetListingById_NotFound() throws Exception {
		Optional<VehicleListingResponse> emptyResult = Optional.empty();
		when(vehicleService.getListingById(Mockito.<Long>any())).thenReturn(emptyResult);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/vehicles/{id}", 1L);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(vehicleController)
			.build()
			.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	/**
	 * Method under test: {@link VehicleController#getListingById(Long)}
	 */
	@Test
	void testGetListingById() throws Exception {
		VehicleListingResponse vehicleListingResponse = new VehicleListingResponse();
		vehicleListingResponse.setAuction(true);
		vehicleListingResponse.setBrandId(1L);
		vehicleListingResponse.setCategory(VehicleCategory.PASSENGER_CAR);
		vehicleListingResponse.setCondition(ConditionType.NEW);
		vehicleListingResponse
			.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
		vehicleListingResponse.setDescription("The characteristics of someone or something");
		vehicleListingResponse.setExchangeable(true);
		vehicleListingResponse.setId(1L);
		vehicleListingResponse.setModelId(1L);
		vehicleListingResponse.setPrice(1);
		vehicleListingResponse.setProductionYear(1);
		vehicleListingResponse.setRegionId(1L);
		vehicleListingResponse.setSubCategory(VehicleSubCategory.SEDAN);
		vehicleListingResponse.setTitle("Dr");
		vehicleListingResponse
			.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
		vehicleListingResponse.setUserId(1L);
		Optional<VehicleListingResponse> ofResult = Optional.of(vehicleListingResponse);
		when(vehicleService.getListingById(Mockito.<Long>any())).thenReturn(ofResult);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/vehicles/{id}", 1L);
		MockMvcBuilders.standaloneSetup(vehicleController)
			.build()
			.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.content()
				.string("{\"id\":1,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"price\":1,\"brandId\""
						+ ":1,\"modelId\":1,\"regionId\":1,\"userId\":1,\"condition\":\"NEW\",\"createdAt\":0,\"updatedAt\":0,\"productionYear"
						+ "\":1,\"auction\":true,\"exchangeable\":true,\"category\":\"PASSENGER_CAR\",\"subCategory\":\"SEDAN\"}"));
	}

	/**
    * Method under test: {@link VehicleController#getAllListings()}
    */
    @Test
    void testGetAllListings() throws Exception {
      when(vehicleService.getAllListings()).thenReturn(new ArrayList<>());
      MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/vehicles");
      MockMvcBuilders.standaloneSetup(vehicleController)
          .build()
          .perform(requestBuilder)
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
          .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

	/**

	/**
  * Method under test: {@link VehicleController#createListing(VehicleListingRequest)}
  */
  @Test
  void testCreateCarListing() throws Exception {
    when(vehicleService.getAllListings()).thenReturn(new ArrayList<>());

    CarListingRequest carListingRequest = new CarListingRequest();
    carListingRequest.setAuction(true);
    carListingRequest.setBrandId(1L);
    carListingRequest.setCategory(VehicleCategory.PASSENGER_CAR);
    carListingRequest.setCondition(ConditionType.NEW);
    carListingRequest.setDescription("Some passenger car description");
    carListingRequest.setExchangeable(true);
    carListingRequest.setModelId(1L);
    carListingRequest.setPrice(1);
    carListingRequest.setProductionYear(1);
    carListingRequest.setRegionId(1L);
    carListingRequest.setSubCategory(VehicleSubCategory.SEDAN);
    carListingRequest.setTitle("Mercedes-Benz W211");
    carListingRequest.setUserId(1L);
	carListingRequest.setColor(Color.BROWN);
	carListingRequest.setMileage(123_020);
	carListingRequest.setDriveType(DriveType.REAR_WHEEL_DRIVE);
	carListingRequest.setFuelType(FuelType.DIESEL);

    String content = (new ObjectMapper()).writeValueAsString(carListingRequest);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/vehicles")
        .contentType(MediaType.APPLICATION_JSON)
        .content(content);
    MockMvcBuilders.standaloneSetup(vehicleController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

	/**
	 * Method under test: {@link VehicleController#deleteListingById(Long)}
	 */
	@Test
	void testDeleteListingById() throws Exception {
		VehicleListingResponse vehicleListingResponse = new VehicleListingResponse();
		vehicleListingResponse.setAuction(true);
		vehicleListingResponse.setBrandId(1L);
		vehicleListingResponse.setCategory(VehicleCategory.PASSENGER_CAR);
		vehicleListingResponse.setCondition(ConditionType.NEW);
		vehicleListingResponse
			.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
		vehicleListingResponse.setDescription("The characteristics of someone or something");
		vehicleListingResponse.setExchangeable(true);
		vehicleListingResponse.setId(1L);
		vehicleListingResponse.setModelId(1L);
		vehicleListingResponse.setPrice(1);
		vehicleListingResponse.setProductionYear(1);
		vehicleListingResponse.setRegionId(1L);
		vehicleListingResponse.setSubCategory(VehicleSubCategory.SEDAN);
		vehicleListingResponse.setTitle("Dr");
		vehicleListingResponse
			.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
		vehicleListingResponse.setUserId(1L);

		Optional<VehicleListingResponse> ofResult = Optional.of(vehicleListingResponse);
		doNothing().when(vehicleService).deleteListingById(Mockito.<Long>any());
		when(vehicleService.getListingById(Mockito.<Long>any())).thenReturn(ofResult);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/vehicles/{id}", 1L);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(vehicleController)
			.build()
			.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	/**
	 * Method under test: {@link VehicleController#deleteListingById(Long)}
	 */
	@Test
	void testDeleteListingById_NotFound() throws Exception {
		doNothing().when(vehicleService).deleteListingById(Mockito.<Long>any());
		Optional<VehicleListingResponse> emptyResult = Optional.empty();
		when(vehicleService.getListingById(Mockito.<Long>any())).thenReturn(emptyResult);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/vehicles/{id}", 1L);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(vehicleController)
			.build()
			.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
