package com.example.automarket.domain.model.listing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "car_listings")
@AllArgsConstructor
public class CarListing extends Listing {

    @Column(nullable = false)
    private Integer productionYear;

    @Column(nullable = false)
    private Integer mileage;
}