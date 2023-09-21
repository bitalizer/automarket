package com.example.automarket.repository;

import com.example.automarket.domain.model.listing.vehicle.VehicleListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository
		extends JpaRepository<VehicleListing, Long>, JpaSpecificationExecutor<VehicleListing> {

}