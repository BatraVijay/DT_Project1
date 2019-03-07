package com.backend.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.config.DbConfig;
import com.backend.dao.BlogDao;
import com.backend.model.Blog;

public class BlogTestcase {

	static BlogDao blogDao;
	
	@BeforeClass
	public static void initialize(){
		
		  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		  context.register(DbConfig.class);
		  context.refresh();
		  blogDao=context.getBean("blogDao",BlogDao.class);
	}
	@Test
	@Ignore
	public void addBlog()
	{
		Blog blog=new Blog();
		blog.setBlogName("java");
		blog.setBlogContext("open source platform independent");
		
		Date d=new Date();
		blog.setCreateDate(d);
		
		blog.setEmail("vijaybatra@gmail.com");
		blog.setLikes(0);
		blog.setStatus("Pending");
		
		assertTrue("Blog Not Added...",blogDao.addBlog(blog));
		
		
	}
	
	@Test
	@Ignore 
	public void deleteBlog()
	{
	Blog b=blogDao.getBlog(1);
	assertTrue("Problem in deleting ",  blogDao.deleteBlog(b));
    	
	}
	@Test
	@Ignore
	public void updateTestcase()
	{
	    Blog b=blogDao.getBlog(3);
	    b.setBlogContext("Java is compatabe and easy to learn and understand");  
	    assertTrue("Problems in updating Blog",blogDao.updateBlog(b));
	}
	
	@Test
	@Ignore
	public void getBlogTest()
	{
		Blog b=blogDao.getBlog(3);
		System.out.println(b.getBlogName()+" "+b.getEmail()+" "+b.getLikes()+" "+b.getStatus()+" "+b.getCreateDate()+" "+b.getBlogContext());
		assertNotNull("Blog not Found",b);
		
	}
	
	
	@Test
	@Ignore
	public void approveTestcase()
	{
	    Blog b=blogDao.getBlog(3);
	    assertTrue("Problems in updating Blog",blogDao.approveBlog(b));
	}
	
	@Test
	@Ignore
	public void rejectTestcase()
	{
	    Blog b=blogDao.getBlog(3);
	    assertTrue("Problems in updating Blog",blogDao.rejectBlog(b));
	}
	
	
	
	@Test
	@Ignore
	public
	void fetchPendingBlogs() {
		List<Blog> blogs=blogDao.listPendingBlogs();
		for(Blog b:blogs) {
			System.out.println(b);
		}
		assertTrue("No Pending Blogs Found",blogs.size()!=0);
	}	
	
	@Test
	@Ignore
	public void fetchApprovedBlogs()
	{
		List<Blog> blog=blogDao.listAllApprovedBlogs();
		for(Blog b:blog)
		{
			System.out.println(b);
		}
		assertTrue("No Approved Blogs found",blog.isEmpty());
		}
	
	
	@Test
	@Ignore
	public void listAllBlogs()
	{
		/*List<Blog> blog=blogDao.listBlogs("divya@gmail.com");
		for(Blog b:blog)
		{
			System.out.println(b);
		}*/
	}
	
	@Test
	public void increamentLikes()
	{
	  Blog b=blogDao.getBlog(3);
	  assertTrue("Problem in increament",blogDao.incrementLikes(b));
		
	}
}

	
	
	