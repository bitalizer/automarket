package com.example.automarket.domain.dto.request;

import com.example.automarket.domain.model.listing.vehicle.VehicleCategory;
import com.example.automarket.domain.model.listing.vehicle.VehicleSubCategory;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes(value = { @JsonSubTypes.Type(value = CarListingRequest.class),
		@JsonSubTypes.Type(value = TrailerListingRequest.class) })

public class VehicleListingRequest extends ListingRequest {

	@Min(1920)
	@Max(2023)
	private Integer productionYear;

	@NotNull
	private Boolean auction;

	@NotNull
	private Boolean exchangeable;

	@NotNull
	private VehicleCategory category;

	@NotNull
	private VehicleSubCategory subCategory;

}