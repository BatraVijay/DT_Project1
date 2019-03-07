package com.middleware.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dao.FriendDao;
import com.backend.dao.MyNotificationDao;
import com.backend.dao.UserDao;
import com.backend.model.Friend;
import com.backend.model.MyError;
import com.backend.model.MyNotification;
import com.backend.model.User;

@RestController
public class FriendController {

	@Autowired
	private FriendDao friendDao;

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> suggestedUsers(HttpSession session){
		
		User  user=(User)session.getAttribute("userObj");
		if(user==null)
		{
			MyError error=new MyError("Unauthorised Access..");
			return new ResponseEntity<MyError>(error,HttpStatus.UNAUTHORIZED);  
		}
		List<User> suggestedUsers=friendDao.suggestedUsers(user.getEmail());
		return new ResponseEntity <List<User>>(suggestedUsers,HttpStatus.OK);
		
	}
	
	@Autowired
	MyNotificationDao myNotificationDao;
	
	@RequestMapping(value="/addfriend",method=RequestMethod.POST)
	public ResponseEntity<?> addFriend(@RequestBody User toId,HttpSession session){
		
		User  user=(User)session.getAttribute("userObj");
		if(user==null)
		{
			MyError error=new MyError("Unauthorised Access..");
			return new ResponseEntity<MyError>(error,HttpStatus.UNAUTHORIZED);  
		}
		User fromId=userDao.getUser(user.getEmail());
		Friend friend=new Friend();
		friend.setFromId(fromId);
		friend.setToId(toId);
		friend.setStatus('P');
		friendDao.addFriend(friend);
		
		MyNotification obj=new MyNotification();
		obj.setStatus("Not Viewed");
		obj.setUserEmail(user.getEmail());
		obj.setFriendId(friend.getId());
		
		myNotificationDao.addNotification(obj);
	
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> pendingRequests(HttpSession session){
		
		User  user=(User)session.getAttribute("userObj");
		if(user==null)
		{
			MyError error=new MyError("Unauthorised Access..");
			return new ResponseEntity<MyError>(error,HttpStatus.UNAUTHORIZED);  
		}
		List<Friend> pendingRequests=friendDao.pendingRequests(user.getEmail());
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	}		
	
	@RequestMapping(value="/acceptrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> acceptRequest(@RequestBody Friend request,HttpSession session){
		
		friendDao.acceptRequest(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/deleterequest",method=RequestMethod.PUT)
	public ResponseEntity<
	?> deleteRequest(@RequestBody Friend request,HttpSession session){
		
		User  user=(User)session.getAttribute("userObj");
		if(user==null)
		{
			MyError error=new MyError("Unauthorised Access..");
			return new ResponseEntity<MyError>(error,HttpStatus.UNAUTHORIZED);  
		}
		friendDao.deleteRequest(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/friend",method=RequestMethod.GET)
	public ResponseEntity<?> getAllFriends(HttpSession session){
		
		User userObj=(User) session.getAttribute("userObj");
		if(userObj==null)
		{
			MyError error=new MyError("Unauthorised Access..");
			return new ResponseEntity<MyError>(error,HttpStatus.UNAUTHORIZED);  
		}
		List<User> friends=friendDao.listOfFriends(userObj.getEmail());
		System.out.println("friends : "+friends);
		return new ResponseEntity<List<User>>(friends,HttpStatus.OK);
	}
}

