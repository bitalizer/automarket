package com.example.automarket.controller;

import com.example.automarket.domain.dto.request.VehicleListingRequest;
import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
import com.example.automarket.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

	private final VehicleService vehicleService;

	@GetMapping
	public List<VehicleListingResponse> getAllListings() {
		return vehicleService.getAllListings();
	}

	@GetMapping("/{id}")
	public ResponseEntity<VehicleListingResponse> getListingById(@PathVariable("id") Long listingId) {
		return vehicleService.getListingById(listingId)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<VehicleListing> getListingById(@RequestBody @Validated VehicleListingRequest listingRequest) {
		VehicleListing response = vehicleService.createListing(listingRequest);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteListingById(@PathVariable("id") Long listingId) {
		return vehicleService.getListingById(listingId).map(listingResponse -> {
			vehicleService.deleteListingById(listingId);
			return ResponseEntity.noContent().<Void>build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

}