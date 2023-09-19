package com.example.automarket.domain.model.listing.vehicle;

import lombok.Getter;

@Getter
public enum VehicleCategory {

	PASSENGER_CAR, SUV, COMMERCIAL_VEHICLE, MOTORCYCLE, WATERCRAFT, TRAILER, CARAVAN, CONSTRUCTION_MACHINERY,
	AGRICULTURAL_MACHINERY, FOREST_MACHINERY, COMMUNAL_MACHINERY, OTHER;

	private final int id;

	VehicleCategory() {
		this.id = ordinal();
	}

}