package org.renting.rentanrv.service.impl;

import org.renting.rentanrv.model.User;
import org.renting.rentanrv.repository.UserRepository;
import org.renting.rentanrv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createNewUser(User newUser) {
		User user = userRepository.save(newUser);
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserDetails(long userId) {
		User user = userRepository.findById(userId).get();
		return user;
	}
}
