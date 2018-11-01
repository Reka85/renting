package org.renting.rentanrv.repository;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.renting.rentanrv.model.Booking;
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
		
		vehicles2.add(new Vehicle("Rose's vehicle", 5, "Brighton", 3, price1, 2, user2));
		
		vehicles2.add(new Vehicle("Rose's other vehicle", 10, "Liverpool", 7, price2, 1, user2));
		
		vehicles2.add(new Vehicle("Rose's vehicle in Yorkshire", 4, "Yorkshire", 4, price3, 2, user2));
				
		userRepository.save(user2);
		
		// -- make bookings --
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		Date checkin = calendar.getTime();
		
		calendar.add(Calendar.DATE, 12);
		Date checkout = calendar.getTime();
		
		List<Booking> bookings = user.getBookings();
		BigDecimal priceFirstBooking = new BigDecimal("25");
		bookings.add(new Booking(4, priceFirstBooking, checkin, checkout, user, vehicles2.get(1)));
		
		List<Booking> bookings2 = user2.getBookings();
		BigDecimal priceSecondBooking = new BigDecimal("45");
		bookings2.add(new Booking(5, priceSecondBooking, checkin, checkout, user2, vehicles.get(1)));
		
		userRepository.save(user);
		userRepository.save(user2);
	}
	
}
