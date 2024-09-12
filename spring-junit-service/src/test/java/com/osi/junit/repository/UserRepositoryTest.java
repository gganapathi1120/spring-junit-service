package com.osi.junit.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.osi.junit.entity.User;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	User user;
	
	@BeforeEach
	void setUp() {
		user = new User((long) 500,"ramesh", "ramesh@gmail.com");
		userRepository.save(user);
		
	}
	
	@AfterEach
	void tearDown() {
		user = null;
		userRepository.deleteAll();
	}
	
	//Test case for success
	
//	@Test
//	void testFindByUserName_Found() {
//	  List<User> userList =	userRepository.findByUserName("ramesh");
//	  
//	  assertThat(userList.get(0).getUserId())
//	  .isEqualTo(user.getUserId());
//	  
//	  assertThat(userList.get(0).getUserName())
//	  .isEqualTo(user.getUserName());
//	  
//			 
//	}
//	
	//test case for failure
	// Test case FAILURE
    @Test
    void testFindByUserName_NotFound()
    {
  	  List<User> userList =	userRepository.findByUserName("ramesh");
      assertThat(userList.isEmpty()).isTrue();
	
	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
