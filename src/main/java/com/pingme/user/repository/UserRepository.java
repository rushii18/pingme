package com.pingme.user.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pingme.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query("select u from User u where u.firstName LIKE %:firstName% OR u.contact LIKE %:contact%")
//	public List<User> searchByfirtsName(@Param("firstName") String firstName, @Param("contact") String contact);

//	@Query("select u from User u where u.FirstName LIKE %:query% OR u.LastName LIKE %:query% OR u.email LIKE %:query%")
//	public List<User> searchByUser(@Param("query") String query);

	//@Query("select u from User u where u.email LIKE %:email%")
	// public User findByEmail(@Param("email") String email);

	public User findByfirstName(String firstName);


	public User findBylastName(String lastName);

	
	public Optional<User> findByEmail(String email);

}
