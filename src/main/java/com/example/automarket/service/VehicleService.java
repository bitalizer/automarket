package com.example.automarket.service;

import com.example.automarket.domain.dto.request.VehicleListingRequest;
import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

	List<VehicleListingResponse> getAllListings();

	List<VehicleListingResponse> getFilteredListings(Specification<VehicleListing> spec, Sort sort);

	Optional<VehicleListingResponse> getListingById(Long listingId);

	VehicleListing createListing(VehicleListingRequest listingRequest);

	void deleteListingById(Long listingId);

}
