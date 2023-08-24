package com.example.automarket.controller;

import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.domain.model.listing.Listing;
import com.example.automarket.domain.model.listing.VehicleListing;
import com.example.automarket.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public List<VehicleListingResponse> getAllVehicles() {
        return vehicleService.getAllListings();
    }

    @GetMapping("{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable(value = "id") Long listingId) {
        Optional<VehicleListing> optionalListing = vehicleService.getListingById(listingId);
        if (optionalListing.isPresent()) {
            Listing listing = optionalListing.get();
            return ResponseEntity.ok(listing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteListingById(@PathVariable(value = "id") Long listingId) {
        Optional<VehicleListing> optionalUser = vehicleService.getListingById(listingId);
        if (optionalUser.isPresent()) {
            vehicleService.deleteListingById(listingId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}