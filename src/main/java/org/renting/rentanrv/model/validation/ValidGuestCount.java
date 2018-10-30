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
@Constraint(validatedBy = { GuestCountValidator.class })
@Documented
public @interface ValidGuestCount {

    String message() default "number of guests can not be more than the available places";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

