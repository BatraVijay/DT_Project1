package com.backend;


import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.daos.UserDao;
import com.backend.models.UserInfo;


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
	        UserInfo cat=new UserInfo();
	        cat.setUserName("vj");
	        cat.setPassword("vijay");
	        cat.setCustomerName("vijay");
	        cat.setEnabled(true);
	        cat.setAddress("Sonipat");
	        cat.setMobileNo("100");
	        
	        assertTrue("Problem in Adding User", userDao.addUser(cat));
	    }
}
