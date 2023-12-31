package com.example.automarket.seed.dummy;

import com.example.automarket.domain.model.Region;
import com.example.automarket.domain.model.User;
import com.example.automarket.domain.model.listing.ConditionType;
import com.example.automarket.domain.model.listing.Listing;
import com.example.automarket.domain.model.listing.VehicleModel;
import com.example.automarket.domain.model.listing.part.DealType;
import com.example.automarket.domain.model.listing.part.PartAvailability;
import com.example.automarket.domain.model.listing.part.PartCategory;
import com.example.automarket.domain.model.listing.part.PartListing;
import com.example.automarket.domain.model.listing.vehicle.*;
import com.example.automarket.repository.RegionRepository;
import com.example.automarket.repository.UserRepository;
import com.example.automarket.repository.VehicleModelRepository;
import com.example.automarket.service.RandomGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Lazy
@Component
@Profile("dev")
@Slf4j
public class ListingFactory implements DummyFactory<Listing> {

	private final RandomGenerator randomGenerator;

	private final UserRepository userRepository;

	private final RegionRepository regionRepository;

	private final VehicleModelRepository modelRepository;

	private List<User> users;

	private List<Region> regions;

	private List<VehicleModel> models;

	protected void initialize() {
		users = userRepository.findAll();
		regions = regionRepository.findAll();
		models = modelRepository.findAll();

		if (users.isEmpty() || regions.isEmpty() || models.isEmpty()) {
			throw new IllegalStateException("Initialization data is missing");
		}
	}

	@Override
	public Listing create() {

		int randomType = randomGenerator.getRandomInt(0, 3);

		if (randomType == 0) {
			return createCarListing();
		}
		else if (randomType == 1) {
			return createTrailerListing();
		}
		else {
			return createPartListing();
		}
	}

	private CarListing createCarListing() {

		User user = randomGenerator.getRandomElement(users);
		Region region = randomGenerator.getRandomElement(regions);
		VehicleModel model = randomGenerator.getRandomElement(models);

		VehicleSubCategory[] subCategories = { VehicleSubCategory.SEDAN, VehicleSubCategory.CABRIOLET,
				VehicleSubCategory.SUV_TOURING, VehicleSubCategory.SUV_PICKUP };
		VehicleSubCategory randomSubCategory = randomGenerator.getRandomElement(List.of(subCategories));

		return CarListing.builder()
			.title(String.format("%s %s", model.getBrand().getTitle(), model.getTitle()))
			.description("some car description")
			.plateNumber(String.format("%s%s", randomGenerator.getRandomUppercaseString(3),
					randomGenerator.getRandomInt(100, 999)))
			.vinCode(randomGenerator.getRandomUppercaseString(17))
			.price(randomGenerator.getRandomInt(800, 22000))
			.user(user)
			.region(region)
			.brand(model.getBrand())
			.model(model)
			.mileage(randomGenerator.getRandomInt(25_000, 300_000))
			.productionYear(randomGenerator.getRandomInt(1998, 2023))
			.condition(randomGenerator.getRandomBoolean() ? ConditionType.USED : ConditionType.DAMAGED)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.auction(randomGenerator.getRandomBoolean())
			.fuelType(FuelType.DIESEL)
			.transmissionType(TransmissionType.AUTOMATIC)
			.driveType(DriveType.FRONT_WHEEL_DRIVE)
			.category(randomSubCategory.getCategory())
			.subCategory(randomSubCategory)
			.build();
	}

	private TrailerListing createTrailerListing() {

		User user = randomGenerator.getRandomElement(users);
		Region region = randomGenerator.getRandomElement(regions);
		VehicleModel model = randomGenerator.getRandomElement(models);

		int payload = randomGenerator.getRandomInt(800, 3500);

		return TrailerListing.builder()
			.title(String.format("%s %d", model.getTitle(), payload))
			.description("some trailer description")
			.price(randomGenerator.getRandomInt(800, 22000))
			.user(user)
			.region(region)
			.brand(model.getBrand())
			.model(model)
			.condition(randomGenerator.getRandomBoolean() ? ConditionType.USED : ConditionType.RESTORED)
			.productionYear(randomGenerator.getRandomInt(1998, 2023))
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.payload(payload)
			.category(VehicleCategory.TRAILER)
			.subCategory(VehicleSubCategory.TRAILER_LIGHT_TRAILER)
			.build();
	}

	private PartListing createPartListing() {
		User user = randomGenerator.getRandomElement(users);
		Region region = randomGenerator.getRandomElement(regions);
		VehicleModel model = randomGenerator.getRandomElement(models);

		return PartListing.builder()
			.title(String.format("Part %s", model.getTitle()))
			.description("some part description")
			.price(randomGenerator.getRandomInt(800, 22000))
			.user(user)
			.region(region)
			.brand(model.getBrand())
			.model(model)
			.category(randomGenerator.getRandomBoolean() ? PartCategory.ENGINE_PARTS : PartCategory.SUSPENSION_PARTS)
			.condition(randomGenerator.getRandomBoolean() ? ConditionType.NEW : ConditionType.USED)
			.dealType(randomGenerator.getRandomBoolean() ? DealType.BUY : DealType.SELL)
			.availability(randomGenerator.getRandomBoolean() ? PartAvailability.IN_STOCK : PartAvailability.ON_ORDER)
			.build();
	}

}
