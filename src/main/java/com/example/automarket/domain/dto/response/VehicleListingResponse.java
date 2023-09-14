package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.vehicle.VehicleCategory;
import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
import com.example.automarket.domain.model.listing.vehicle.VehicleSubCategory;
import lombok.Getter;

@Getter

public abstract class VehicleListingResponse extends ListingResponse {

	private final Integer productionYear;

	private final boolean auction;

	private final boolean exchangeable;

	private final VehicleCategory category;

	private final VehicleSubCategory subCategory;

	protected VehicleListingResponse(VehicleListing listing) {
		super(listing);

		this.productionYear = listing.getProductionYear();
		this.auction = listing.isAuction();
		this.exchangeable = listing.isExchangeable();
		this.category = listing.getCategory();
		this.subCategory = listing.getSubCategory();
	}

}
