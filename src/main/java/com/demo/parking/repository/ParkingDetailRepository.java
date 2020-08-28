package com.demo.parking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.parking.domain.ParkingDetail;
import com.demo.parking.domain.enumeration.VehicleType;

@SuppressWarnings("unused")
@Repository
public interface ParkingDetailRepository extends JpaRepository<ParkingDetail, Long> {

	Optional<ParkingDetail> findBySlotNumberAndParked(Integer slotNumber, Boolean parked);

	Optional<ParkingDetail> findByVehicleNumberAndParked(String vehicleNumber, Boolean parked);

	@Query(value = "SELECT pd.slotNumber  FROM ParkingDetail pd where pd.parked=true and pd.vehicleType=?1")
	List<Integer> findParkingSlotByVehicleType(VehicleType vehicleType);
}
