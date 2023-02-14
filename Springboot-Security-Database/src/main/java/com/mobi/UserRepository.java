package com.mobi;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobi.models.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	Optional<User> findByUserName(String userName);

}
