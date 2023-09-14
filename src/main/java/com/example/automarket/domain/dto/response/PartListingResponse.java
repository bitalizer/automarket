package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.part.PartListing;
import lombok.Getter;

@Getter
public class PartListingResponse extends ListingResponse {

	private final int availability;

	private final int dealType;

	private final int category;

	public PartListingResponse(PartListing listing) {
		super(listing);

		this.availability = listing.getAvailability().ordinal();
		this.dealType = listing.getDealType().ordinal();
		this.category = listing.getCategory().ordinal();

	}

}
