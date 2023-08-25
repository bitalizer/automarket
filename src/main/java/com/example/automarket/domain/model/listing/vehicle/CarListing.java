package com.example.automarket.domain.model.listing.vehicle;

import com.example.automarket.domain.Color;
import com.example.automarket.domain.DriveType;
import com.example.automarket.domain.FuelType;
import com.example.automarket.domain.TransmissionType;
import com.example.automarket.domain.model.listing.Colorable;
import com.example.automarket.domain.model.listing.Driven;
import com.example.automarket.domain.model.listing.Fuelable;
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
public class CarListing extends VehicleListing implements Fuelable, Driven, Colorable {

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private FuelType fuelType;

    @Column(nullable = false)
    private TransmissionType transmissionType;

    @Column(nullable = false)
    private DriveType driveType;

    @Column
    private Color color;

    @Column
    private String plateNumber;

    @Column
    private String vinCode;

    @Override
    public Integer getMileage() {
        return mileage;
    }

    @Override
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Override
    public String getPlateNumber() {
        return plateNumber;
    }

    @Override
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public String vinCode() {
        return vinCode;
    }

    @Override
    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    @Override
    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    @Override
    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public DriveType getDriveType() {
        return driveType;
    }

    @Override
    public void setDriveType(DriveType driveType) {
        this.driveType = driveType;
    }

    @Override
    public FuelType getFuelType() {
        return fuelType;
    }

    @Override
    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}