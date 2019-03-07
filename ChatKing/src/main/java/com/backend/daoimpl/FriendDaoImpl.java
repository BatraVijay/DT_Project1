package com.backend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.dao.FriendDao;
import com.backend.model.Friend;
import com.backend.model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> suggestedUsers(String email) {
		Session session=sessionFactory.getCurrentSession();
		String queryString="select * from usertab where email in "
				+ "(select email from usertab where email!=? "
				+ "minus "
				+ "(select toId_email from friend where fromId_email=? "
				+ "union "
				+ "select fromId_email from friend where toId_email=? )) ";
		SQLQuery query=session.createSQLQuery(queryString);
		query.setString(1, email);
		query.setString(2, email);
		query.setString(3, email);
		query.addEntity(User.class);
		List<User> suggestedUsers=query.list();
		return suggestedUsers;
		
	}

	@Override
	public void addFriend(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);
		
	}

	@Override
	public List<Friend> pendingRequests(String toIdEmail) {
	
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where status=:x and toId.email=:y");
		query.setParameter("x", 'P');
		query.setParameter("y", toIdEmail);
		
		List<Friend> pendingRequests=query.list();
		return pendingRequests;
	}

	@Override
	public void acceptRequest(Friend request) {
		Session session=sessionFactory.getCurrentSession();
		request.setStatus('A');
		session.update(request);
		
	}

	@Override
	public void deleteRequest(Friend request) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(request);
	}

	@Override
	public List<User> listOfFriends(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query1=session.createQuery
				("select f.toId from Friend f where f.fromId.email=:x and f.status=:y");
				
					query1.setParameter("x", email);
					query1.setParameter("y", 'A');
					List<User> friendList1=query1.list();
					
					Query query2=session.createQuery("select f.fromId "
							+ "from Friend f where f.toId.email=:x and f.status=:y");
					query2.setParameter("x", email);
					query2.setParameter("y", 'A');
					List<User> friendList2=query2.list();
					
					friendList1.addAll(friendList2);
					return friendList1;
		
		
	}

}
