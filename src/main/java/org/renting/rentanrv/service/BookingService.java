package org.renting.rentanrv.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.renting.rentanrv.model.Booking;

public interface BookingService {
	List<Booking> getBookingsByUserId(Long userId);
	Booking createNewBooking(@NotNull @Valid Booking newBooking);
}
