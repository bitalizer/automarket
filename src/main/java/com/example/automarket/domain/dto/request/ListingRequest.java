package com.example.automarket.domain.dto.request;

import com.example.automarket.domain.model.listing.ConditionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class ListingRequest {

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@NotNull
	@Positive
	private Integer price;

	@NotNull
	private Long brandId;

	@NotNull
	private Long modelId;

	private Long regionId;

	@NotNull
	private Long userId;

	@NotNull
	private ConditionType condition;

}
