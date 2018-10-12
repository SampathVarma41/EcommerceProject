package com.niit.project.internal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.project.internal.model.Category;

@Repository("categoryDao")
@Transactional
public class CategoryImpl implements CategoryDao
{
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
    public boolean addCategory(Category category) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(category);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}
	
	@Transactional
    public boolean updateCategory(Category category) 
	{
		try
		{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	@Transactional
    public boolean deleteCategory(Category category) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(category);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
   
	
	
    public Category getCategory(int categoryId) 
	{
		try
		{
			Session session= sessionFactory.getCurrentSession();
			Category category=(Category)session.get(Category.class,categoryId);
			return category;
		}
		catch(Exception e)
		{
			return null;
		}
	}


    public List<Category> listCategories() 
	{
		try
		{
			Session session= sessionFactory.openSession();
			Query query=session.createQuery("from Category");
			List<Category> listCategories=query.list();
			session.close();
			return listCategories;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
