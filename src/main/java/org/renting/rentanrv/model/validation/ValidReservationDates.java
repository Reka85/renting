package org.renting.rentanrv.model.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { BookingDatesValidator.class })
@Documented
public @interface ValidReservationDates {
	String message() default "For reservation dates you must respect the minimum stay set by the owner";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
