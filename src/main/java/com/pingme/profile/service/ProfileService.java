package com.pingme.profile.service;

import com.pingme.profile.model.Profile;

public interface ProfileService  {

	public Profile createProfile(Profile profile , Integer userid) throws Exception;
	
	public Profile updateProfile(Profile profile , Integer userid) throws Exception;
	
	
	
	
}
