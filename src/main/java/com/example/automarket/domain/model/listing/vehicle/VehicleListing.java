package com.example.automarket.domain.model.listing.vehicle;

import com.example.automarket.domain.BodyType;
import com.example.automarket.domain.model.listing.Listing;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "vehicle_listings")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarListing.class, name = "car"),
        @JsonSubTypes.Type(value = TrailerListing.class, name = "trailer")
})
@Inheritance(strategy = InheritanceType.JOINED)
public class VehicleListing extends Listing {

    @Column
    private Integer productionYear;

    @Column
    private boolean auction;

    @Column
    private boolean exchangeable;

    @Column(nullable = false)
    private BodyType bodyType;
}
