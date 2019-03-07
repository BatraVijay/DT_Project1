package com.middleware.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dao.MyNotificationDao;
import com.backend.model.MyError;
import com.backend.model.MyNotification;

@RestController
public class MyNotificationController {
	/*	MyNotification getNotificationById(int id);
	List<MyNotification> getAllNotifications(String userEmail);
	*/
	
	@Autowired
	MyNotificationDao myNotificationDao;
	
	@RequestMapping(value="/addNotification",method=RequestMethod.POST)
	public ResponseEntity<?> addJob(@RequestBody MyNotification obj,HttpSession session){
		
		try
		{
			myNotificationDao.addNotification(obj);
			return new ResponseEntity<MyNotification>(obj,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			MyError error=new MyError("unable to add job post.."+e.getMessage());
			return new ResponseEntity <MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/updateNotification",method=RequestMethod.POST)
	public ResponseEntity<?> updateNotification(@RequestBody MyNotification obj,HttpSession session){
		
		try
		{
			myNotificationDao.update(obj);
			return new ResponseEntity<MyNotification>(obj,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			MyError error=new MyError("unable to add job post.."+e.getMessage());
			return new ResponseEntity <MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/getNotification/{nId}",method=RequestMethod.GET)
	public ResponseEntity<?> getNotificationById(@PathVariable("nId")int nId){
		
		try
		{
			MyNotification obj=myNotificationDao.getNotificationById(nId);
			return new ResponseEntity<MyNotification>(obj,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			MyError error=new MyError("unable to add job post.."+e.getMessage());
			return new ResponseEntity <MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/getAllNotifications",method=RequestMethod.GET)
	public ResponseEntity<?> getAllNotifications(){
		String email=(String) session.getAttribute("loginId");
		
		try
		{
			List<MyNotification> obj=myNotificationDao.getAllNotifications(email);
			return new ResponseEntity<List<MyNotification>>(obj,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			MyError error=new MyError("unable to add job post.."+e.getMessage());
			return new ResponseEntity <MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
