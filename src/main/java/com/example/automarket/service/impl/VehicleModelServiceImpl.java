package com.example.automarket.service.impl;

import com.example.automarket.domain.dto.response.VehicleModelResponse;
import com.example.automarket.domain.model.listing.VehicleModel;
import com.example.automarket.repository.VehicleModelRepository;
import com.example.automarket.service.VehicleModelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VehicleModelServiceImpl implements VehicleModelService {

	private final VehicleModelRepository vehicleModelRepository;

	private final ModelMapper mapper;

	@Override
	public List<VehicleModelResponse> getAllModels(Specification<VehicleModel> spec) {
		List<VehicleModel> vehicleModels = vehicleModelRepository.findAll(spec);
		return vehicleModels.stream().map(model -> mapper.map(model, VehicleModelResponse.class)).toList();
	}

}