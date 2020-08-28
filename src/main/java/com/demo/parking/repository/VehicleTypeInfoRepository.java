package com.demo.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.parking.domain.VehicleTypeInfo;
import com.demo.parking.domain.enumeration.VehicleType;

@SuppressWarnings("unused")
@Repository
public interface VehicleTypeInfoRepository extends JpaRepository<VehicleTypeInfo, Long> {

	Optional<VehicleTypeInfo> findByVehicleType(VehicleType vehicleType);
}
