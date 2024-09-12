package com.osi.junit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.junit.entity.User;
import com.osi.junit.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {		
		return userRepository.save(user);
	}
	
	public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}
	
	

}
