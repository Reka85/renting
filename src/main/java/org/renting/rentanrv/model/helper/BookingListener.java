package org.renting.rentanrv.model.helper;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.renting.rentanrv.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingListener {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PrePersist
	@PreUpdate
	public void calculateTotalPriceBeforeSave(Booking booking) {
		long diffInMilliseconds = booking.getCheckOut().getTime() - booking.getCheckIn().getTime();
		long diffInDays = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
		BigDecimal pricePerNight = booking.getVehicle().getPricePerNight();
		BigDecimal total = pricePerNight.multiply(BigDecimal.valueOf(diffInDays));
		booking.setTotalPrice(total);
		logger.info("total: {}", total);
	}
}
