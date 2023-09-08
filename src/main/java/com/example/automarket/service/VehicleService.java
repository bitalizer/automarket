package com.example.automarket.service;

import com.example.automarket.domain.dto.response.VehicleListingResponse;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

	List<VehicleListingResponse> getAllListings();

	Optional<VehicleListingResponse> getListingById(Long listingId);

	void deleteListingById(Long listingId);

}
