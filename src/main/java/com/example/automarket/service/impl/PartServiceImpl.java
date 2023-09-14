package com.example.automarket.service.impl;

import com.example.automarket.domain.dto.response.PartListingResponse;
import com.example.automarket.domain.model.listing.Listing;
import com.example.automarket.domain.model.listing.part.PartListing;
import com.example.automarket.repository.PartRepository;
import com.example.automarket.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PartServiceImpl implements PartService {

	private final PartRepository partRepository;

	public List<PartListingResponse> getAllListings() {
		List<PartListing> listings = partRepository.findAll();
		return listings.stream().map(this::mapToListingResponse).toList();
	}

	public Optional<PartListingResponse> getListingById(Long listingId) {
		Optional<PartListing> listing = partRepository.findById(listingId);

		if (listing.isPresent()) {
			PartListingResponse listingResponse = mapToListingResponse(listing.get());
			return Optional.of(listingResponse);
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteListingById(Long listingId) {
		partRepository.deleteById(listingId);
	}

	private PartListingResponse mapToListingResponse(Listing listing) {

		if (listing instanceof PartListing partListing) {
			return new PartListingResponse(partListing);
		}
		else {
			throw new java.lang.UnsupportedOperationException("Not supported yet.");
		}
	}

}