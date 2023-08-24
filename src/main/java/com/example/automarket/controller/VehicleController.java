package com.example.automarket.controller;

import com.example.automarket.domain.dto.response.VehicleListingResponse;
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

    @GetMapping("/{id}")
    public ResponseEntity<VehicleListingResponse> getListingById(@PathVariable("id") Long listingId) {
        Optional<VehicleListingResponse> optionalListingResponse = vehicleService.getListingById(listingId);

        if (optionalListingResponse.isPresent()) {
            VehicleListingResponse listingResponse = optionalListingResponse.get();
            return ResponseEntity.ok(listingResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListingById(@PathVariable("id") Long listingId) {
        Optional<VehicleListingResponse> optionalListingResponse = vehicleService.getListingById(listingId);

        if (optionalListingResponse.isPresent()) {
            vehicleService.deleteListingById(listingId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}