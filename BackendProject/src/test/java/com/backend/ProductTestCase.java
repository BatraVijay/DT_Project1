package com.backend;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.daos.ProductDao;
import com.backend.models.Product;




public class ProductTestCase {
	
	


	static ProductDao productDao;
	
	@BeforeClass
	public static void initialize()
	{

		  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	      context.register(DBConfig.class);
	      context.refresh();
	productDao=context.getBean("productDao",ProductDao.class);    
	     
	} 
	
	
	
	@Test
	@Ignore
	public void addProductTest(){
		  
		  
	    
	        Product cat=new Product();
	        cat.setProductName("Womens Wear");
	        cat.setProductDesc("Ethnic Wear");
	        assertTrue("Problem in Adding Product",productDao.addProduct(cat));
}
}