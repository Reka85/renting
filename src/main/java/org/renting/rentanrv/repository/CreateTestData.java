package org.renting.rentanrv.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.renting.rentanrv.model.User;
import org.renting.rentanrv.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateTestData {
	// -- create users --
	// fields: firstName, lastName, emailAddress, age, phoneNumber
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void createData() {
		createTestUser();
	}
	
	@Transactional
	private User createTestUser() {
		User user = new User("Joe", "Smith", "joe@email.com", 25, "123-1234567");
		List<Vehicle> vehicles = user.getVehicles();
		
		BigDecimal price1 = new BigDecimal("30.5");
		vehicles.add(new Vehicle("Joe's vehicle", 5, "San José", 3, price1 ,2, user));
		
		BigDecimal price2 = new BigDecimal("25.5");
		vehicles.add(new Vehicle("Joe's other vehicle", 10, "Buenos Aires", 7, price2, 1, user));
		
		return userRepository.save(user);
	}
	
}
