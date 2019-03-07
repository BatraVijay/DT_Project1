package com.backend.daosimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.daos.MyNotificationDao;
import com.backend.models.MyNotification;

@Repository("myNotificationDao")
@Transactional

public class MyNotificationDaoImpl implements MyNotificationDao {
	@Autowired
	SessionFactory sessionFactory;


	@Override
	public MyNotification addNotification(MyNotification obj) {
		try {
		Session session=sessionFactory.getCurrentSession();
		session.save(obj);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return obj;

	}

	@Override
	public MyNotification getNotificationById(int id) {
		try {
			Session session=sessionFactory.getCurrentSession();
			MyNotification obj=(MyNotification) session.get(MyNotification.class,id);
			return obj;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;

	}

	@Override
	public List<MyNotification> getAllNotifications(String userEmail) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from MyNotification where userEmail=:a");
			query.setParameter("a",userEmail);
			return query.list();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public MyNotification update(MyNotification obj) {
		try {
			Session session=sessionFactory.getCurrentSession();
			session.update(obj);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return obj;
	}

}
