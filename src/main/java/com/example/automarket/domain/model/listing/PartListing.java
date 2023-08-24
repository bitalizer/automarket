package com.example.automarket.domain.model.listing;

import com.example.automarket.domain.PartAvailability;
import com.example.automarket.domain.model.DealType;
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
@Table(name = "part_listings")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarListing.class, name = "car"),
        @JsonSubTypes.Type(value = TrailerListing.class, name = "trailer")
})
@Inheritance(strategy = InheritanceType.JOINED)
public class PartListing extends Listing {

    @Column
    private PartAvailability availability;
    @Column
    private DealType dealType;
}

