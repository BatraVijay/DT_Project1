package com.middleware.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.backend.dao.ProfilePictureDao;
import com.backend.model.MyError;
import com.backend.model.ProfilePicture;
import com.backend.model.User;


@RestController
public class ProfilePictureController {


	@Autowired
	private ProfilePictureDao profilePictureDao;
	
	@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
		User userObj=(User)session.getAttribute("userObj");

		if(userObj==null)
		{
			MyError error=new MyError("Unauthorised Access..");
			return new ResponseEntity<MyError>(error,HttpStatus.UNAUTHORIZED);  
		}	
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setEmail(userObj.getEmail());
		profilePicture.setImage(image.getBytes());
		profilePictureDao.uploadProfilePicture(profilePicture);
		return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);  
	}
	
	@RequestMapping(value="/getimage/{email:.+}",method=RequestMethod.GET)
	public @ResponseBody byte[] getImage(@PathVariable String email,HttpSession session){
	
			
		ProfilePicture profilePicture=profilePictureDao.getImage(email);
		System.out.println(profilePicture);
		if(profilePicture==null){
			return null;
		}
		else
			return profilePicture.getImage();
		
	}
	
}
