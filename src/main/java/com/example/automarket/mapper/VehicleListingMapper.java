package com.example.automarket.mapper;

import com.example.automarket.domain.dto.request.VehicleListingRequest;
import com.example.automarket.domain.dto.response.CarListingResponse;
import com.example.automarket.domain.dto.response.TrailerListingResponse;
import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.domain.model.listing.vehicle.CarListing;
import com.example.automarket.domain.model.listing.vehicle.TrailerListing;
import com.example.automarket.domain.model.listing.vehicle.VehicleCategory;
import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
import com.example.automarket.exception.UnsupportedCategoryException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.automarket.domain.model.listing.vehicle.VehicleCategory.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleListingMapper
		implements ListingMapper<VehicleListing, VehicleListingResponse, VehicleListingRequest> {

	private final ModelMapper modelMapper;

	// @formatter:off
	private static final Map<VehicleCategory, Class<? extends VehicleListingResponse>> categoryToResponseClass = Map.of(
			PASSENGER_CAR, CarListingResponse.class,
			SUV, CarListingResponse.class,
			TRAILER, TrailerListingResponse.class
	);

	private static final Map<VehicleCategory, Class<? extends VehicleListing>> categoryToEntityClass = Map.of(
			PASSENGER_CAR, CarListing.class,
			SUV, CarListing.class,
			TRAILER, TrailerListing.class
	);
	// @formatter:on

	/**
	 * Maps a VehicleListing entity to a corresponding VehicleListingResponse DTO.
	 * @param listing The VehicleListing entity to be mapped.
	 * @return The mapped VehicleListingResponse DTO.
	 * @throws IllegalStateException If the category of the listing is not supported.
	 */
	@Override
	public VehicleListingResponse fromEntity(VehicleListing listing) {
		Class<? extends VehicleListingResponse> responseClass = getResponseClass(listing.getCategory());
		return modelMapper.map(listing, responseClass);
	}

	/**
	 * Maps a VehicleListingRequest DTO to a corresponding VehicleListing entity.
	 * @param listingRequest The VehicleListingRequest DTO to be mapped.
	 * @return The mapped VehicleListing entity.
	 * @throws IllegalArgumentException If the category in the request is not recognized.
	 */
	@Override
	public VehicleListing toEntity(VehicleListingRequest listingRequest) {
		Class<? extends VehicleListing> entityClass = getEntityClass(listingRequest.getCategory());
		return modelMapper.map(listingRequest, entityClass);
	}

	/**
	 * Retrieves the response class associated with the given VehicleCategory.
	 * @param category The VehicleCategory for which to retrieve the response class.
	 * @return The response class corresponding to the provided category.
	 * @throws UnsupportedCategoryException If no response class is associated with the
	 * category.
	 */
	private Class<? extends VehicleListingResponse> getResponseClass(VehicleCategory category) {
		Class<? extends VehicleListingResponse> responseClass = categoryToResponseClass.get(category);
		if (responseClass == null) {
			throw new UnsupportedCategoryException(category.toString());
		}
		return responseClass;
	}

	/**
	 * Retrieves the entity class associated with the given VehicleCategory.
	 * @param category The VehicleCategory for which to retrieve the entity class.
	 * @return The entity class corresponding to the provided category.
	 * @throws UnsupportedCategoryException If no entity class is associated with the
	 * category.
	 */
	private Class<? extends VehicleListing> getEntityClass(VehicleCategory category) {
		Class<? extends VehicleListing> entityClass = categoryToEntityClass.get(category);
		if (entityClass == null) {
			throw new UnsupportedCategoryException(category.toString());
		}
		return entityClass;
	}

	/**
	 * Initializes the VehicleListingMapper by checking for missing mappings between
	 * VehicleCategory enums and their corresponding response classes. If any mappings are
	 * missing, a warning message is logged.
	 */
	@PostConstruct
	public void init() {
		EnumSet<VehicleCategory> missingMappings = EnumSet
			.complementOf(EnumSet.copyOf(categoryToResponseClass.keySet()));

		if (!missingMappings.isEmpty()) {

			String missingCategories = missingMappings.stream()
				.map(VehicleCategory::name)
				.collect(Collectors.joining(", "));

			log.warn("The following vehicle categories do not have corresponding mappings: {}", missingCategories);
		}
	}

}