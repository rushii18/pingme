package com.pingme.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.profile.model.Profile;
import com.pingme.profile.service.ProfileService;

@RestController
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	@PostMapping("/profile/{id}")
	public Profile createProfile(@RequestBody Profile profile, @PathVariable("id")Integer id) throws Exception {

		Profile createprofile = profileService.createProfile(profile, id);

		return createprofile;
	}
	
	@GetMapping("/profileupdate/{id}")
	public Profile profileUpdate(@RequestBody Profile profile, @PathVariable("id")Integer id) throws Exception {

		Profile profileupdaet = profileService.updateProfile(profile, id);

		return profileupdaet;
	}
}
