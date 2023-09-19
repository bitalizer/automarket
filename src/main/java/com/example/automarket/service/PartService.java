package com.example.automarket.service;

import com.example.automarket.domain.dto.response.PartListingResponse;

import java.util.List;
import java.util.Optional;

public interface PartService {

	List<PartListingResponse> getAllListings();

	Optional<PartListingResponse> getListingById(Long listingId);

	void deleteListingById(Long listingId);

}
