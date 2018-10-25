package org.renting.rentanrv;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.renting.rentanrv.model.User;
import org.renting.rentanrv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidationTests {
	@Autowired
	private UserService service;

	@Test
	public void test_registrationWithValidUserInfo_successful() {
		service.createNewUser(new User("mary", "smith", "mary@email.com", 25, "456-4578965"));
	}
	
	// -- testing blank and null values --
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithNull_fails() {
		service.createNewUser(null);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithBlankFirstName_fails() {
		service.createNewUser(new User(" ", "smith", "mary@email.com", 25, "456-4578965"));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithBlankLastName_fails() {
		service.createNewUser(new User("mary", " ", "mary@email.com", 25, "456-4578965"));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithBlankEmail_fails() {
		service.createNewUser(new User("mary", "smith", " ", 25, "456-4578965"));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithoutAge_fails() {
		service.createNewUser(new User(" ", "smith", "mary@email.com", null, "456-4578965"));
	}
	
	// -- testing format --
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithTooShortFirstName_fails() {
		service.createNewUser(new User("m", "smith", "mary@email.com", 25, "456-4578965"));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithTooShortLastName_fails() {
		service.createNewUser(new User("mary", "s", "mary@email.com", 25, "456-4578965"));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithLowAge_fails() {
		service.createNewUser(new User("mary", "smith", "mary@email.com", 17, "456-4578965"));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithInvalidEmailFormat_fails() {
		service.createNewUser(new User("mary", "smith", "maryemail.com", 25, "456-4578965"));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void test_userCreationWithInvalidPhoneNumberFormat_fails() {
		service.createNewUser(new User("mary", "smith", "maryemail.com", 25, "456-465"));
	}
}
