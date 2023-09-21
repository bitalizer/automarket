package com.example.automarket.service;

import com.example.automarket.domain.dto.response.VehicleModelResponse;
import com.example.automarket.domain.model.listing.VehicleModel;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface VehicleModelService {

	List<VehicleModelResponse> getAllModels(Specification<VehicleModel> spec);

}
