package com.backend.daos;

import com.backend.models.User;

public interface UserDao {
	

	public boolean registerUser(User product);
	public User getUserById(String id);

}
