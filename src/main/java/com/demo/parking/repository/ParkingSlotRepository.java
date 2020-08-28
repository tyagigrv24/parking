package com.demo.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.parking.domain.ParkingSlot;
import com.demo.parking.domain.enumeration.VehicleType;

@SuppressWarnings("unused")
@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

	Optional<ParkingSlot> findByVehicleType(VehicleType vehicleType);
}
