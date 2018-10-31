package org.renting.rentanrv.model.validation;

import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.renting.rentanrv.model.Booking;

public class BookingDatesValidator implements ConstraintValidator<ValidReservationDates, Booking > {
	 @Override
	    public void initialize(ValidReservationDates constraintAnnotation) {
	    }

	    @Override
	    public boolean isValid(Booking booking, ConstraintValidatorContext context) {
	        if ( booking == null ) {
	            return true;
	        }
	        int minStay = booking.getVehicle().getMinStay();
	        long diffInMilliseconds = booking.getCheckOut().getTime() - booking.getCheckIn().getTime();
	        long diffInDays = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
	        return diffInDays >= minStay;
	 
	    }
}
