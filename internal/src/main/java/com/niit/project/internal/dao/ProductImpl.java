package com.niit.project.internal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.project.internal.model.Product;

@Repository("productDao")
@Transactional
public class ProductImpl implements ProductDao
{
	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addProduct(Product product) 
	{
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(product);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	@Transactional
	public boolean deleteProduct(Product product) {
		try
		{
			sessionFactory.getCurrentSession().delete(product);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	@Transactional
	public boolean updateProduct(Product product) {
		try
		{
			sessionFactory.getCurrentSession().update(product);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public List<Product> getProducts() 
	{  
	  Session session=sessionFactory.openSession();
	  @SuppressWarnings("unchecked")
	  List<Product> listProducts=session.createQuery("from Product").list();
      return listProducts;
	}
	public Product getProduct(int productId) 
	{
		Session session=sessionFactory.openSession();
		Product product=(Product)session.get(Product.class,productId);
		
		return product;
	}
}
