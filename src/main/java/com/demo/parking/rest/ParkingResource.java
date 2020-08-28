package com.demo.parking.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.parking.domain.ParkingDetail;
import com.demo.parking.domain.enumeration.VehicleType;
import com.demo.parking.dto.ParkingVehicleDTO;
import com.demo.parking.service.ParkingService;
import com.demo.parking.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class ParkingResource {

	private final Logger log = LoggerFactory.getLogger(ParkingResource.class);

	private final ParkingService parkingService;

	public ParkingResource(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	@PostMapping("/park")
	public ResponseEntity<ParkingDetail> parkVehicle(@Valid @RequestBody ParkingVehicleDTO parkingVehicleDTO)
			throws URISyntaxException {
		log.debug("REST request to park Vehicle : {}", parkingVehicleDTO);
		ParkingDetail result = parkingService.parkVehicle(parkingVehicleDTO);
		return ResponseEntity.created(new URI("/api/parking-details/" + result.getId())).body(result);
	}

	@PatchMapping("/unpark/vehicle/{vehicleNumber}")
	public ResponseEntity<ParkingDetail> unparkVehicle(
			@Valid @PathVariable("vehicleNumber") @Pattern(regexp = "[\\d]{4}", message = "Please Enter Valid Vehicle Number") String vehicleNumber) {
		log.debug("REST request to unparkpark Vehicle : {}", vehicleNumber);
		Optional<ParkingDetail> result = parkingService.unparkVehicle(vehicleNumber);
		return ResponseUtil.wrapOrNotFound(result);
	}

	@GetMapping("/slot/{number}")
	public ResponseEntity<ParkingDetail> slotDetails(@PathVariable("number") Integer slotNumber) {
		log.debug("REST request to unparkpark Vehicle : {}", slotNumber);
		Optional<ParkingDetail> result = parkingService.findSlotDetails(slotNumber);
		return ResponseUtil.wrapOrNotFound(result);

	}

	@GetMapping("/available-slot/{vehicleType}")
	public ResponseEntity<String> availableSlotDetails(@PathVariable("vehicleType") VehicleType vehicleType) {
		log.debug("REST request to unparkpark Vehicle : {}", vehicleType);
		return ResponseEntity.ok(parkingService.findAvailableSlotDetails(vehicleType));

	}
}
