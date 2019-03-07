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

import com.backend.dao.BlogCommentDao;
import com.backend.model.Blog;
import com.backend.model.BlogComment;
import com.backend.model.MyError;
import com.backend.model.User;

@RestController
public class BlogCommentController {

	@Autowired
	HttpSession session;
	
	@Autowired
	BlogCommentDao blogCommentDaoImpl;
	
	@PostMapping(value="/addComment")
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment){
	try {
			User user=(User)session.getAttribute("userObj");
			
			System.out.println(user);
			
			blogComment.setCommentedBy(user.getEmail());
			blogComment.setCommentedOn(new Date());
			
			
			blogCommentDaoImpl.addBlogComment(blogComment);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			MyError error = new MyError("Unauthorised User. U need to login first for making any comment");
			return new ResponseEntity<MyError>(error,HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);	
	}
	
	@GetMapping(value="getBlogComments/{blogId}")
	public ResponseEntity<?> getBlogComments(@PathVariable("blogId") int blogId)
	{
		List<BlogComment> list=blogCommentDaoImpl.getAllComments(blogId);
		if(list.size()!=0)
		{
			return new ResponseEntity<List<BlogComment>>(list,HttpStatus.OK);

	}
		else
		{
			MyError error = new MyError("No Comments Found");
			return new ResponseEntity<MyError>(error,HttpStatus.NOT_FOUND);
		}
	}
	
}
