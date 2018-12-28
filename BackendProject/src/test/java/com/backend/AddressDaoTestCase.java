package com.backend;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.daos.AddressDao;
import com.backend.daos.UserDao;
import com.backend.models.Address;

public class AddressDaoTestCase {
	
	static AddressDao addressdao;
	static UserDao userDao;
	 @BeforeClass
	    public static void initialize(){
	        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	        context.register(DBConfig.class);
	        context.refresh();
	         
	        addressdao =context.getBean("addressDao",AddressDao.class);
	        userDao =context.getBean("userDao",UserDao.class);
	 }
	        @Test
		    @Ignore
		    public void addCategoryTest(){
		        Address cat=new Address();
		       cat.setCity("sonipat");
		       cat.setHousenumber(35);
		       cat.setId(101);
		       cat.setLocality("hjsd");
		       cat.setPinCode("131001");
		       cat.setState("Haryana");
		       cat.setUser(userDao.getUserById("kshdf"));
		       assertTrue("Problem in Adding Address", addressdao.insertAddress(cat));
		    }
}
