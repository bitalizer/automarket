package com.example.automarket.domain.dto.response;

import com.example.automarket.domain.model.listing.VehicleListing;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import java.util.Date;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarListingResponse.class, name = "car"),
        @JsonSubTypes.Type(value = TrailerListingResponse.class, name = "trailer")
})
public abstract class VehicleListingResponse {
    private final Long id;
    private final String title;
    private final String description;
    private final Integer price;
    private final Integer productionYear;
    private final Long brandId;
    private final Long modelId;
    private final Long regionId;
    private final Date createdAt;
    private final Date updatedAt;

    public VehicleListingResponse(VehicleListing listing) {
        this.id = listing.getId();
        this.title = listing.getTitle();
        this.description = listing.getDescription();
        this.price = listing.getPrice();
        this.productionYear = listing.getProductionYear();
        this.brandId = listing.getBrand().getId();
        this.modelId = listing.getModel().getId();
        this.regionId = listing.getRegion() != null ? listing.getRegion().getId() : null;
        this.createdAt = listing.getCreatedAt();
        this.updatedAt = listing.getUpdatedAt();
    }
}