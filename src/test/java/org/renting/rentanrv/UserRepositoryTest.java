package org.renting.rentanrv;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.renting.rentanrv.model.User;
import org.renting.rentanrv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;

	private User user;
	
	@Before
	public void setUp() {
		user =  new User("Joe", "Smith", "joe@email.com", 25, "123-1234567");
	}
	
	@Test
	public void saveUserAndFindById() {
		User savedUser = userRepository.save(user);
		assertThat(userRepository.findById(savedUser.getId()).get()).isEqualTo((user));
	}
}
