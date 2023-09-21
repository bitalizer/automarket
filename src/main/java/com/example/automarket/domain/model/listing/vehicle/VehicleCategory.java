package com.example.automarket.domain.model.listing.vehicle;

import lombok.Getter;

@Getter
public enum VehicleCategory {

    // @formatter:off
	PASSENGER_CAR,
	SUV,
	COMMERCIAL_VEHICLE,
	MOTORCYCLE,
	WATERCRAFT,
	TRAILER,
	CARAVAN,
	CONSTRUCTION_MACHINERY,
	AGRICULTURAL_MACHINERY,
	FOREST_MACHINERY,
	COMMUNAL_MACHINERY,
	OTHER;
	// @formatter:on

    private final int id;

    VehicleCategory() {
        this.id = ordinal();
    }

}