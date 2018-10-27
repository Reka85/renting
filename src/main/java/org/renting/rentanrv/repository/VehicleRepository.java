package org.renting.rentanrv.repository;

import org.renting.rentanrv.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
	Page<Vehicle> findAllByOrderByLocalisation(Pageable pageable);
}
