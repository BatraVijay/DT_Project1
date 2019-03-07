package com.backend.dao;

import java.util.List;

import com.backend.model.Friend;
import com.backend.model.User;

public interface FriendDao {

	List<User> suggestedUsers(String email);

	void addFriend(Friend friend);
	
	List<Friend> pendingRequests(String toIdEmail);

	void acceptRequest(Friend request);

	void deleteRequest(Friend request);
	
	List<User> listOfFriends(String email);


}
