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
public class BookingEntityTests {
	@Autowired
	private TestEntityManager entityManager;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private User ownerUser;
	private User rentingUser;

	private Booking firstBooking;
	private Booking secondBooking;
	
	private Vehicle vehicle;

	@Before
	public void setUp() {
		ownerUser = new User("Joe", "Smith", "joe@email.com", 25, "123-1234567");
		rentingUser = new User("Jane", "Anderson", "jane@email.com", 34, "123-1237890");
		
		BigDecimal price1 = new BigDecimal("34");
		vehicle = new Vehicle("Joe's vehicle", 5, "Venice Beach", 5, price1, 1, ownerUser);
		
		// -- making bookings --
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		Date checkin = calendar.getTime();
		
		calendar.add(Calendar.DATE, 12);
		Date checkout = calendar.getTime();
		
		BigDecimal bookingPrice = new BigDecimal("148");
		
		firstBooking = new Booking(4, bookingPrice, checkin, checkout, rentingUser, vehicle);
		secondBooking = new Booking(2, bookingPrice, checkin, checkout, rentingUser, vehicle);
	}
	
	@Test
	public void saveBooking() {		
		User savedOwnerUser = this.entityManager.persistFlushFind(ownerUser);
		User savedRentingUser = this.entityManager.persistFlushFind(rentingUser);
		Vehicle savedVehicle = this.entityManager.persistFlushFind(vehicle);
		Booking savedFirstBooking = this.entityManager.persistFlushFind(firstBooking);
		
		assertThat(savedFirstBooking.getId()).isNotNull();
		assertThat(savedFirstBooking.getVehicle().getName()).isEqualTo("Joe's vehicle");
		assertThat(savedFirstBooking.getUser().getFirstName()).isEqualTo("Jane");
	}
	
	@Test
	public void removeVehicleBookings() {
		User savedOwnerUser = this.entityManager.persistFlushFind(ownerUser);
		User savedRentingUser = this.entityManager.persistFlushFind(rentingUser);
		Vehicle savedVehicle = this.entityManager.persistFlushFind(vehicle);
		Booking savedFirstBooking = this.entityManager.persistFlushFind(firstBooking);
		Booking savedSecondBooking = this.entityManager.persistFlushFind(secondBooking);
		
		savedVehicle.getBookings().remove(0);
		assertThat(savedVehicle.getBookings().size()).isEqualTo(1);
		assertThat(savedVehicle.getBookings().get(0).getGuestCount()).isEqualTo(2);
	}
}
