package com.example.automarket.service.impl;

import com.example.automarket.domain.dto.response.PartListingResponse;
import com.example.automarket.domain.model.listing.part.PartListing;
import com.example.automarket.repository.PartRepository;
import com.example.automarket.service.PartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PartServiceImpl implements PartService {

	private final PartRepository partRepository;

	private final ModelMapper mapper;

	public List<PartListingResponse> getAllListings() {
		List<PartListing> listings = partRepository.findAll();
		return listings.stream().map(listing -> mapper.map(listing, PartListingResponse.class)).toList();
	}

	public Optional<PartListingResponse> getListingById(Long listingId) {
		return partRepository.findById(listingId).map(listing -> mapper.map(listing, PartListingResponse.class));
	}

	@Override
	public void deleteListingById(Long listingId) {
		partRepository.deleteById(listingId);
	}

}