package org.renting.rentanrv.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.renting.rentanrv.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface VehicleService {
	Vehicle createNewVehicle(@NotNull @Valid Vehicle newVehicle);
	Vehicle getVehicleDetails(long vehicleId);
	//Page <Vehicle> getAllVehicles(Pageable pageable);
	Page <Vehicle> searchByNameOrLocalisation(String searchCriteria, Pageable page);
}
