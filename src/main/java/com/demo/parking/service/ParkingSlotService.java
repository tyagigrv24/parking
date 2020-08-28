package com.demo.parking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.parking.domain.ParkingSlot;
import com.demo.parking.domain.enumeration.VehicleType;
import com.demo.parking.repository.ParkingSlotRepository;

/**
 * 
 * @author Gaurav_T
 * @since 27-08-2020
 * 
 *        Parking Slot service
 *
 */

@Service
@Transactional
public class ParkingSlotService {

	private final Logger log = LoggerFactory.getLogger(ParkingDetailService.class);

	private final ParkingSlotRepository parkingSlotRepository;

	public ParkingSlotService(ParkingSlotRepository parkingSlotRepository) {
		this.parkingSlotRepository = parkingSlotRepository;
	}

	/**
	 * Save a ParkingSlot.
	 *
	 * @param ParkingSlot the entity to save
	 * @return the persisted entity
	 */
	public ParkingSlot save(ParkingSlot parkingSlot) {
		log.debug("Request to save ParkingSlot : {}", parkingSlot);
		ParkingSlot result = parkingSlotRepository.save(parkingSlot);
		return result;
	}

	/**
	 * Get all the ParkingSlots.
	 *
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public List<ParkingSlot> findAll() {
		log.debug("Request to get all ParkingSlot");
		return parkingSlotRepository.findAll();
	}

	/**
	 * Get one ParkingSlot by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<ParkingSlot> findOne(Long id) {
		log.debug("Request to get ParkingSlot : {}", id);
		return parkingSlotRepository.findById(id);
	}

	/**
	 * Delete the ParkingSlot by id.
	 *
	 * @param id the id of the entity
	 */
	public void delete(Long id) {
		log.debug("Request to delete ParkingSlot : {}", id);
		parkingSlotRepository.deleteById(id);
	}

	/**
	 * Get ParkingSlot by VehicleType.
	 *
	 * @param vehicleType
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<ParkingSlot> findByVehicleType(VehicleType vehicleType) {
		log.debug("Request to get ParkingSlot : {}", vehicleType);
		return parkingSlotRepository.findByVehicleType(vehicleType);
	}
}
