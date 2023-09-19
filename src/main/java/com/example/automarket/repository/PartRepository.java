package com.example.automarket.repository;

import com.example.automarket.domain.model.listing.part.PartListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<PartListing, Long> {

}