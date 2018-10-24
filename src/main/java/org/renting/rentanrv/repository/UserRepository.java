package org.renting.rentanrv.repository;

import org.renting.rentanrv.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
}
