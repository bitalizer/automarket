package com.example.automarket.service.impl;

import com.example.automarket.domain.dto.response.CarListingResponse;
import com.example.automarket.domain.dto.response.ListingResponse;
import com.example.automarket.domain.dto.response.TrailerListingResponse;
import com.example.automarket.domain.model.listing.CarListing;
import com.example.automarket.domain.model.listing.Listing;
import com.example.automarket.domain.model.listing.TrailerListing;
import com.example.automarket.repository.ListingRepository;
import com.example.automarket.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;

    public List<ListingResponse> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        return listings.stream().map(this::mapToListingResponse).collect(Collectors.toList());
    }

    public Optional<Listing> getListingById(Long listingId) {
        return listingRepository.findById(listingId);
    }

    @Override
    public void deleteListingById(Long listingId) {
        listingRepository.deleteById(listingId);
    }

    private ListingResponse mapToListingResponse(Listing listing) {
        if (listing instanceof CarListing carListing) {
            return new CarListingResponse(carListing);
        } else if (listing instanceof TrailerListing trailerListing) {
            return new TrailerListingResponse(trailerListing);
        } else {
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }
    }
}