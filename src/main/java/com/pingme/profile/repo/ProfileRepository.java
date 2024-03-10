package com.pingme.profile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.profile.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
