package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.vehicle.DriveType;
import com.example.automarket.domain.model.listing.vehicle.FuelType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class CarListingResponse extends VehicleListingResponse {

	private Integer mileage;

	private FuelType fuelType;

	private DriveType driveType;

}
