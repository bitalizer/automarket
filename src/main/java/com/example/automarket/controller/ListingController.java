package com.example.automarket.controller;

import com.example.automarket.domain.dto.response.ListingResponse;
import com.example.automarket.domain.model.listing.Listing;
import com.example.automarket.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/listings")
public class ListingController {

    private final ListingService listingService;

    @GetMapping
    public List<ListingResponse> getAllListings() {
        return listingService.getAllListings();
    }

    @GetMapping("{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable(value = "id") Long listingId) {
        Optional<Listing> optionalListing = listingService.getListingById(listingId);
        if (optionalListing.isPresent()) {
            Listing listing = optionalListing.get();
            return ResponseEntity.ok(listing);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(value = "id") Long listingId) {
        Optional<Listing> optionalUser = listingService.getListingById(listingId);
        if (optionalUser.isPresent()) {
            listingService.deleteListingById(listingId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}