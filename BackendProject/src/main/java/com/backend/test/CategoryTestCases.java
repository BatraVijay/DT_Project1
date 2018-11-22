package com.backend.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.daos.CategoryDaos;
import com.backend.models.Category;

public class CategoryTestCases {

	 static CategoryDaos categoryDaos;
     
	    @BeforeClass
	    public static void initialize(){
	        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	        context.register(DBConfig.class);
	        context.refresh();
	         
	        categoryDaos=context.getBean("categoryDaos",CategoryDaos.class);
	    }
	     
	    @Test
	        public void addCategoryTest(){
	        Category cat=new Category();
	        cat.setCategoryName("Womens Wear");
	        cat.setCategoryDesc("Ethnic Wear");
	        assertTrue("Problem in Adding Category",categoryDaos.addCategory(cat));
	    }
	     
	 	
}
