package com.backend;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.daos.CategoryDaos;
import com.backend.models.Category;
public class CategoryTestCase {

	 static CategoryDaos categoryDao;
     
	    @BeforeClass
	    public static void initialize(){
	        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	        context.register(DBConfig.class);
	        context.refresh();
	         
	        categoryDao =context.getBean("categoryDaos",CategoryDaos.class);
	    }
	     
	    @Test
	    @Ignore
	    public void addCategoryTest(){
	        Category cat=new Category();
	        cat.setCategoryName("Womens Wear");
	        cat.setCategoryDesc("Ethnic Wear");
	        assertTrue("Problem in Adding Category", categoryDao.addCategory(cat));
	    }
}
