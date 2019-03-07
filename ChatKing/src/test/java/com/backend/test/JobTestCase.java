package com.backend.test;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DbConfig;
import com.backend.dao.JobDao;
import com.backend.model.Job;

public class JobTestCase {

	static JobDao jobdao;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.register(DbConfig.class);
		context.refresh();
	
		jobdao=context.getBean("jobDao",JobDao.class);
	}
	@Test
	public void AddJOb()
	{
		Date d=new Date();
		Job job=new Job();
		job.setCompanyName("Google");
		job.setJobTitle("Software Devolper");
		job.setLocation("Haryana");
		job.setPostedOn(d);
		job.setSalary("90000 PM");
		job.setSkillsRequired("Full Stack programmer");
		job.setYrsofExp("2 yrs");
		job.setJobDescription("Programmer");
		
		assertTrue("Problem in adding Job", jobdao.addJob(job));		
	}
	@Test
	@Ignore
	public void ListOfJobs()
	{
		List<Job> job=jobdao.getAllJobs();
		for(Job j:job)
		{
	System.out.println(j.getCompanyName()+ " "+j.getJobTitle()+" "+j.getJobDescription()+" "+j.getLocation()+" "+j.getSalary()+" "+j.getPostedOn()+" "+j.getSkillsRequired()+" "+j.getYrsofExp());
	 
	
		}
	}
	
	@Test
	@Ignore
	public void getJobById()
	{
		Job j=jobdao.getJob(23);
		System.out.println(j.getCompanyName()+ " "+j.getJobTitle()+" "+j.getJobDescription()+" "+j.getLocation()+" "+j.getSalary()+" "+j.getPostedOn()+" "+j.getSkillsRequired()+" "+j.getYrsofExp());
		
		assertNotNull("User Not found",j);
	}

	
}
