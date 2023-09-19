package com.example.automarket.domain.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrailerListingResponse extends VehicleListingResponse {

	@NotNull
	@Positive
	private Integer payload;

}
