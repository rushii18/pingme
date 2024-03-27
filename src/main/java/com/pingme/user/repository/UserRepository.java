package com.pingme.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.user.model.User;
import org.springframework.stereotype.Repository;
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByfirstName(String firstName);

	public User findBylastName(String lastName);

	public User findBycontact(String contact);

//	public  User findByEmail(String email);
	
	public Optional<User>findByEmail( String userNme);

}
