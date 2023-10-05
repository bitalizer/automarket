package com.example.automarket.seed.dummy;

import com.example.automarket.domain.model.listing.Listing;
import com.example.automarket.domain.model.listing.part.PartListing;
import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
import com.example.automarket.repository.PartRepository;
import com.example.automarket.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Order(4)
@Slf4j
@RequiredArgsConstructor
@Lazy
@Component
@Profile("dev")
public class ListingSeeder {

	private final ListingFactory factory;

	private final VehicleRepository vehicleRepository;

	private final PartRepository partRepository;

	public void run(int count) {
		log.info("Seeding {} dummy listings", count);
		factory.initialize();

		List<Listing> listings = IntStream.range(0, count).parallel().mapToObj(i -> factory.create()).toList();

		List<VehicleListing> vehicleListings = listings.stream()
			.filter(VehicleListing.class::isInstance)
			.map(VehicleListing.class::cast)
			.toList();

		List<PartListing> partsListings = listings.stream()
			.filter(PartListing.class::isInstance)
			.map(PartListing.class::cast)
			.toList();

		if (!vehicleListings.isEmpty()) {
			vehicleRepository.saveAll(vehicleListings);
		}

		if (!partsListings.isEmpty()) {
			partRepository.saveAll(partsListings);
		}
	}

}
