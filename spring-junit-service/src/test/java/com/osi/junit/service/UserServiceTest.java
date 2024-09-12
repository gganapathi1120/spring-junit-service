package com.osi.junit.service;



import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.osi.junit.entity.User;
import com.osi.junit.repository.UserRepository;

@ExtendWith(MockitoExtension.class)

class UserServiceTest {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	

	@Test
    public void testCreateUser() {
        User user = new User(1L, "test", "test@example.com");
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.createUser(user);
        assertThat(user.getUserName()).isEqualTo(savedUser.getUserName());
    }
	
	@Test
	public void testUpdateUser() {
		//prepare mock data
		Long userId = 3L;
		User existingUser = new User(userId, "test", "test@gmail.com");
		when(userRepository.save(existingUser)).thenReturn(existingUser);
		
		User updatedUser = userService.updateUser(existingUser);
		
		//assertNotNull(updatedUser);
		//assertEquals("test",updatedUser.getUserName());
		//assertEquals("test@gmail.com",updatedUser.getUserEmail());
	
	}
	
	@Test
	public void testDeleteUser() {
        User user = new User(1L, "test", "test@example.com");
        
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        
        //checking how many times called
        verify(userRepository, times(1)).deleteById(1L);
		
	}
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	

}
