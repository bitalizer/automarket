package com.example.automarket.domain.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PartListingResponse extends ListingResponse {

	private int availability;

	private int dealType;

	private int category;

}
