package com.backend.ChatKing;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DbConfig;

public class AppTest 
{

	@BeforeClass
	public static void init(){
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.register(DbConfig.class);
		context.refresh();
	}
	
	@Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
