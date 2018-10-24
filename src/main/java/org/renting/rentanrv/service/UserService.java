package org.renting.rentanrv.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.renting.rentanrv.model.User;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
	User createNewUser(@NotNull @Valid User newUser);
	User getUserDetails(long userId);
}
