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

import com.demo.parking.domain.enumeration.VehicleType;

/**
 * @author Gaurav_T
 */
@Entity
@Table(name = "vehicle_type_info", catalog = "parking")
public class VehicleTypeInfo implements java.io.Serializable {

	private Long id;
	private VehicleType vehicleType;
	private float costPerHour;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "vehicle_type", nullable = false, length = 225)
	public VehicleType getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name = "cost_per_hour", nullable = false, precision = 12, scale = 0)
	public float getCostPerHour() {
		return this.costPerHour;
	}

	public void setCostPerHour(float costPerHour) {
		this.costPerHour = costPerHour;
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

}
