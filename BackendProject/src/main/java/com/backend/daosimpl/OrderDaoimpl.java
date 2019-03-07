
package com.backend.daosimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.daos.OrderDao;
import com.backend.models.Order;



@Repository("orderDao")
@Transactional
public class OrderDaoimpl implements OrderDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int makeOrder(Order order) {

		Session session=sessionFactory.getCurrentSession();
		return (int)session.save(order);
	}

	
}
