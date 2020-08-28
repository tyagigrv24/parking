package com.demo.parking.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.demo.parking.domain.enumeration.Color;
import com.demo.parking.domain.enumeration.VehicleType;

public class ParkingVehicleDTO {

	@NotNull
	@Size(min = 4, max = 4, message = "Please enter 4 digit Vehical number")
	@Pattern(regexp = "[\\d]{4}", message = "Please enter 4 digit Vehical number")
	private String vehicleNumber;

	@NotNull(message = "Vehicle Type is required.")
	private VehicleType vehicleType;

	private Color color;

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
