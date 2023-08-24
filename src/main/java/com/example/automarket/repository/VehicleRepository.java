package com.example.automarket.repository;

import com.example.automarket.domain.model.listing.VehicleListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleListing, Long> {
}