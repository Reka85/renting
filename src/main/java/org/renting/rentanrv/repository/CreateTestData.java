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
		createTestUsers();
	}
	
	@Transactional
	private void createTestUsers() {
		User user = new User("Joe", "Smith", "joe@email.com", 25, "123-1234567");
		List<Vehicle> vehicles = user.getVehicles();
		
		BigDecimal price1 = new BigDecimal("30.5");
		vehicles.add(new Vehicle("Joe's vehicle", 5, "San José", 3, price1, 2, user));
		
		BigDecimal price2 = new BigDecimal("25.5");
		vehicles.add(new Vehicle("Joe's other vehicle", 10, "Buenos Aires", 7, price2, 1, user));
		
		BigDecimal price3 = new BigDecimal("27");
		vehicles.add(new Vehicle("Joe's vehicle in Spain", 4, "Valencia", 4, price3, 2, user));
		
		userRepository.save(user);
		
		//-- 2nd user --
		User user2 = new User("Rose", "Wright", "rose@email.com", 34, "456-4563214");
		List<Vehicle> vehicles2 = user2.getVehicles();
		
		vehicles2.add(new Vehicle("Mary's vehicle", 5, "Brighton", 3, price1, 2, user2));
		
		vehicles2.add(new Vehicle("Mary's other vehicle", 10, "Liverpool", 7, price2, 1, user2));
		
		vehicles2.add(new Vehicle("Mary's vehicle in Yorkshire", 4, "Yorkshire", 4, price3, 2, user2));
		
		userRepository.save(user2);
	}
	
}
