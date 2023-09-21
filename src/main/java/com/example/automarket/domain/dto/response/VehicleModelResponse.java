package com.example.automarket.domain.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehicleModelResponse {

	@NotNull
	private Integer id;

	@NotBlank
	private String name;

}