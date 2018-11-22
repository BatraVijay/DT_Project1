package com.backend.daosimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.backend.daos.CategoryDaos;
import com.backend.models.Category;

@Repository("categoryDaos")
@Transactional
public class CategoryDaoImpl implements CategoryDaos{

	@Autowired
	SessionFactory sessionFactory;
	@Override

	 public boolean addCategory(Category category) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.save(category);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	
	public boolean deleteCategory(Category category) {
		try
		{
			Session session=sessionFactory.getCurrentSession();
			session.delete(category);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return false;
		}
			
		

	@Override
	public boolean updateCategory(Category category) {
		try {
            Session session=sessionFactory.getCurrentSession();
            session.update(category);
            return true;
            }
            catch(Exception e){e.printStackTrace();}
            return false;
            	}

	@Override
	public List<Category> getAllCategories() {
		try {
            Session session=sessionFactory.getCurrentSession();
            Query query=session.createQuery("from Category");
            List<Category> categories=query.list();
            return categories;
            }
            catch(Exception e){e.printStackTrace();}
	return null;
	}

	@Override
	public Category getCategoryById(int categoryId) {
		 
		 try {
		        Session session=sessionFactory.getCurrentSession();
		        Category obj=session.get(Category.class, categoryId);
		        return obj;
		 }
		        catch(Exception e){e.printStackTrace();}
		        return null;
		 }
	

	
}
