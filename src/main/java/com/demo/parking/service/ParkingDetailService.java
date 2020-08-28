package com.demo.parking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.parking.domain.ParkingDetail;
import com.demo.parking.domain.enumeration.VehicleType;
import com.demo.parking.repository.ParkingDetailRepository;

/**
 * 
 * @author Gaurav_T
 * @since 27-08-2020
 * 
 *        Parking Detail service
 *
 */

@Service
@Transactional
public class ParkingDetailService {

	private final Logger log = LoggerFactory.getLogger(ParkingDetailService.class);

	private final ParkingDetailRepository parkingDetailRepository;

	public ParkingDetailService(ParkingDetailRepository parkingDetailRepository) {
		this.parkingDetailRepository = parkingDetailRepository;
	}

	/**
	 * Save a ParkingDetail.
	 *
	 * @param ParkingDetail the entity to save
	 * @return the persisted entity
	 */
	public ParkingDetail save(ParkingDetail parkingDetail) {
		log.debug("Request to save ParkingDetail : {}", parkingDetail);
		ParkingDetail result = parkingDetailRepository.save(parkingDetail);
		return result;
	}

	/**
	 * Get all the ParkingDetails.
	 *
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public List<ParkingDetail> findAll() {
		log.debug("Request to get all ParkingDetails");
		return parkingDetailRepository.findAll();
	}

	/**
	 * Get one ParkingDetail by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<ParkingDetail> findOne(Long id) {
		log.debug("Request to get ParkingDetail : {}", id);
		return parkingDetailRepository.findById(id);
	}

	/**
	 * Delete the ParkingDetail by id.
	 *
	 * @param id the id of the entity
	 */
	public void delete(Long id) {
		log.debug("Request to delete ParkingDetail : {}", id);
		parkingDetailRepository.deleteById(id);
	}

	/**
	 * Get one ParkingDetail by slot number.
	 *
	 * @param slotNumber
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<ParkingDetail> findSlotDetails(Integer slotNumber) {
		log.debug("Request to get ParkingDetail : {}", slotNumber);
		return parkingDetailRepository.findBySlotNumberAndParked(slotNumber, true);
	}

	/**
	 * Get one ParkingDetail by vehical number.
	 *
	 * @param slotNumber
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<ParkingDetail> findParkedVehicle(String vehicleNumber) {
		log.debug("Request to get ParkingDetail : {}", vehicleNumber);
		return parkingDetailRepository.findByVehicleNumberAndParked(vehicleNumber, true);
	}

	/**
	 * Get all slot by vehicle type.
	 *
	 * @param vehicleType
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public List<Integer> findParkedVehicleSlots(VehicleType vehicleType) {
		log.debug("Request to get ParkingDetail : {}", vehicleType);
		System.out.println("vehicleType::"+vehicleType);
		return parkingDetailRepository.findParkingSlotByVehicleType(vehicleType);
	}
}
