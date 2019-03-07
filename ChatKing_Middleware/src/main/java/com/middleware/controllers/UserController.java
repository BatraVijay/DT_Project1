package com.middleware.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dao.UserDao;
import com.backend.model.MyError;
import com.backend.model.User;


@RestController
public class UserController {

	@Autowired
	UserDao userDao;
	
	@GetMapping(value="/demo")
	public ResponseEntity<String> demo(){
		return new ResponseEntity<String>("Hello I m middleware",HttpStatus.OK);
	}
	
	@PostMapping(value="registerUser")
	public ResponseEntity<?> registerUser(@RequestBody User userObj){
		
		userObj.setOnlineStatus("Offline");
		userObj.setRole("Role_User");
		
		
		
		if(userDao.registerUser(userObj)){
			return new ResponseEntity<User>(userObj,HttpStatus.OK);
		}
		else {
			MyError error=new MyError("Problem in Registering . Try Again");
			return new ResponseEntity<MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Autowired
	HttpSession  session;
	
	@PostMapping(value="validateUser")
	public ResponseEntity<?> validateUser(@RequestBody User userObj){
		
		User uObj=userDao.checkLogin(userObj);
		if(uObj!=null){
			uObj.setOnlineStatus("Online");
			session.setAttribute("userObj",uObj);
			return new ResponseEntity<User>(uObj,HttpStatus.OK);
		}
		else {
			//Create the object of MyError class
			return new ResponseEntity<String>("User doesnt exist",HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping(value="updateOnlineStatus/{status}/{email:.+}")
	public ResponseEntity<?> updateOnlineStatus(@PathVariable("status")String status,@PathVariable("email")String email){
		
		System.out.println("Email = "+email);
		if(userDao.updateOnlineStatus(status, email)){
			return new ResponseEntity<String>("Status Updated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Problem in Updating Status",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="getUser/{email:.+}")
	public ResponseEntity<?> getUser(@PathVariable("email")String email){
		
		User userObj=userDao.getUser(email);
		if(userObj!=null){
			return new ResponseEntity<User>(userObj,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("User doesnt exist",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(value="deleteUser")
	public ResponseEntity<?> deleteUser(@RequestBody User userObj){
		if(userDao.deleteUser(userObj)){
			return new ResponseEntity<String>("User deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in deleting User",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User userObj){
		
		
		if(userDao.updateUser(userObj)){
			return new ResponseEntity<User>(userObj,HttpStatus.OK);
		}
		else {
			MyError er=new MyError("Error in updating User");
			return new ResponseEntity<MyError>(er,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
