package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.CarListing;
import lombok.Getter;

@Getter
public class CarListingResponse extends ListingResponse {
    private final Integer productionYear;
    private final Integer mileage;

    public CarListingResponse(CarListing carListing) {
        super(carListing);
        this.productionYear = carListing.getProductionYear();
        this.mileage = carListing.getMileage();
    }
}
