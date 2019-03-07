package com.backend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.dao.UserDao;
import com.backend.model.User;

@Repository()
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	public boolean registerUser(User user) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.save(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User checkLogin(User user) {
	try
	{
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from User where email=:x and password=:y");
		query.setString("x",user.getEmail());
		query.setString("y",user.getPassword());
		List<User> userDetails=query.list();
		System.out.println(userDetails);
		
		if(userDetails.size()!=0) {
			return userDetails.get(0);
		}
	}
	catch(Exception e)
	{
		
	}
		return null;
	}

	@Override
	public boolean updateOnlineStatus(String status, String email) {
	try
	{
		Session session=sessionfactory .getCurrentSession();
		User user=session.get(User.class, email);
		user.setOnlineStatus("online");
		return true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public User getUser(String email) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			User user=(User)session.get(User.class, email);
			return user;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getUser() {
		try{
		Session session=sessionfactory.openSession();
		Query query=session.createQuery("from User");
		List<User> getUserDetails=query.list();
		session.close();
		return getUserDetails;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			}
	
	return null;
	}
	
	@Override
	public boolean deleteUser(User user) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.delete(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.update(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
