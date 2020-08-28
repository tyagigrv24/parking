package com.demo.parking.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.parking.domain.ParkingDetail;
import com.demo.parking.domain.ParkingSlot;
import com.demo.parking.domain.VehicleTypeInfo;
import com.demo.parking.domain.enumeration.VehicleType;
import com.demo.parking.dto.ParkingVehicleDTO;
import com.demo.parking.exception.BadRequestExceptionHandler;
import com.demo.parking.exception.RecordNotFoundException;

/**
 * 
 * @author Gaurav_T
 * @since 28-08-2020
 */
@Service
@Transactional
public class ParkingService {

	private final Logger log = LoggerFactory.getLogger(ParkingService.class);

	private final ParkingDetailService parkingDetailService;
	private final ParkingSlotService parkingSlotService;
	private final VehicleTypeInfoService vehicleTypeInfoService;

	public ParkingService(ParkingDetailService parkingDetailService, ParkingSlotService parkingSlotService,
			VehicleTypeInfoService vehicleTypeInfoService) {
		this.parkingDetailService = parkingDetailService;
		this.parkingSlotService = parkingSlotService;
		this.vehicleTypeInfoService = vehicleTypeInfoService;
	}

	/**
	 * park a vehicle.
	 *
	 * @param ParkingDetail the entity to save
	 * @return the persisted entity
	 */
	public ParkingDetail parkVehicle(ParkingVehicleDTO parkingVehicleDTO) {
		log.debug("Request to park a vehicle : {}", parkingVehicleDTO);
		Optional<ParkingDetail> existParkingDetail = parkingDetailService
				.findParkedVehicle(parkingVehicleDTO.getVehicleNumber());
		if (existParkingDetail.isPresent()) {
			throw new BadRequestExceptionHandler("Vehicle with this number is already parked.");
		}
		ParkingDetail parkingDetail = setParkingDetailsDTO(parkingVehicleDTO);
		ParkingDetail result = parkingDetailService.save(parkingDetail);
		return result;
	}

	private ParkingDetail setParkingDetailsDTO(ParkingVehicleDTO parkingVehicleDTO) {
		ParkingDetail parkingDetail = new ParkingDetail();
		parkingDetail.setCheckIn(Instant.now());
		parkingDetail.setVehicleNumber(parkingVehicleDTO.getVehicleNumber().toString());
		parkingDetail.setCreatedDate(Instant.now());
		parkingDetail.setParked(true);
		parkingDetail.setVehicleType(parkingVehicleDTO.getVehicleType());
		parkingDetail.setColor(parkingVehicleDTO.getColor());
		parkingDetail.setSlotNumber(findSlotToPark(parkingVehicleDTO));
		return parkingDetail;
	}

	private Integer findSlotToPark(ParkingVehicleDTO parkingVehicleDTO) {
		List<Integer> availableSlots = findAvailableSlots(parkingVehicleDTO.getVehicleType());
		if (availableSlots.size() > 0) {
			return availableSlots.get(0);
		} else {
			throw new RecordNotFoundException("Parking is full, No slot found!");
		}
	}

	private List<Integer> findAvailableSlots(VehicleType vehicleType) {
		Optional<ParkingSlot> parkingSlot = parkingSlotService.findByVehicleType(vehicleType);
		List<Integer> totalSlots = IntStream
				.rangeClosed(parkingSlot.get().getStartingSlot(), parkingSlot.get().getEndingSlot()).boxed()
				.collect(Collectors.toList());

		List<Integer> bookedParkingSlot = parkingDetailService.findParkedVehicleSlots(vehicleType);
		if (bookedParkingSlot.size() > 0) {
			totalSlots.removeAll(bookedParkingSlot);
		}
		return totalSlots;
	}

	/**
	 * unpark a vehicle.
	 *
	 * @param ParkingDetail the entity to save
	 * @return the persisted entity
	 */
	public Optional<ParkingDetail> unparkVehicle(String vehicleNumber) {
		log.debug("Request to unpark a vehicle : {}", vehicleNumber);
		Optional<ParkingDetail> existingParkingDetail = parkingDetailService.findParkedVehicle(vehicleNumber);
		if (existingParkingDetail.isPresent()) {
			ParkingDetail parkingDetail = existingParkingDetail.get();
			parkingDetail.setCheckOut(Instant.now());
			parkingDetail.setParked(false);
			parkingDetail.setUpdatedDate(Instant.now());
			parkingDetail.setCost(calculateParkingCost(parkingDetail));
			ParkingDetail result = parkingDetailService.save(parkingDetail);
			return Optional.of(result);
		} else {
			throw new RecordNotFoundException("Vehicle is not park with this number!");
		}

	}

	private Float calculateParkingCost(ParkingDetail parkingDetail) {
		long timeElapsed = Duration.between(parkingDetail.getCheckIn(), parkingDetail.getCheckOut()).toMillis();
		Optional<VehicleTypeInfo> vehicleTypeInfo = vehicleTypeInfoService
				.findByVehicleType(parkingDetail.getVehicleType());
		double hours = (double) timeElapsed / 3600000;
		float cost = (float) (Math.ceil(hours) * vehicleTypeInfo.get().getCostPerHour());
		return cost;
	}

	/**
	 * find slot details.
	 *
	 * @param ParkingDetail the entity to save
	 * @return the persisted entity
	 */
	public Optional<ParkingDetail> findSlotDetails(Integer slotNumber) {
		log.debug("Request to find slot details : {}", slotNumber);
		Optional<ParkingDetail> result = parkingDetailService.findSlotDetails(slotNumber);
		if (result.isPresent()) {
			return result;
		} else {
			throw new RecordNotFoundException("Slot is available to park the Vehicle!");
		}
	}

	/**
	 * find available slot details.
	 *
	 * @param vehicleType
	 *
	 */
	public String findAvailableSlotDetails(VehicleType vehicleType) {
		log.debug("Request to find available slot details : {}", vehicleType);
		String result = "";
		List<Integer> availableSlots = findAvailableSlots(vehicleType);
		if (availableSlots.size() > 0) {
			result = "Currently we have " + availableSlots.size() + " available slots.";
		} else {
			result = "Currently we have no slots available";
		}
		return result;
	}
}
