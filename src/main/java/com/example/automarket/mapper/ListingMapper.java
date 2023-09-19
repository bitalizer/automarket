package com.example.automarket.mapper;

import com.example.automarket.domain.dto.request.ListingRequest;
import com.example.automarket.domain.dto.response.ListingResponse;
import com.example.automarket.domain.model.listing.Listing;

public interface ListingMapper<T extends Listing, R extends ListingResponse, Q extends ListingRequest> {

	/**
	 * Maps a Listing entity to a ListingResponse DTO.
	 * @param listing The Listing entity to be mapped.
	 * @return The mapped ListingResponse DTO.
	 * @throws IllegalStateException If the category is not implemented.
	 */
	R fromEntity(T listing);

	/**
	 * Maps a ListingRequest DTO to a Listing entity.
	 * @param listingRequest The ListingRequest DTO to be mapped.
	 * @return The mapped Listing entity.
	 * @throws IllegalArgumentException If the category is unsupported.
	 */
	T toEntity(Q listingRequest);

}
