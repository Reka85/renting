package org.renting.rentanrv;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.renting.rentanrv.model.User;
import org.renting.rentanrv.model.Vehicle;
import org.renting.rentanrv.repository.UserRepository;
import org.renting.rentanrv.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
    private TestEntityManager entityManager;

	private User user;
	private Vehicle vehicleOne;
	private Vehicle vehicleTwo;
	private Vehicle vehicleThree;
	
	@Before
	public void setUp() {
		user =  new User("Joe", "Smith", "joe@email.com", 25, "123-1234567");
		user = userRepository.save(user);
		
		//new vehicles
		BigDecimal price1 = new BigDecimal("34");
		vehicleOne = new Vehicle("Joe's vehicle", 5, "New York", 5, price1, 1, user);
		vehicleTwo = new Vehicle("New vehicle", 4, "Long island", 4, price1, 2, user);
		vehicleThree = new Vehicle("Awesome vehicle", 5, "Barcelona", 3, price1, 1, user);
	}
	
	@Test
	public void saveVehicleAndFindById() {
		Vehicle savedVehicle = vehicleRepository.save(vehicleOne);
		assertThat(vehicleRepository.findById(savedVehicle.getId()).get()).isEqualTo((vehicleOne));
	}
	
	// List<Vehicle> findAllByUserIdOrderByNameDesc(Long userId);
	@Test
	public void whenFindByUserId_thenReturnVehicleListInOrder() {
		entityManager.persist(vehicleOne);
		entityManager.persist(vehicleTwo);
		entityManager.persist(vehicleThree);
		
		Long id = user.getId();
	    List<Vehicle> vehiclesFound = vehicleRepository.findAllByUserIdOrderByNameAsc(id);
	 
	    assertThat(vehiclesFound.size()).isEqualTo(3);
	    // testing alphabetical order
	    assertThat(vehiclesFound.get(0).getName()).isEqualTo("Awesome vehicle");
	    assertThat(vehiclesFound.get(1).getName()).isEqualTo("Joe's vehicle");
	    assertThat(vehiclesFound.get(2).getName()).isEqualTo("New vehicle");
	}
	
	//Page<Vehicle> findAllByOrderByLocalisation(Pageable pageable);
	@Test
	public void whenFindAll_thenReturnVehiclePageInOrderOfLocalisation() {
		entityManager.persist(vehicleOne);
		entityManager.persist(vehicleTwo);
		entityManager.persist(vehicleThree);
		
		
		Pageable page = PageRequest.of(0, 5);
	    Page<Vehicle> found = vehicleRepository.findAllByOrderByLocalisation(page);
	 
	    assertThat(found.getTotalElements()).isEqualTo(3);
	    List<Vehicle> foundList = found.getContent();
	    assertThat(foundList.get(0).getLocalisation()).isEqualTo("Barcelona");
	    assertThat(foundList.get(1).getLocalisation()).isEqualTo("Long island");
	    assertThat(foundList.get(2).getLocalisation()).isEqualTo("New York");
	}
	
	// not working
	//Page<Vehicle> findByNameIgnoreCaseLikeOrLocalisationIgnoreCaseLikeOrderByName(
	//               String name, Pageable page);
//	@Test
//	public void whenFindVehiclesByNameOrLocalisation_thenReturnVehiclesInOrderOfLocalisation() {
//		entityManager.persist(vehicleOne);
//		entityManager.persist(vehicleTwo);
//		entityManager.persist(vehicleThree);
//		
//		
//		Pageable page = PageRequest.of(0, 5);
//	    Page<Vehicle> found = vehicleRepository.findByNameIgnoreCaseLikeOrLocalisationIgnoreCaseLikeOrderByName(
//	    		"New", page);
//	 
////	    assertThat(found.getTotalElements()).isEqualTo(2);
////	    List<Vehicle> foundList = found.getContent();
////	    assertThat(foundList.get(0).getLocalisation()).isEqualTo("New York");
////	    assertThat(foundList.get(1).getName()).isEqualTo("New vehicle");
//	}
}
