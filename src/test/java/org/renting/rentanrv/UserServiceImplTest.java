package org.renting.rentanrv;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.renting.rentanrv.model.User;
import org.renting.rentanrv.repository.UserRepository;
import org.renting.rentanrv.service.UserService;
import org.renting.rentanrv.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

//not working
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
 
    @Autowired
    private UserService userService;
 
    @MockBean
    private UserRepository userRepository;
    
    private User user;
    
    @Before
    public void setUp() {
        User user = new User("Joe", "Smith", "joe@email.com", 25, "123-1234567");
//        userRepository.save(user);
    	Mockito.when(userRepository.findById(user.getId())).equals(user);
    }
    
    @Test
    public void whenValidId_thenUserShouldBeFound() {
    	
       
        User found = userService.getUserDetails(user.getId());
      
        assertThat(found.getFirstName()).isEqualTo("Joe");
     }
}
