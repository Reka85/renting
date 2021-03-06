package org.renting.rentanrv.service.impl;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.renting.rentanrv.model.Booking;
import org.renting.rentanrv.repository.BookingRepository;
import org.renting.rentanrv.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public List<Booking> getBookingsByUserId(Long userId) {
		List<Booking> bookings = bookingRepository.findAllByUserIdOrderByCheckInDesc(userId);
		return bookings;
	}

	@Override
	public Booking createNewBooking(@NotNull @Valid Booking newBooking) {
		Booking booking = bookingRepository.save(newBooking);
		return booking;
	}

	@Override
	@Transactional(readOnly = true)
	public Booking getBookingDetails(Long bookingId) {
		Booking booking = bookingRepository.findById(bookingId).get();
		return booking;
	}

	@Override
	public void deleteBookingById(Long bookingId) {
		bookingRepository.deleteById(bookingId);	
	}

}
