package com.osi.junit.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osi.junit.entity.User;
import com.osi.junit.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		System.out.println("setUp");
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.build();
	}

	@AfterEach
	public void tearDown() {
		System.out.println("tearDown");
		userService = null;
		userController = null;
		mockMvc = null;
	}

	@Test
	public void testCreateUser() throws Exception {

		System.out.println("testCreateUser");

		User userToCreate = new User("testUser", "test@gmail.com");

		when(userService.createUser(userToCreate)).thenReturn(userToCreate);

		mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(userToCreate)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.userName", is("testUser")))
				.andExpect(jsonPath("$.userEmail", is("test@gmail.com")));

	}
	
	@Test
	public void testGetUserById() throws Exception {
		System.out.println("testGetUserById");

		Long userId = 1L;
		User user = new User();
		user.setUserId(userId);
		user.setUserName("testUser");
		user.setUserEmail("test@example.com");

		when(userService.getUserById(userId)).thenReturn(user);

		mockMvc.perform(get("/api/users/{userId}", userId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userId", is(1)))
				.andExpect(jsonPath("$.userName", is("testUser")))
				.andExpect(jsonPath("$.userEmail", is("test@example.com")));
	}
	
	 @Test
	 public void testUpdateUser() throws Exception {
		 
			System.out.println("testUpdateUser");

	        Long userId = 1L;
	        User userToUpdate = new User();
	        userToUpdate.setUserId(userId);
	        userToUpdate.setUserName("updatedUser");
	        userToUpdate.setUserEmail("updated@example.com");

	        when(userService.getUserById(userId)).thenReturn(userToUpdate);
	        when(userService.updateUser(userToUpdate)).thenReturn(userToUpdate);

	        mockMvc.perform(put("/api/users/{userId}", userId)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(userToUpdate)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.userId", is(1)))
	                .andExpect(jsonPath("$.userName", is("updatedUser")))
	                .andExpect(jsonPath("$.userEmail", is("updated@example.com")));
	    }
	 
	 
	@Test
	public void testDeleteUser() throws Exception {
		System.out.println("testDeleteUser");

		Long userId = 1L;

		mockMvc.perform(delete("/api/users/{userId}", userId)).andExpect(status().isNoContent());

		verify(userService, times(1)).deleteUser(userId);

	}


	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
