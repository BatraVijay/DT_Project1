package com.backend.daosimpl;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.daos.ProductDao;
import com.backend.models.Product;

@Repository("ProductDao")
@Transactional

public class ProductDaoImpl implements ProductDao {

	
	@Autowired
	SessionFactory sessionfactory;
	
	
	@Override
	public boolean addProduct(Product product) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.save(product);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.delete(product);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			session.update(product);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public Product getProductById(int pId) {
		try
		{
			Session session=sessionfactory.getCurrentSession();
			 Product sup=session.get(Product.class, pId);
			  return sup;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<Product> getAllProducts() {
		 try {
		        Session session=sessionfactory.getCurrentSession();
		        Query<Product> query=session.createQuery("from Product");
		        List<Product> product=query.list();
		        return product;
		        }
		        catch(Exception e){
		            e.printStackTrace();
		        }
		        return null;
		
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
