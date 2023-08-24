package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.DriveType;
import com.example.automarket.domain.FuelType;
import com.example.automarket.domain.model.listing.CarListing;
import lombok.Getter;

@Getter
public class CarListingResponse extends VehicleListingResponse {
    private final Integer mileage;
    private final FuelType fuelType;
    private final DriveType driveType;

    public CarListingResponse(CarListing carListing) {
        super(carListing);
        this.fuelType = carListing.getFuelType();
        this.driveType = carListing.getDriveType();
        this.mileage = carListing.getMileage();
    }
}
