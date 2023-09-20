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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleListingMapper
		implements ListingMapper<VehicleListing, VehicleListingResponse, VehicleListingRequest> {

	private final ModelMapper modelMapper;

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
		return switch (category) {
			case PASSENGER_CAR, SUV -> CarListingResponse.class;
			case TRAILER -> TrailerListingResponse.class;
			default -> throw new UnsupportedCategoryException(category.toString());
		};
	}

	/**
	 * Retrieves the entity class associated with the given VehicleCategory.
	 * @param category The VehicleCategory for which to retrieve the entity class.
	 * @return The entity class corresponding to the provided category.
	 * @throws UnsupportedCategoryException If no entity class is associated with the
	 * category.
	 */
	private Class<? extends VehicleListing> getEntityClass(VehicleCategory category) {
		return switch (category) {
			case PASSENGER_CAR, SUV -> CarListing.class;
			case TRAILER -> TrailerListing.class;
			default -> throw new UnsupportedCategoryException(category.toString());
		};
	}

}