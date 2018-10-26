package org.renting.rentanrv.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.renting.rentanrv.model.Vehicle;
import org.springframework.validation.annotation.Validated;

@Validated
public interface VehicleService {
	Vehicle createNewVehicle(@NotNull @Valid Vehicle newVehicle);
	Vehicle getVehicleDetails(long vehicleId);
	// get vehicles of a user
	// show top x vehicles
	// search for vehicle by localisation / name
}
