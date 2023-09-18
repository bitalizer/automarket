package com.example.automarket.domain.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class ListingResponse {

	@Positive
	private Long id;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@Positive
	private Integer price;

	private Long brandId;

	private Long modelId;

	private Long regionId;

	private Long userId;

	private Integer conditionId;

	private Date createdAt;

	private Date updatedAt;

}
