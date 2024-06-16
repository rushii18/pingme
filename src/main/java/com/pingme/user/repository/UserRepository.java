package com.pingme.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pingme.user.model.User;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByfirstName(String firstName);

	public User findBylastName(String lastName);

	public User findBycontact(String contact);

	public Optional<User> findByEmail(String userNme);

	@Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.contact LIKE %:query%")
	public List<User> searchByUser(@Param("query") String query);

}
