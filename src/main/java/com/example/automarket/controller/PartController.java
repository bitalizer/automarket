package com.example.automarket.controller;

import com.example.automarket.domain.dto.response.PartListingResponse;
import com.example.automarket.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

	private final PartService partService;

	@GetMapping
	public List<PartListingResponse> getAllListings() {
		return partService.getAllListings();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PartListingResponse> getListingById(@PathVariable("id") Long listingId) {
		Optional<PartListingResponse> optionalListingResponse = partService.getListingById(listingId);

		if (optionalListingResponse.isPresent()) {
			PartListingResponse listingResponse = optionalListingResponse.get();
			return ResponseEntity.ok(listingResponse);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteListingById(@PathVariable("id") Long listingId) {
		Optional<PartListingResponse> optionalListingResponse = partService.getListingById(listingId);

		if (optionalListingResponse.isPresent()) {
			partService.deleteListingById(listingId);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}