package com.example.automarket.controller;

import com.example.automarket.domain.dto.response.PartListingResponse;
import com.example.automarket.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
		return partService.getListingById(listingId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteListingById(@PathVariable("id") Long listingId) {
		return partService.getListingById(listingId).map(listingResponse -> {
			partService.deleteListingById(listingId);
			return ResponseEntity.noContent().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
	}

}