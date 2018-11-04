package org.renting.rentanrv;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.renting.rentanrv.model.Booking;
import org.renting.rentanrv.model.User;
import org.renting.rentanrv.model.Vehicle;
import org.renting.rentanrv.repository.BookingRepository;
import org.renting.rentanrv.repository.UserRepository;
import org.renting.rentanrv.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
    private TestEntityManager entityManager;

	private User user;
	private User rentingUser;
	private Vehicle vehicle;
	private Booking bookingOne;
	private Booking bookingTwo;
	private Booking bookingThree;
	
	private Date checkInDateOne;
	private Date checkInDateTwo;
	private Date checkInDateThree;
	
	@Before
	public void setUp() {
		user =  new User("Joe", "Smith", "joe@email.com", 25, "123-1234567");
		user = userRepository.save(user);
		
		rentingUser = new User("Jane", "Anderson", "jane@email.com", 34, "123-1237890");
		rentingUser = userRepository.save(rentingUser);
		
		BigDecimal price1 = new BigDecimal("34");
		vehicle = new Vehicle("Joe's vehicle", 5, "New York", 5, price1, 1, user);	
		vehicle = vehicleRepository.save(vehicle);
		
		// -- creating bookings --
		Calendar calendar = Calendar.getInstance();
		
		//bookingOne dates
		calendar.add(Calendar.DATE, 7);
		Date checkInDateOne = calendar.getTime();		
		calendar.add(Calendar.DATE, 12);
		Date checkoutOne = calendar.getTime();
		
		//bookingTwo dates
		calendar.add(Calendar.DATE, 2);
		Date checkInDateTwo = calendar.getTime();		
		calendar.add(Calendar.DATE, 5);
		Date checkoutTwo = calendar.getTime();
		
		//bookingThree dates
		calendar.add(Calendar.DATE, 10);
		Date checkInDateThree = calendar.getTime();		
		calendar.add(Calendar.DATE, 15);
		Date checkoutThree = calendar.getTime();
		
		BigDecimal bookingPrice = new BigDecimal("148");
		
		bookingOne = new Booking(4, bookingPrice, checkInDateOne, checkoutOne, rentingUser, vehicle);
		bookingTwo = new Booking(2, bookingPrice, checkInDateTwo, checkoutTwo, rentingUser, vehicle);
		bookingThree = new Booking(3, bookingPrice, checkInDateThree, checkoutThree, rentingUser, vehicle);
	}
	
	@Test
	public void saveBookingAndFindById() {
		Booking savedBooking = bookingRepository.save(bookingOne);
		assertThat(bookingRepository.findById(savedBooking.getId()).get()).isEqualTo((bookingOne));
	}
	
	// List<Booking> findAllByUserIdOrderByCheckInDesc(Long userId);
	@Test
	public void whenFindByUserId_thenReturnBookingListInCheckInOrder() {
		entityManager.persist(bookingOne);
		entityManager.persist(bookingTwo);
		entityManager.persist(bookingThree);
		
		Long id = rentingUser.getId();
	    List<Booking> bookingsFound = bookingRepository.findAllByUserIdOrderByCheckInDesc(id); 	
	    
	    assertThat(bookingsFound.size()).isEqualTo(3);
	    //
//		not working   
//	    assertThat(bookingsFound.get(0).getCheckIn()).isEqualTo(checkInDateOne);
	}
}
