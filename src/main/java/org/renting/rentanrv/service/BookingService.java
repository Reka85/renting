package org.renting.rentanrv.service;

import java.util.List;

import org.renting.rentanrv.model.Booking;

public interface BookingService {
	List<Booking> getBookingsByUserId(Long userId);
}
