package org.renting.rentanrv.service.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.renting.rentanrv.model.Vehicle;
import org.renting.rentanrv.repository.VehicleRepository;
import org.renting.rentanrv.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Vehicle getVehicleDetails(long vehicleId) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
		return vehicle;
	}

}
