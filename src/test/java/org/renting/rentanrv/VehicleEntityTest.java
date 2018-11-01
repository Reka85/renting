package org.renting.rentanrv;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.renting.rentanrv.model.Booking;
import org.renting.rentanrv.model.User;
import org.renting.rentanrv.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleEntityTest {
	@Autowired
	private TestEntityManager entityManager;
	
	private User testUser;
	private User rentingTestUser;
	private Vehicle testVehicle;
	private Booking firstTestBooking;
	private Booking secondTestBooking;
	
	@Before
	public void setUp() {
		testUser = new User("Joe", "Smith", "joe@email.com", 25, "123-1234567");
		rentingTestUser = new User("Jane", "Anderson", "jane@email.com", 34, "123-1237890");
		
		BigDecimal price1 = new BigDecimal("34");
		testVehicle = new Vehicle("Joe's vehicle", 5, "Venice Beach", 5, price1, 1, testUser);
		
		// -- making bookings --
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		Date checkin = calendar.getTime();
		
		calendar.add(Calendar.DATE, 12);
		Date checkout = calendar.getTime();
		
		BigDecimal bookingPrice = new BigDecimal("148");
		firstTestBooking = new Booking(4, bookingPrice, checkin, checkout, rentingTestUser, testVehicle);
		secondTestBooking = new Booking(2, bookingPrice, checkin, checkout, rentingTestUser, testVehicle);
	}
	
	@Test
	public void saveVehicle() {
		User savedUser = this.entityManager.persistAndFlush(testUser);
		Vehicle savedVehicle = this.entityManager.persistAndFlush(testVehicle);
		assertThat(savedVehicle.getName()).isEqualTo("Joe's vehicle");
	}
	
	@Test
	public void saveVehicleBookings() {
		testVehicle.getBookings().add(firstTestBooking);
		testVehicle.getBookings().add(secondTestBooking);
		
		User savedUser = this.entityManager.persistFlushFind(testUser);
		User savedRentingUser = this.entityManager.persistFlushFind(rentingTestUser);
		Vehicle savedVehicle = this.entityManager.persistFlushFind(testVehicle);
		
		assertThat(savedVehicle.getId()).isNotNull();
		assertThat(savedVehicle.getName()).isEqualTo("Joe's vehicle");
		assertThat(testVehicle.getBookings().size()).isEqualTo(2);
	}
	
	@Test
	public void removeVehicleBookings() {
		testVehicle.getBookings().add(firstTestBooking);
		testVehicle.getBookings().add(secondTestBooking);

		User savedUser = this.entityManager.persistFlushFind(testUser);
		User savedRentingUser = this.entityManager.persistFlushFind(rentingTestUser);
		Vehicle savedVehicle = this.entityManager.persistFlushFind(testVehicle);
		
		savedVehicle.getBookings().remove(0);
		assertThat(savedVehicle.getBookings().size()).isEqualTo(1);
		assertThat(savedVehicle.getBookings().get(0).getGuestCount()).isEqualTo(2);
	}
}
