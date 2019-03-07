package com.backend.dao;

import com.backend.model.ProfilePicture;

public interface ProfilePictureDao {


	void uploadProfilePicture(ProfilePicture profilePicture);

	ProfilePicture getImage(String email);
}
