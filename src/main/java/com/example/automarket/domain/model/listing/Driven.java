package com.example.automarket.domain.model.listing;

import com.example.automarket.domain.DriveType;
import com.example.automarket.domain.TransmissionType;

public interface Driven {

	Integer getMileage();

	void setMileage(Integer mileage);

	String getPlateNumber();

	void setPlateNumber(String plateNumber);

	String vinCode();

	void setVinCode(String vinCode);

	TransmissionType getTransmissionType();

	void setTransmissionType(TransmissionType transmissionType);

	DriveType getDriveType();

	void setDriveType(DriveType wheelDriveType);

}