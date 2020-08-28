package com.demo.parking.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.demo.parking.domain.enumeration.Color;
import com.demo.parking.domain.enumeration.VehicleType;

/**
 * @author Gaurav_T
 */
@Entity
@Table(name = "parking_detail", catalog = "parking")
public class ParkingDetail implements java.io.Serializable {

	private Long id;
	private VehicleType vehicleType;
	private Instant checkIn;
	private Instant checkOut;
	private String vehicleNumber;
	private Color color;
	private float cost;
	private int slotNumber;
	private Boolean parked;
	private Instant createdDate;
	private Instant updatedDate;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "check_in")
	public Instant getCheckIn() {
		return this.checkIn;
	}

	public void setCheckIn(Instant checkIn) {
		this.checkIn = checkIn;
	}

	@Column(name = "check_out")
	public Instant getCheckOut() {
		return this.checkOut;
	}

	public void setCheckOut(Instant checkOut) {
		this.checkOut = checkOut;
	}

	@Column(name = "vehicle_number", nullable = false, length = 225)
	public String getVehicleNumber() {
		return this.vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "color", length = 225)
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Column(name = "slot_number", nullable = false)
	public int getSlotNumber() {
		return this.slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	@Column(name = "parked")
	public Boolean getParked() {
		return this.parked;
	}

	public void setParked(Boolean parked) {
		this.parked = parked;
	}

	@Column(name = "created_date")
	public Instant getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_date")
	public Instant getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Instant updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "vehicle_type", nullable = false, length = 225)
	public VehicleType getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name = "cost", nullable = false, precision = 12, scale = 0)
	public float getCost() {
		return this.cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
