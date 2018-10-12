package com.niit.project.internal.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.niit.project.internal.dao.OrderDao;
import com.niit.project.internal.model.Order;

public class OrderDaoTest 
{
static OrderDao orderDao;
	
	@BeforeClass
	public static void preExecution() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		
		orderDao = (OrderDao)context.getBean("orderDao");
		
	}
	
	@Test
	public void processPayment() {
		Order order = new Order();
		
		order.setPmode("CC");
		order.setUserName("");
		order.setTotalAmountPaid(20);
		order.setOrderDate(new java.util.Date());
		
		assertTrue("problem in adding order",orderDao.paymentProcess(order));
	}
}
