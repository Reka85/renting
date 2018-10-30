package org.renting.rentanrv.repository;

import java.util.List;

import org.renting.rentanrv.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
	List<Booking> findAllByUserIdOrderByCheckInDesc(Long userId);
}
