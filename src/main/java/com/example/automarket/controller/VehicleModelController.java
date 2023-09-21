package com.example.automarket.controller;

import com.example.automarket.domain.dto.response.VehicleModelResponse;
import com.example.automarket.domain.model.listing.VehicleModel;
import com.example.automarket.service.VehicleModelService;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/models")
public class VehicleModelController {

	private final VehicleModelService vehicleModelService;

	@GetMapping
	@Cacheable(value = "models", key = "#brand", condition = "#brand != null")
	public List<VehicleModelResponse> getModels(@RequestParam(name = "brand", required = false) String brand,
			@And({ @Spec(path = "brand.name", params = "brand",
					spec = LikeIgnoreCase.class) }) Specification<VehicleModel> spec) {
		return vehicleModelService.getAllModels(spec);
	}

}