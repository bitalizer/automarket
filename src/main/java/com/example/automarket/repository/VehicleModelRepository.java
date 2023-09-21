package com.example.automarket.repository;

import com.example.automarket.domain.model.listing.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleModelRepository
		extends JpaRepository<VehicleModel, Long>, JpaSpecificationExecutor<VehicleModel> {

}