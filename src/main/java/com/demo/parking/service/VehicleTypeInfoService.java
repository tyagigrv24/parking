package com.demo.parking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.parking.domain.VehicleTypeInfo;
import com.demo.parking.domain.enumeration.VehicleType;
import com.demo.parking.repository.VehicleTypeInfoRepository;

@Service
@Transactional
public class VehicleTypeInfoService {

	private final Logger log = LoggerFactory.getLogger(VehicleTypeInfoService.class);

	private final VehicleTypeInfoRepository vehicleTypeInfoRepository;

	public VehicleTypeInfoService(VehicleTypeInfoRepository vehicleTypeInfoRepository) {
		this.vehicleTypeInfoRepository = vehicleTypeInfoRepository;
	}

	/**
	 * Save a VehicleTypeInfo.
	 *
	 * @param VehicleTypeInfo the entity to save
	 * @return the persisted entity
	 */
	public VehicleTypeInfo save(VehicleTypeInfo vehicleTypeInfo) {
		log.debug("Request to save VehicleDetail : {}", vehicleTypeInfo);
		VehicleTypeInfo result = vehicleTypeInfoRepository.save(vehicleTypeInfo);
		return result;
	}

	/**
	 * Get all the VehicleTypeInfos.
	 *
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public List<VehicleTypeInfo> findAll() {
		log.debug("Request to get all VehicleTypeInfos");
		return vehicleTypeInfoRepository.findAll();
	}

	/**
	 * Get one VehicleTypeInfo by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<VehicleTypeInfo> findOne(Long id) {
		log.debug("Request to get VehicleTypeInfo : {}", id);
		return vehicleTypeInfoRepository.findById(id);
	}

	/**
	 * Delete the VehicleTypeInfo by id.
	 *
	 * @param id the id of the entity
	 */
	public void delete(Long id) {
		log.debug("Request to delete VehicleTypeInfo : {}", id);
		vehicleTypeInfoRepository.deleteById(id);
	}

	/**
	 * Get one VehicleTypeInfo by id.
	 *
	 * @param vehicleType
	 * @return the entity
	 */
	@Transactional(readOnly = true)
	public Optional<VehicleTypeInfo> findByVehicleType(VehicleType vehicleType) {
		log.debug("Request to get VehicleTypeInfo : {}", vehicleType);
		return vehicleTypeInfoRepository.findByVehicleType(vehicleType);
	}
}
