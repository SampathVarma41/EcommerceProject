package com.niit.project.internal.dao;

import com.niit.project.internal.model.Order;

public interface OrderDao 
{
	public boolean paymentProcess(Order order);
}
