package com.middleware.controllers;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dao.BlogDao;
import com.backend.model.Blog;
import com.backend.model.MyError;
import com.backend.model.User;

@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;

	@Autowired
	HttpSession session;
	
	
	@PostMapping(value="/addblog")
	public ResponseEntity<?> addBlog(@RequestBody Blog blogObj)
	{
		
		try {
			
			
			User user=(User)session.getAttribute("userObj");
			System.out.println(user);
			Date d=new Date();
			blogObj.setCreateDate(d);
			blogObj.setLikes(0);
			blogObj.setStatus("Pending");
			blogObj.setEmail(user.getEmail());
			
			
			blogDao.addBlog(blogObj);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			MyError error = new MyError("Problem in adding Blog");
			return new ResponseEntity<MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Blog>(blogObj,HttpStatus.OK);
}
	
	@PostMapping(value="/deleteBlog")
	public ResponseEntity<?> deleteBlog(@RequestBody Blog blog)
	{
		try
		{
			blogDao.deleteBlog(blog);
			}
		catch(Exception e)
		{
			MyError error=new MyError("Error in deleting Blog");
			return new ResponseEntity<MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
					}
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		
		
	}
	
	
	@PostMapping(value="/updateBlog")
	public ResponseEntity<?> updateBlog(@RequestBody Blog blog)
	{
		try
		{
			Date d= new Date();
			blog.setCreateDate(d);
			blogDao.updateBlog(blog);
			
			
		}
		catch(Exception e)
		{
			MyError error= new MyError("Problem in updating Blog");
			return new ResponseEntity<MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);

		
	}
	
	
	@GetMapping(value="getBlog/{blogId}")

	public ResponseEntity<?> getBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=blogDao.getBlog(blogId);
		if(blog!=null)
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);

	}
		else
		{
			MyError error = new MyError("No Blog Found");
			return new ResponseEntity<MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/listBlog/{email:.+}")
	public ResponseEntity<?> listBlogs(@PathVariable("email") String email)
	{
			System.out.println("email= "+email);
			
			
		    List<Blog> listBlogByemail=blogDao.listBlogs(email);
		    System.out.println("In Controller"+listBlogByemail);
		    if(listBlogByemail.size()!=0)
		    {
		    	System.out.println("In If");
				return new ResponseEntity<List<Blog>>(listBlogByemail,HttpStatus.OK);

		    }
		    else
		    {
		    	System.out.println("In Else");
		    	MyError error = new MyError("No Blog Found");
				return new ResponseEntity<MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

	@GetMapping(value="/listAllApprovedBlog")

	public ResponseEntity<?> listBlogs()
	{
		    List<Blog> listAllApprovedBlog=blogDao.listAllApprovedBlogs();
		    if(listAllApprovedBlog.size()!=0)
		    {
				return new ResponseEntity<List<Blog>>(listAllApprovedBlog,HttpStatus.OK);

		    }
		    else
		    {
		    	MyError error = new MyError("No Blog Found");
				return new ResponseEntity<MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	@GetMapping(value="/listPendingBlogs")

	public ResponseEntity<?> listPendingBlogs()
	{
		    List<Blog> listPendingBlogs=blogDao.listPendingBlogs();
		    if(listPendingBlogs.size()!=0)
		    {
				return new ResponseEntity<List<Blog>>(listPendingBlogs,HttpStatus.OK);

		    }
		    else
		    {
		    	MyError error = new MyError("No Blog Found");
				return new ResponseEntity<MyError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

	@PostMapping(value="/approveBlog")
	public ResponseEntity<?> approveBlog(@RequestBody Blog blog)
	{
		try
		{
		blogDao.approveBlog(blog);
	}
		catch(Exception e) 
		{
			MyError error = new MyError("Problem in approving Blog");
			return new ResponseEntity<MyError>(error,HttpStatus. INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);

	
		
	}
	
	
	@PostMapping(value="/rejectBlog")
	public ResponseEntity<?> rejectBlog(@RequestBody Blog blog)
	{
		try
		{
			
			blogDao.rejectBlog(blog);
			
		}
		catch(Exception e) 
		{
			MyError error=new MyError("Problem in rejecting Blog");
			return new ResponseEntity<MyError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@PostMapping(value="/incrementLikes")
	public ResponseEntity<?> incrementLikesOnBlog(@RequestBody Blog blog)
	{
		try
		{
			
			blogDao.incrementLikes(blog);
			
		}
		catch(Exception e) 
		{
			MyError error=new MyError("Problem in Incrementing likes");
			return new ResponseEntity<MyError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
}