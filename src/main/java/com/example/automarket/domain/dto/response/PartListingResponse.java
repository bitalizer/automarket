package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.part.DealType;
import com.example.automarket.domain.model.listing.part.PartAvailability;
import com.example.automarket.domain.model.listing.part.PartCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PartListingResponse extends ListingResponse {

	@NotNull
	private PartAvailability availability;

	@NotNull
	private DealType dealType;

	@NotNull
	private PartCategory category;

}
