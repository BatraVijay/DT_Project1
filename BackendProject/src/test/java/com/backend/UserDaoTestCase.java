package com.backend;


import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.daos.UserDao;
import com.backend.models.User;


public class UserDaoTestCase {

	
	 static UserDao userDao;
     
	    @BeforeClass
	    public static void initialize(){
	        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	        context.scan("com.backend");
	        context.refresh();
	         
	        userDao=context.getBean("userDao",UserDao.class);
	    }
	     
	    @Test
	   @Ignore
	    public void addUser(){
	        User cat=new User();
	       cat.setEmail("kshdf");
	       cat.setEnabled(true);
	       cat.setName("vijay");
	       cat.setPassword("vijay");
	       cat.setPassword2("vijay");
	       cat.setPhone("9812953284");
	       cat.setRole("Employee");
	        assertTrue("Problem in Adding User", userDao.registerUser(cat));
	    }
}
