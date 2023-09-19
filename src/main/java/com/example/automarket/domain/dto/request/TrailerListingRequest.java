package com.example.automarket.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class TrailerListingRequest extends VehicleListingRequest {

	@NotNull
	@Positive
	private Integer payload;

}
