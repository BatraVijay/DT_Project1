package com.backend.daosimpl;

import java.util.List;




import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.daos.SupplierDao;
import com.backend.models.Supplier;



@Repository("supplierDaos")
@Transactional
public class SupplierDaoImpl implements SupplierDao {

	
	 @Autowired
	    SessionFactory sessionFactory;
	     
	    
	    public boolean addSupplier(Supplier supplierObj) {
	        try {
	        Session session=sessionFactory.getCurrentSession();
	        session.save(supplierObj);
	        return true;
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	    
	    public boolean deleteSupplier(Supplier supplierObj) {
	        try {
	        Session session=sessionFactory.getCurrentSession(); 
	        session.delete(supplierObj);
	        return true;
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	    
	    public boolean updateSupplier(Supplier supplierObj) {
	        try {
	        Session session=sessionFactory.getCurrentSession();
	        session.update(supplierObj);
	        return true;
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	    
	    public Supplier getSupplierById(int id) {
	        try {
	        Session session=sessionFactory.getCurrentSession();
	        Supplier sup=session.get(Supplier.class, id);
	        return sup;
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	    
	    public List<Supplier> getAllSuppliers() {
	        try {
	        Session session=sessionFactory.getCurrentSession();
	        Query<Supplier> query=session.createQuery("from Supplier");
	        List<Supplier> suppliers=query.list();
	        return suppliers;
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	
	
	
	
	
	
	
	
	
}
