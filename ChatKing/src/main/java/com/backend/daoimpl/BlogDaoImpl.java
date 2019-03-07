package com.backend.daoimpl;

import java.util.List;

import	org.hibernate.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.dao.BlogDao;
import com.backend.model.Blog;


@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {

	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	public boolean addBlog(Blog blogobj) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.save(blogobj);
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean deleteBlog(Blog blog) {
		try
		{
			
			Session session=sessionfactory.getCurrentSession();
			session.delete(blog);
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}



	@Override
	public boolean updateBlog(Blog blog) {

		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.update(blog);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
		
		
	@Override
	public Blog getBlog(int blogId) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			Blog obj=session.get(Blog.class,blogId);
			return obj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public boolean approveBlog(Blog blog) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			blog.setStatus("Approved");
			session.update(blog);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean rejectBlog(Blog blog) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			blog.setStatus("Rejected");
			session.update(blog);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Blog> listBlogs(String email) {
		try
		{
			System.out.println("Email in "+email);
			Session session=sessionfactory.openSession();
			Query query=session.createQuery("from Blog where email=:x");
			query.setParameter("x", email);
			
			List<Blog> list=query.list();
			System.out.println("listblogs : "+list);
			return list;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<Blog> listAllApprovedBlogs() {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			Query query=session.createQuery("from Blog where status='Approved'");
		    List<Blog> listAllApprovedBlogs=query.list();
		     return listAllApprovedBlogs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Blog> listPendingBlogs() {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			 Query query=session.createQuery("from Blog where status='Pending'");
			 List<Blog> listPendingBlogs=query.list();
			 return listPendingBlogs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean incrementLikes(Blog blog) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			blog.setLikes(blog.getLikes()+1);
			session.update(blog);
			     return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
