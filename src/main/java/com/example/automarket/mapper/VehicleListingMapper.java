package com.example.automarket.mapper;

import com.example.automarket.domain.dto.response.CarListingResponse;
import com.example.automarket.domain.dto.response.TrailerListingResponse;
import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.domain.model.listing.vehicle.VehicleCategory;
import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
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
public class VehicleListingMapper {

	private final ModelMapper modelMapper;

	// @formatter:off
	private final Map<VehicleCategory, Class<? extends VehicleListingResponse>> categoryToResponseClass = Map.of(
			PASSENGER_CAR, CarListingResponse.class,
			SUV, CarListingResponse.class,
			TRAILER, TrailerListingResponse.class
	);
	// @formatter:on

	/**
	 * Maps a VehicleListing entity to a VehicleListingResponse DTO.
	 * @param listing The VehicleListing entity to be mapped.
	 * @return The mapped VehicleListingResponse DTO.
	 * @throws IllegalStateException If the category is not implemented.
	 */
	public VehicleListingResponse fromEntity(VehicleListing listing) {
		Class<? extends VehicleListingResponse> responseClass = categoryToResponseClass.get(listing.getCategory());

		if (responseClass != null) {
			return modelMapper.map(listing, responseClass);
		}
		else {
			throw new IllegalStateException("Category not implemented.");
		}
	}

	@PostConstruct
	public void init() {
		EnumSet<VehicleCategory> missingMappings = EnumSet
			.complementOf(EnumSet.copyOf(categoryToResponseClass.keySet()));

		if (!missingMappings.isEmpty()) {

			String missingCategories = missingMappings.stream()
				.map(VehicleCategory::name)
				.collect(Collectors.joining(", "));

			log.warn("The following VehicleCategory enums do not have corresponding mappings: {}", missingCategories);
		}
	}

}