package org.renting.rentanrv.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.renting.rentanrv.model.Booking;

public class GuestCountValidator implements ConstraintValidator<ValidGuestCount, Booking > {

    @Override
    public void initialize(ValidGuestCount constraintAnnotation) {
    }

    @Override
    public boolean isValid(Booking booking, ConstraintValidatorContext context) {
        if ( booking == null ) {
            return true;
        }
        
        return booking.getGuestCount() <= booking.getVehicle().getNumberOfGuests();
    }
}
