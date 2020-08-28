package com.demo.parking;

import java.time.Instant;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.demo.parking.domain.ParkingSlot;
import com.demo.parking.domain.VehicleTypeInfo;
import com.demo.parking.domain.enumeration.VehicleType;
import com.demo.parking.service.ParkingSlotService;
import com.demo.parking.service.VehicleTypeInfoService;

@Component
public class DataLoader implements ApplicationRunner {

	private final ParkingSlotService parkingSlotService;
	private final VehicleTypeInfoService vehicleTypeInfoService;

	public DataLoader(ParkingSlotService parkingSlotService, VehicleTypeInfoService vehicleTypeInfoService) {
		this.parkingSlotService = parkingSlotService;
		this.vehicleTypeInfoService = vehicleTypeInfoService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		ParkingSlot parkingSlot1 = new ParkingSlot();
		parkingSlot1.setVehicleType(VehicleType.LARGE_VEHICLE);
		parkingSlot1.setStartingSlot(21);
		parkingSlot1.setEndingSlot(30);
		parkingSlot1.setCreatedDate(Instant.now());
		parkingSlot1.setUpdatedDate(Instant.now());
		parkingSlotService.save(parkingSlot1);

		ParkingSlot parkingSlot2 = new ParkingSlot();
		parkingSlot2.setVehicleType(VehicleType.MEDIUM_VEHICLE);
		parkingSlot2.setStartingSlot(11);
		parkingSlot2.setEndingSlot(20);
		parkingSlot2.setCreatedDate(Instant.now());
		parkingSlot2.setUpdatedDate(Instant.now());
		parkingSlotService.save(parkingSlot2);

		ParkingSlot parkingSlot3 = new ParkingSlot();
		parkingSlot3.setVehicleType(VehicleType.SMALL_VEHICLE);
		parkingSlot3.setStartingSlot(1);
		parkingSlot3.setEndingSlot(10);
		parkingSlot3.setCreatedDate(Instant.now());
		parkingSlot3.setUpdatedDate(Instant.now());
		parkingSlotService.save(parkingSlot3);

		VehicleTypeInfo vehicleTypeInfo1 = new VehicleTypeInfo();
		vehicleTypeInfo1.setVehicleType(VehicleType.LARGE_VEHICLE);
		vehicleTypeInfo1.setCostPerHour(30.0F);
		vehicleTypeInfo1.setCreatedDate(Instant.now());
		vehicleTypeInfo1.setUpdatedDate(Instant.now());
		vehicleTypeInfoService.save(vehicleTypeInfo1);

		VehicleTypeInfo vehicleTypeInfo2 = new VehicleTypeInfo();
		vehicleTypeInfo2.setVehicleType(VehicleType.MEDIUM_VEHICLE);
		vehicleTypeInfo2.setCostPerHour(20.0F);
		vehicleTypeInfo2.setCreatedDate(Instant.now());
		vehicleTypeInfo2.setUpdatedDate(Instant.now());
		vehicleTypeInfoService.save(vehicleTypeInfo2);

		VehicleTypeInfo vehicleTypeInfo3 = new VehicleTypeInfo();
		vehicleTypeInfo3.setVehicleType(VehicleType.SMALL_VEHICLE);
		vehicleTypeInfo3.setCostPerHour(10.0F);
		vehicleTypeInfo3.setCreatedDate(Instant.now());
		vehicleTypeInfo3.setUpdatedDate(Instant.now());
		vehicleTypeInfoService.save(vehicleTypeInfo3);

	}
}
