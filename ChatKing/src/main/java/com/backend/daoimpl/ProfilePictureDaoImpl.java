package com.backend.daoimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.dao.ProfilePictureDao;
import com.backend.model.ProfilePicture;

@Repository()
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {

	@Autowired
	SessionFactory sessionfactory;
	
	@Override
	public void uploadProfilePicture(ProfilePicture profilePicture) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.saveOrUpdate(profilePicture);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public ProfilePicture getImage(String email) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			ProfilePicture p=session.get(ProfilePicture.class,email);
			return p;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
