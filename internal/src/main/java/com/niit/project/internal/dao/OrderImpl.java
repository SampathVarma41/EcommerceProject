package com.niit.project.internal.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.project.internal.model.Order;

@Repository("orderDao")
public class OrderImpl implements OrderDao
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean paymentProcess(Order order) {
		try 
		{
		sessionFactory.getCurrentSession().save(order);
		return true;
		}
		catch(Exception e) 
		{
		System.out.println("Exception Arised"+e);
		return false;
		}
	}
}
