package com.niit.project.internal.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.niit.project.internal.model.User;

public class UserImpl implements UserDao
{
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public boolean registerUser(User user) {
		try
		{
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception e) 
		{
			System.out.println("Exception arised" +e);
			return false;
		}
	}

	@Transactional
	public boolean updateUser(User user) {
		try
		{
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e) 
		{
			System.out.println("Exception arised" +e);
			return false;
		}
	}

	@Transactional
	public User getUser(String userName) {
		Session session = sessionFactory.openSession();
		User user = (User)session.get(User.class, userName);
		session.close();
		return user;
	}

	public boolean approveUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
