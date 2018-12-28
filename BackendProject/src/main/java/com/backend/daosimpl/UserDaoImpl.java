package com.backend.daosimpl;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.daos.UserDao;
import com.backend.models.User;


@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {


	@Autowired
	SessionFactory sessionFactory;
	@Override
	public boolean registerUser(User user) {
		
		 try {
		        Session session=sessionFactory.getCurrentSession();
		        session.save(user);
		        return true;
		        
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
		 return false;
	}

	@Override
	public User getUserById(String id) {
		 try {
		        Session session=sessionFactory.getCurrentSession();
		        User user=session.get(User.class, id);
		        return user;
		        }
		        catch(Exception e){
		            e.printStackTrace();
		        }
		        return null;
		    }
	}

