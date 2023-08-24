package com.example.automarket.service;

import com.example.automarket.domain.dto.response.ListingResponse;
import com.example.automarket.domain.model.listing.Listing;

import java.util.List;
import java.util.Optional;

public interface ListingService {

    List<ListingResponse> getAllListings();

    Optional<Listing> getListingById(Long listingId);

    void deleteListingById(Long listingId);
}
