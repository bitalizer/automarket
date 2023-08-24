package com.example.automarket.domain.model.listing;

import com.example.automarket.domain.FuelType;

public interface Fuelable {
    FuelType getFuelType();

    void setFuelType(FuelType fuelType);
}