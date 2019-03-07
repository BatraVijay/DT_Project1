package com.backend.daosimpl;


import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.daos.AddressDao;
import com.backend.models.Address;
import com.backend.models.Category;

@Repository("addressDao")
@Transactional
public class AddressDaoImpl implements AddressDao {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	
	public boolean insertAddress(Address address) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.save(address);
		return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return false;

		
	}

	@Override
	public Address getAddressById(int addressId) {
		 try {
		        Session session=sessionFactory.getCurrentSession();
		        Address obj=session.get(Address.class, addressId);
		        return obj;
		 		}
		        catch(Exception e){e.printStackTrace();}
		        return null;
		 }
		

	@Override
	public void updateAddress(Address address) {
		
		try {
            Session session=sessionFactory.getCurrentSession();
            session.update(address);
            
            }
            catch(Exception e){e.printStackTrace();
            }
           
     }


	@Override
	public void deleteAddress(int addressId) {
		try {
            Session session=sessionFactory.getCurrentSession();
            session.delete(addressId);
            
            }
            catch(Exception e){e.printStackTrace();
           
            }
           
	}

	@Override
	public List<Address> getAddressForUser(String customerId) {
		try
		{
		Session session= sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Address where user.email=:a");
		query.setString("a", customerId);
		List<Address> address= query.list();
		return address;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

}
