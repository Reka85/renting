package org.renting.rentanrv.repository;

import java.util.List;

import org.renting.rentanrv.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
	// get all vehicles
	Page<Vehicle> findAllByOrderByLocalisation(Pageable pageable);
	
	// get vehicles that match location / name search
	Page<Vehicle> findByNameIgnoreCaseLikeOrLocalisationIgnoreCaseLikeOrderByName(
			String name, String localisation, Pageable page);

	List<Vehicle> findAllByUserIdOrderByNameDesc(Long userId);
}
