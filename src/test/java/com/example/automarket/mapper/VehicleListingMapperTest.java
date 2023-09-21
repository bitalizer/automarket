package com.example.automarket.mapper;

import com.example.automarket.domain.Color;
import com.example.automarket.domain.dto.request.CarListingRequest;
import com.example.automarket.domain.dto.response.CarListingResponse;
import com.example.automarket.domain.model.Region;
import com.example.automarket.domain.model.User;
import com.example.automarket.domain.model.listing.ConditionType;
import com.example.automarket.domain.model.listing.VehicleBrand;
import com.example.automarket.domain.model.listing.VehicleModel;
import com.example.automarket.domain.model.listing.vehicle.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles({ "test" })
@ExtendWith(SpringExtension.class)
class VehicleListingMapperTest {

	private static VehicleBrand brand;

	private static VehicleModel model;

	private static Region region;

	private static User user;

	@Autowired
	private VehicleListingMapper vehicleListingMapper;

	@BeforeAll
	public static void setUp() {
		// Initialize mock instances of VehicleBrand, VehicleModel, and Region
		brand = mock(VehicleBrand.class);
		when(brand.getName()).thenReturn("Toyota");
		when(brand.getId()).thenReturn(1L);

		model = mock(VehicleModel.class);
		when(model.getName()).thenReturn("Camry");
		when(brand.getId()).thenReturn(1L);

		region = mock(Region.class);
		when(region.getName()).thenReturn("Tallinn");
		when(region.getId()).thenReturn(1L);

		user = mock(User.class);
		when(region.getName()).thenReturn("Test User");
		when(region.getId()).thenReturn(1L);
	}

	@Test
	void testCarListingFromEntityToResponse() {
		CarListing carListing = CarListing.builder()
			.title("test title")
			.description("some car description")
			.price(5000)
			.brand(brand)
			.model(model)
			.region(region)
			.user(user)
			.condition(ConditionType.USED)
			.createdAt(new Date())
			.updatedAt(new Date())
			.productionYear(2015)
			.auction(false)
			.category(VehicleCategory.PASSENGER_CAR)
			.subCategory(VehicleSubCategory.SEDAN)
			.mileage(125_500)
			.fuelType(FuelType.DIESEL)
			.transmissionType(TransmissionType.AUTOMATIC)
			.color(Color.BLACK)
			.plateNumber("333AAA")
			.build();

		CarListingResponse carListingResponse = (CarListingResponse) vehicleListingMapper.fromEntity(carListing);

		// Generate assert equals to all
		assertEquals(carListing.getTitle(), carListingResponse.getTitle());
		assertEquals(carListing.getDescription(), carListingResponse.getDescription());
		assertEquals(carListing.getPrice(), carListingResponse.getPrice());
		assertEquals(carListing.getBrand().getId(), carListingResponse.getBrandId());
		assertEquals(carListing.getModel().getId(), carListingResponse.getModelId());
		assertEquals(carListing.getRegion().getId(), carListingResponse.getRegionId());
		assertEquals(carListing.getUser().getId(), carListingResponse.getUserId());
		assertEquals(carListing.getCondition(), carListingResponse.getCondition());
		assertNotNull(carListingResponse.getCreatedAt());
		assertNotNull(carListingResponse.getUpdatedAt());
		assertEquals(carListing.getProductionYear(), carListingResponse.getProductionYear());
		assertFalse(carListingResponse.isAuction());
		assertEquals(carListing.getCategory(), carListingResponse.getCategory());
		assertEquals(carListing.getSubCategory(), carListingResponse.getSubCategory());
		assertEquals(carListing.getMileage(), carListingResponse.getMileage());
		assertEquals(carListing.getFuelType(), carListingResponse.getFuelType());
		assertEquals(carListing.getTransmissionType(), carListingResponse.getTransmissionType());
		assertEquals(carListing.getColor(), carListingResponse.getColor());
		assertEquals(carListing.getPlateNumber(), carListingResponse.getPlateNumber());
	}

	@Test
	void testCarListingRequestToEntity() {

		CarListingRequest carListingRequest = new CarListingRequest();
		carListingRequest.setTitle("test title");
		carListingRequest.setDescription("some car description");
		carListingRequest.setPrice(5000);
		carListingRequest.setBrandId(brand.getId());
		carListingRequest.setModelId(model.getId());
		carListingRequest.setRegionId(region.getId());
		carListingRequest.setUserId(user.getId());
		carListingRequest.setCondition(ConditionType.USED);
		carListingRequest.setProductionYear(2015);
		carListingRequest.setAuction(false);
		carListingRequest.setCategory(VehicleCategory.PASSENGER_CAR);
		carListingRequest.setSubCategory(VehicleSubCategory.SEDAN);
		carListingRequest.setMileage(125_500);
		carListingRequest.setFuelType(FuelType.DIESEL);
		carListingRequest.setTransmissionType(TransmissionType.AUTOMATIC);
		carListingRequest.setColor(Color.BLACK);
		carListingRequest.setPlateNumber("333AAA");

		CarListing carListing = (CarListing) vehicleListingMapper.toEntity(carListingRequest);

		// Generate assert equals to all
		assertEquals(carListing.getTitle(), carListingRequest.getTitle());
		assertEquals(carListing.getDescription(), carListingRequest.getDescription());
		assertEquals(carListing.getPrice(), carListingRequest.getPrice());
		assertEquals(carListing.getBrand().getId(), carListingRequest.getBrandId());
		assertEquals(carListing.getModel().getId(), carListingRequest.getModelId());
		assertEquals(carListing.getRegion().getId(), carListingRequest.getRegionId());
		assertEquals(carListing.getUser().getId(), carListingRequest.getUserId());
		assertEquals(carListing.getCondition(), carListingRequest.getCondition());
		assertEquals(carListing.getProductionYear(), carListingRequest.getProductionYear());
		assertFalse(carListing.isAuction());
		assertEquals(carListing.getCategory(), carListingRequest.getCategory());
		assertEquals(carListing.getSubCategory(), carListingRequest.getSubCategory());
		assertEquals(carListing.getMileage(), carListingRequest.getMileage());
		assertEquals(carListing.getFuelType(), carListingRequest.getFuelType());
		assertEquals(carListing.getTransmissionType(), carListingRequest.getTransmissionType());
		assertEquals(carListing.getColor(), carListingRequest.getColor());
		assertEquals(carListing.getPlateNumber(), carListingRequest.getPlateNumber());
	}

}
