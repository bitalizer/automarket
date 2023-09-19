package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.ConditionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class ListingResponse {

	@NotNull
	private Long id;

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

	@NotNull
	private Date createdAt;

	private Date updatedAt;

}
