package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.TrailerListing;
import lombok.Getter;

@Getter
public class TrailerListingResponse extends VehicleListingResponse {
    private final Integer payload;

    public TrailerListingResponse(TrailerListing trailerListing) {
        super(trailerListing);
        this.payload = trailerListing.getPayload();
    }
}
