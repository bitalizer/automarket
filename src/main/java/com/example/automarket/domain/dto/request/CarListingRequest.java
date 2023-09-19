package com.example.automarket.domain.dto.request;

import com.example.automarket.domain.Color;
import com.example.automarket.domain.model.listing.vehicle.DriveType;
import com.example.automarket.domain.model.listing.vehicle.FuelType;
import com.example.automarket.domain.model.listing.vehicle.TransmissionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class CarListingRequest extends VehicleListingRequest {

	@NotNull
	@Positive
	private Integer mileage;

	@NotNull
	private FuelType fuelType;

	@NotNull
	private TransmissionType transmissionType;

	@NotNull
	private DriveType driveType;

	private Color color;

	private String plateNumber;

	private String vinCode;

}
