package com.example.automarket.service.impl;

import com.example.automarket.domain.dto.response.CarListingResponse;
import com.example.automarket.domain.dto.response.TrailerListingResponse;
import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.domain.model.listing.Listing;
import com.example.automarket.domain.model.listing.vehicle.CarListing;
import com.example.automarket.domain.model.listing.vehicle.TrailerListing;
import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
import com.example.automarket.repository.VehicleRepository;
import com.example.automarket.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository vehicleRepository;

	public List<VehicleListingResponse> getAllListings() {
		List<VehicleListing> listings = vehicleRepository.findAll();
		return listings.stream().map(this::mapToListingResponse).collect(Collectors.toList());
	}

	public Optional<VehicleListingResponse> getListingById(Long listingId) {
		Optional<VehicleListing> vehicleListing = vehicleRepository.findById(listingId);

		if (vehicleListing.isPresent()) {
			VehicleListingResponse vehicleListingResponse = mapToListingResponse(vehicleListing.get());
			return Optional.of(vehicleListingResponse);
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteListingById(Long listingId) {
		vehicleRepository.deleteById(listingId);
	}

	private VehicleListingResponse mapToListingResponse(Listing listing) {
		if (listing instanceof CarListing carListing) {
			return new CarListingResponse(carListing);
		}
		else if (listing instanceof TrailerListing trailerListing) {
			return new TrailerListingResponse(trailerListing);
		}
		else {
			throw new java.lang.UnsupportedOperationException("Not supported yet.");
		}
	}

}