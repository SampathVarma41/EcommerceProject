package com.niit.project.internal.dao;

import com.niit.project.internal.model.User;

public interface UserDao 
{
	public boolean registerUser(User user);
	public boolean updateUser(User user);
	public User getUser(String userName);
	public boolean approveUser(User user);
}