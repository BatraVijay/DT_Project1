package com.backend;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DBConfig;
import com.backend.daos.SupplierDao;

import com.backend.models.Supplier;

public class SupplierTestCase {

	 static SupplierDao supplierDao;
     
	    @BeforeClass
	    public static void initialize(){
	        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	        context.register(DBConfig.class);
	        context.refresh();
	         
	        supplierDao=context.getBean("supplierDaos",SupplierDao.class);
	    }
	     
	    @Test
	    @Ignore
	    public void addSupplier(){
	    	System.out.println("I m here ");
	        Supplier cat=new Supplier();
	        cat.setSupplierName("Vijay");
	        cat.setSupplierAddress("Sonipat");
	        assertTrue("Problem in Adding Supplier", supplierDao.addSupplier(cat));
	    }
}