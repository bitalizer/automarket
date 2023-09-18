package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.vehicle.VehicleCategory;
import com.example.automarket.domain.model.listing.vehicle.VehicleSubCategory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class VehicleListingResponse extends ListingResponse {

	@Min(1920)
	@Max(2023)
	private Integer productionYear;

	private boolean auction;

	private boolean exchangeable;

	private VehicleCategory category;

	private VehicleSubCategory subCategory;

}