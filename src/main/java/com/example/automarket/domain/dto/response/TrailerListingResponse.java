package com.example.automarket.domain.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrailerListingResponse extends VehicleListingResponse {

	private Integer payload;

}
