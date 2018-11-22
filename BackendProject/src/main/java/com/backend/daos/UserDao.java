package com.backend.daos;

import java.util.List;

import com.backend.models.UserInfo;

public interface UserDao {

	public boolean addUser(UserInfo user);
	public boolean deleteUser(UserInfo user);
	public boolean updateUser(UserInfo user);
	 public UserInfo getUserById(int UId);
	    public List<UserInfo> getAllUser();
	    public List<UserInfo> getUsersByCategory(int userId);
	
	
	
	
	
	
}
