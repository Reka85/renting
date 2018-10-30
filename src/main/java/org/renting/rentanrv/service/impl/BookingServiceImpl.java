package org.renting.rentanrv.service.impl;

import java.util.List;

import org.renting.rentanrv.model.Booking;
import org.renting.rentanrv.repository.BookingRepository;
import org.renting.rentanrv.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public List<Booking> getBookingsByUserId(Long userId) {
		List<Booking> bookings = bookingRepository.findAllByUserIdOrderByCheckInDesc(userId);
		return bookings;
	}
}