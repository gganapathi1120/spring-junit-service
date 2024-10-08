package com.osi.junit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osi.junit.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUserName(String username);
	
}
