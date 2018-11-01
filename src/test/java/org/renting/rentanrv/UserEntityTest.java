package org.renting.rentanrv;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class UserEntityTest {
	
	@Autowired
	private TestEntityManager entityManager;

	private User testUser;
	private User rentingTestUser;
	private Vehicle firstTestVehicle;
	private Vehicle secondTestVehicle;
	private Booking firstTestBooking;
	private Booking secondTestBooking;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
		testUser = new User("Joe", "Smith", "joe@email.com", 25, "123-1234567");
		rentingTestUser = new User("Jane", "Anderson", "jane@email.com", 34, "123-1237890");
		
		// -- making vehicles
		BigDecimal price1 = new BigDecimal("34");
		BigDecimal price2 = new BigDecimal("40.5");
		firstTestVehicle = new Vehicle("Joe's vehicle", 5, "Venice Beach", 5, price1, 1, testUser);
		secondTestVehicle = new Vehicle("Joe's second vehicle", 3, "Long Island", 2, price2, 2, testUser);
		
		// -- making bookings --
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		Date checkin = calendar.getTime();
		
		calendar.add(Calendar.DATE, 12);
		Date checkout = calendar.getTime();
		
		BigDecimal bookingPrice = new BigDecimal("148");
		firstTestBooking = new Booking(4, bookingPrice, checkin, checkout, rentingTestUser, firstTestVehicle);
		secondTestBooking = new Booking(2, bookingPrice, checkin, checkout, rentingTestUser, secondTestVehicle);
	}
	
	@Test
	public void saveUser() {
		User savedUser = this.entityManager.persistAndFlush(testUser);
		assertThat(savedUser.getFirstName()).isEqualTo("Joe");
	}
	
	@Test
	public void saveUserVehicles() {
		testUser.getVehicles().add(firstTestVehicle);
		testUser.getVehicles().add(secondTestVehicle);

		User savedUser = this.entityManager.persistFlushFind(testUser);

		assertThat(savedUser.getId()).isNotNull();
		assertThat(savedUser.getFirstName()).isEqualTo("Joe");
		assertThat(testUser.getVehicles().size()).isEqualTo(2);
	}
	
	@Test
	public void removeUserVehicle() {
		testUser.getVehicles().add(firstTestVehicle);
		testUser.getVehicles().add(secondTestVehicle);

		User savedUser = this.entityManager.persistFlushFind(testUser);

		savedUser.getVehicles().remove(0);

		User updatedUser = this.entityManager.persistAndFlush(savedUser);
		assertThat(updatedUser.getVehicles().size()).isEqualTo(1);
		assertThat(updatedUser.getVehicles().get(0).getName()).isEqualTo("Joe's second vehicle");
	}
	
	@Test
	public void saveUserBookings() {
		testUser.getVehicles().add(firstTestVehicle);
		testUser.getVehicles().add(secondTestVehicle);
		
		rentingTestUser.getBookings().add(firstTestBooking);
		rentingTestUser.getBookings().add(secondTestBooking);

		User savedUser = this.entityManager.persistFlushFind(testUser);

		assertThat(savedUser.getId()).isNotNull();
		assertThat(savedUser.getFirstName()).isEqualTo("Joe");
		assertThat(rentingTestUser.getBookings().size()).isEqualTo(2);
	}
	
	@Test
	public void removeUserBooking() {
		testUser.getVehicles().add(firstTestVehicle);
		testUser.getVehicles().add(secondTestVehicle);
		
		rentingTestUser.getBookings().add(firstTestBooking);
		rentingTestUser.getBookings().add(secondTestBooking);

		User savedUser = this.entityManager.persistFlushFind(testUser);
		
		User savedRentingUser = this.entityManager.persistFlushFind(rentingTestUser);

		savedRentingUser.getBookings().remove(0);

		User updatedUser = this.entityManager.persistAndFlush(savedRentingUser);
		assertThat(updatedUser.getBookings().size()).isEqualTo(1);
		assertThat(updatedUser.getBookings().get(0).getGuestCount()).isEqualTo(2);
	}
}
