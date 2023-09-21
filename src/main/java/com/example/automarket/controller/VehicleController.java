package com.example.automarket.controller;

import com.example.automarket.domain.dto.request.VehicleListingRequest;
import com.example.automarket.domain.dto.response.VehicleListingResponse;
import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
import com.example.automarket.service.VehicleService;
import com.example.automarket.util.VehicleSortingOptions;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.kaczmarzyk.spring.data.jpa.web.annotation.OnTypeMismatch.IGNORE;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

	private final VehicleService vehicleService;

	@GetMapping
	public List<VehicleListingResponse> getAllListings() {
		return vehicleService.getAllListings();
	}

	@GetMapping("/search")
	public List<VehicleListingResponse> getFilteredListings(

			@RequestParam(name = "sortBy", required = false, defaultValue = "newest") String sortBy, @And({

					@Spec(path = "brand.id", params = "brand", spec = Equal.class, onTypeMismatch = IGNORE),
					@Spec(path = "model.id", params = "model", spec = Equal.class, onTypeMismatch = IGNORE),
					@Spec(path = "region.id", params = "region", spec = Equal.class, onTypeMismatch = IGNORE),
					@Spec(path = "category", params = "category", spec = Equal.class, onTypeMismatch = IGNORE),
					@Spec(path = "price", params = { "price_from", "price_to" }, spec = Between.class,
							onTypeMismatch = IGNORE),
					@Spec(path = "productionYear", params = { "year_from", "year_to" }, spec = Between.class,
							onTypeMismatch = IGNORE) }) Specification<VehicleListing> spec

	) {
		Sort sort = VehicleSortingOptions.getSort(sortBy);
		return vehicleService.getFilteredListings(spec, sort);
	}

	@GetMapping("/{id}")
	@Cacheable(value = "vehicles", key = "#listingId")
	public ResponseEntity<VehicleListingResponse> getListingById(@PathVariable("id") Long listingId) {
		return vehicleService.getListingById(listingId)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<VehicleListing> createListing(@RequestBody @Validated VehicleListingRequest listingRequest) {
		VehicleListing response = vehicleService.createListing(listingRequest);
		return ResponseEntity.ok(response);
	}

	@CacheEvict(cacheNames = "vehicles", key = "#listingId")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteListingById(@PathVariable("id") Long listingId) {
		return vehicleService.getListingById(listingId).map(listingResponse -> {
			vehicleService.deleteListingById(listingId);
			return ResponseEntity.noContent().<Void>build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

}