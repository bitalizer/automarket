package com.example.automarket.service.impl;

import com.example.automarket.convert.VehicleListingMapper;
import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
import com.example.automarket.repository.VehicleRepository;
import com.example.automarket.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository vehicleRepository;

	private final VehicleListingMapper mapper;

	public List<VehicleListingResponse> getAllListings() {
		List<VehicleListing> listings = vehicleRepository.findAll();
		return listings.stream().map(mapper::fromEntity).toList();
	}

	public Optional<VehicleListingResponse> getListingById(Long listingId) {
		return vehicleRepository.findById(listingId).map(mapper::fromEntity);
	}

	@Override
	public void deleteListingById(Long listingId) {
		vehicleRepository.deleteById(listingId);
	}

}