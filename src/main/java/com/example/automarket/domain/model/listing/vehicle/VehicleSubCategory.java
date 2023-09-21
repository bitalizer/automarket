package com.example.automarket.domain.model.listing.vehicle;

import lombok.Getter;

@Getter
public enum VehicleSubCategory {
    // @formatter:off
	// Passenger Car Subcategories
	SEDAN(VehicleCategory.PASSENGER_CAR),
	HATCHBACK(VehicleCategory.PASSENGER_CAR),
	TOURING(VehicleCategory.PASSENGER_CAR),
	MINIVAN(VehicleCategory.PASSENGER_CAR),
	COUPE(VehicleCategory.PASSENGER_CAR),
	CABRIOLET(VehicleCategory.PASSENGER_CAR),

	// SUV Subcategories
	SUV_TOURING(VehicleCategory.SUV),
	SUV_PICKUP(VehicleCategory.SUV),
	SUV_OPEN(VehicleCategory.SUV),
	SUV_COUPE(VehicleCategory.SUV),

	// Commercial Vehicle Subcategories
	SMALL_COMMERCIAL_VEHICLE(VehicleCategory.COMMERCIAL_VEHICLE),
	COMMERCIAL_VEHICLE(VehicleCategory.COMMERCIAL_VEHICLE),
	RIGID_COMMERCIAL_VEHICLE(VehicleCategory.COMMERCIAL_VEHICLE),
	SADDLE_TRUCK(VehicleCategory.COMMERCIAL_VEHICLE),
	RIGID_TRUCK(VehicleCategory.COMMERCIAL_VEHICLE),
	CHASSIS_TRUCK(VehicleCategory.COMMERCIAL_VEHICLE),

	// Motorcycle Subcategories
	CLASSICAL_MOTORCYCLE(VehicleCategory.MOTORCYCLE),
	SCOOTER(VehicleCategory.MOTORCYCLE),
	MOPED(VehicleCategory.MOTORCYCLE),
	BIKE(VehicleCategory.MOTORCYCLE),
	CRUISER_CHOPPER(VehicleCategory.MOTORCYCLE),
	TOURING_MOTORCYCLE(VehicleCategory.MOTORCYCLE),
	MOTOCROSS_BIKE(VehicleCategory.MOTORCYCLE),
	ENDURO_SUPERMOTO(VehicleCategory.MOTORCYCLE),
	TRIAL_MOTORCYCLE(VehicleCategory.MOTORCYCLE),

	// Watercraft Subcategories
	WATER_LAUNCH_MOTORBOAT(VehicleCategory.WATERCRAFT),
	WATER_YACHT_SAILBOAT(VehicleCategory.WATERCRAFT),
	WATER_WATERSCOOTER(VehicleCategory.WATERCRAFT),
	WATER_OTHER(VehicleCategory.WATERCRAFT),

	// Trailer Subcategories
	TRAILER_LIGHT_TRAILER(VehicleCategory.TRAILER),
	TRAILER_SEMI_TRAILER(VehicleCategory.TRAILER),
	TRAILER_TRAILER(VehicleCategory.TRAILER),
	TRAILER_BOAT_TRAILER(VehicleCategory.TRAILER),

	// Caravan Subcategories
	CARAVAN(VehicleCategory.CARAVAN),
	CARAVAN_TRAILER_TENT(VehicleCategory.CARAVAN),

	// Construction Machinery Subcategories
	CONSTRUCTION_MACHINERY_CRANE(VehicleCategory.CONSTRUCTION_MACHINERY),
	CONSTRUCTION_MACHINERY_CONCRETE_MIXER(VehicleCategory.CONSTRUCTION_MACHINERY),
	CONSTRUCTION_MACHINERY_EXCAVATOR(VehicleCategory.CONSTRUCTION_MACHINERY),
	CONSTRUCTION_MACHINERY_BULLDOZER(VehicleCategory.CONSTRUCTION_MACHINERY),
	CONSTRUCTION_MACHINERY_FORKLIFT(VehicleCategory.CONSTRUCTION_MACHINERY),
	CONSTRUCTION_MACHINERY_LOADER(VehicleCategory.CONSTRUCTION_MACHINERY),
	CONSTRUCTION_MACHINERY_LOADER_EXCAVATOR(VehicleCategory.CONSTRUCTION_MACHINERY),
	CONSTRUCTION_MACHINERY_ROAD_CONSTRUCTION(VehicleCategory.CONSTRUCTION_MACHINERY),
	CONSTRUCTION_MACHINERY_OTHER(VehicleCategory.CONSTRUCTION_MACHINERY),

	// Agricultural Machinery Subcategories
	AGRICULTURAL_MACHINERY_TRACTOR(VehicleCategory.AGRICULTURAL_MACHINERY),
	AGRICULTURAL_MACHINERY_COMBINE(VehicleCategory.AGRICULTURAL_MACHINERY),
	AGRICULTURAL_MACHINERY_MOWER(VehicleCategory.AGRICULTURAL_MACHINERY),
	AGRICULTURAL_MACHINERY_OTHER(VehicleCategory.AGRICULTURAL_MACHINERY),

	// Forest Machinery Subcategories
	FOREST_MACHINERY_HARVESTER(VehicleCategory.FOREST_MACHINERY),
	FOREST_MACHINERY_FORWARDER(VehicleCategory.FOREST_MACHINERY),
	FOREST_MACHINERY_OTHER(VehicleCategory.FOREST_MACHINERY),

	// Communal Machinery Subcategories
	COMMUNAL_MACHINERY_SWEEPING_MACHINE(VehicleCategory.COMMUNAL_MACHINERY),
	COMMUNAL_MACHINERY_GARBAGE_TRUCK(VehicleCategory.COMMUNAL_MACHINERY),
	COMMUNAL_MACHINERY_EXCREMENTS_REMOVAL(VehicleCategory.COMMUNAL_MACHINERY),
	COMMUNAL_MACHINERY_OTHER(VehicleCategory.COMMUNAL_MACHINERY),

	// Other Subcategories
	OTHER(VehicleCategory.OTHER);
	// @formatter:on

    private final int id;

    private final VehicleCategory category;

    VehicleSubCategory(VehicleCategory category) {
        this.id = ordinal();
        this.category = category;
    }

    public VehicleCategory getCategory() {
        return category;
    }

}