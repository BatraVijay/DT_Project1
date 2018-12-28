package com.backend.daosimpl;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.daos.ProductDao;
import com.backend.models.Product;

@Repository("productDao")
@Transactional

public class ProductDaoImpl implements ProductDao {

	
	@Autowired
	SessionFactory sessionFactory;

	
	public boolean addProduct(Product productObj) {
		try 
		{
	        Session session=sessionFactory.getCurrentSession();
	        session.save(productObj);
	        return true;
	        }
		
	        catch(Exception e){
	            e.printStackTrace();
	        }
		
	        return false;
	    }
	 
	

	@Override
	public boolean deleteProduct(Product productObj) {
			
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.delete(productObj);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
			return false;
		}

	@Override
	public boolean updateProduct(Product productObj) {
		try {
            Session session=sessionFactory.getCurrentSession();
            session.update(productObj);
            return true;
            }
            catch(Exception e){e.printStackTrace();}
            return false;
     }

	@Override
	public Product getProduct(int pId) {
		
		 try {
		        Session session=sessionFactory.getCurrentSession();
		        Product obj=session.get(Product.class, pId);
		        return obj;
		 		}
		        catch(Exception e){e.printStackTrace();}
		        return null;
		 }

	@Override
	public List<Product> listProducts() {
		try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from Product");
            List<Product> product=query.list();
            return product;
            }
            catch(Exception e){e.printStackTrace();}
			return null;
	}


	@Override
	public List<Product> getAllProductsByCategory(int categoryId) {
		try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from Product where categoryId=:x");
            query.setInteger("x",categoryId);
            List<Product> product =query.list();
            return product;
            }
            catch(Exception e){e.printStackTrace();}
			return null;
	}
	
	
}