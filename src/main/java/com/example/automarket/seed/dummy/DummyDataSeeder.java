package com.example.automarket.seed.dummy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(3)
@Profile("dev")
public class DummyDataSeeder implements CommandLineRunner {

	private final UserSeeder userSeeder;

	@Lazy
	private final ListingSeeder listingSeeder;

	@Override
	public void run(String... args) {
		log.info("Seeding dummy data...");
		userSeeder.run(50);
		listingSeeder.run(150);
	}

}
