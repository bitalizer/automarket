package com.example.automarket.domain.model.listing;

import com.example.automarket.domain.model.Region;
import com.example.automarket.domain.model.User;
import com.example.automarket.domain.model.VehicleBrand;
import com.example.automarket.domain.model.VehicleModel;
import com.example.automarket.domain.model.base.BaseEntityAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "listings")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarListing.class, name = "car"),
        @JsonSubTypes.Type(value = TrailerListing.class, name = "trailer")
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Listing extends BaseEntityAudit {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private VehicleBrand brand;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private VehicleModel model;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;
}
