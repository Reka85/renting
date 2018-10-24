package org.renting.rentanrv.repository;

import javax.annotation.PostConstruct;

import org.renting.rentanrv.model.User;
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
		return userRepository.save(user);
	}
	
}
