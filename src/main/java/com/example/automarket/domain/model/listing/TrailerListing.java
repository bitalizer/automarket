package com.example.automarket.domain.model.listing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Entity
@AllArgsConstructor
@Table(name = "trailer_listings")
public class TrailerListing extends VehicleListing implements Loadable {

    @Column(nullable = false)
    private Integer payload;

    @Override
    public Integer getPayload() {
        return payload;
    }

    @Override
    public void setPayload(Integer payload) {
        this.payload = payload;
    }
}