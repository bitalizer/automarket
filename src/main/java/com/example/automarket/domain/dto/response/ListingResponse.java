package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.Listing;
import lombok.Getter;

import java.util.Date;

@Getter
public abstract class ListingResponse {

	private final Long id;

	private final String title;

	private final String description;

	private final Integer price;

	private final Long brandId;

	private final Long modelId;

	private final Long regionId;

	private final Long userId;

	private final Integer condition;

	private final Date createdAt;

	private final Date updatedAt;

	protected ListingResponse(Listing listing) {
		this.id = listing.getId();
		this.title = listing.getTitle();
		this.description = listing.getDescription();
		this.price = listing.getPrice();
		this.brandId = listing.getBrand().getId();
		this.modelId = listing.getModel().getId();
		this.regionId = listing.getRegion() != null ? listing.getRegion().getId() : null;
		this.userId = listing.getUser() != null ? listing.getUser().getId() : null;
		this.condition = listing.getCondition().ordinal();
		this.createdAt = listing.getCreatedAt();
		this.updatedAt = listing.getUpdatedAt();
	}

}
