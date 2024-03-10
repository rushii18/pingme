package com.pingme.profile.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.profile.model.Profile;
import com.pingme.profile.repo.ProfileRepository;
import com.pingme.profile.service.ProfileService;
import com.pingme.user.model.User;

import com.pingme.user.service.imp.UserServiceImplemetation;

@Service
public class ProfileServiceImplemetation implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserServiceImplemetation userServiceImplemetation;

	@Override
	public Profile createProfile(Profile profile, Integer userid) throws Exception {

		User user = userServiceImplemetation.findUserByid(userid);

		Profile profilecreate = new Profile();

		profilecreate.setProfilePic(profile.getProfilePic());
		profilecreate.setDescription(profile.getDescription());
		profilecreate.setUser(user);

		Profile saveprofile = profileRepository.save(profilecreate);

		return saveprofile;
	}

	@Override
	public Profile updateProfile(Profile profile, Integer postid) throws Exception {

		Optional<Profile> updateprofile = profileRepository.findById(postid);

		if (updateprofile.isPresent()) {

			Profile profileupdate = updateprofile.get();

			if (profile.getProfilePic() != null)
				profileupdate.setProfilePic(profile.getProfilePic());
			if (profile.getDescription() != null)
				profileupdate.setDescription(profile.getDescription());

			Profile saveprofileupdate = profileRepository.save(profileupdate);

			return saveprofileupdate;
		}
		return profile;
	}

}
