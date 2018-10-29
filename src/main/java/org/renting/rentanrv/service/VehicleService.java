package org.renting.rentanrv.service;

import java.util.List;

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
	Page <Vehicle> searchByNameOrLocalisation(String searchCriteria, Pageable page);
	List<Vehicle> getVehiclesByUserId(Long userId);
	void deleteVehicleById(Long vehicleId);
	Vehicle getVehicleById(long vehicleId);
}
