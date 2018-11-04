package org.renting.rentanrv.service.impl;

import java.text.MessageFormat;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.renting.rentanrv.model.Vehicle;
import org.renting.rentanrv.repository.VehicleRepository;
import org.renting.rentanrv.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Override
	public Vehicle createNewVehicle(@NotNull @Valid Vehicle newVehicle) {
		Vehicle vehicle = vehicleRepository.save(newVehicle);
		return vehicle;
	}

	@Override
	public Vehicle getVehicleDetails(Long vehicleId) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
		return vehicle;
	}
	
	@Override
	public Page<Vehicle> searchByNameOrLocalisation(String searchCriteria, Pageable page){
		Page<Vehicle> vehicles = null;
		
		if (searchCriteria == null || searchCriteria.length() == 0) {
			vehicles = vehicleRepository.findAllByOrderByLocalisation(page);
		} else {
			searchCriteria = MessageFormat.format("%{0}%", searchCriteria.trim());
			vehicles = vehicleRepository.findByNameIgnoreCaseLikeOrLocalisationIgnoreCaseLikeOrderByName(searchCriteria, page);
		}
		return vehicles;
	}

	@Override
	public List<Vehicle> getVehiclesByUserId(Long userId) {
		List<Vehicle> vehicles = vehicleRepository.findAllByUserIdOrderByNameAsc(userId);
		return vehicles;
	}

	@Override
	public void deleteVehicleById(Long vehicleId) {
		vehicleRepository.deleteById(vehicleId);
		
	}

	@Override
	public Vehicle getVehicleById(Long vehicleId) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
		return vehicle;
	}
	
	
	
	
}
