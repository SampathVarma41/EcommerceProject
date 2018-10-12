package com.niit.project.internal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.project.internal.model.Cart;

@Repository("cartDao")
public class CartImpl implements CartDao
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean addToCart(Cart cart) {
		try 
		{
		sessionFactory.getCurrentSession().save(cart);
		return true;
		}
		catch(Exception e) 
		{
		System.out.println("Exception Arised"+e);
		return false;
		}
	}

	@Transactional
	public boolean deleteFromCart(Cart cart) {
		try 
		{
		sessionFactory.getCurrentSession().delete(cart);
		return true;
		}
		catch(Exception e) 
		{
		System.out.println("Exception Arised"+e);
		return false;
		}
	
	}

	@Transactional
	public boolean updateFromCart(Cart cart) {
		try 
		{
		sessionFactory.getCurrentSession().update(cart);
		return true;
		}
		catch(Exception e) 
		{
		System.out.println("Exception Arised"+e);
		return false;
		}			
	}


	public List<Cart> listCartItems(String userName) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Cart where status=:paidstatus and userName=:username");
		query.setParameter("paidstatus", "NP");
		query.setParameter("username", userName);
		List<Cart> listCartItems = query.list();
		return listCartItems;
		}

	@Transactional
	public Cart getCartItem(int cartItemId) {
		Session session = sessionFactory.openSession();
		Cart cart = (Cart)session.get(Cart.class,cartItemId);
		session.close();
		return cart;
	}

}
