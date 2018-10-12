package com.niit.project.internal.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.project.internal.dao.UserDao;
import com.niit.project.internal.model.User;

public class UserDaoTest 
{
static UserDao userDao;
	
	@BeforeClass
	public static void preExecution() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		userDao = (UserDao)context.getBean("userDao");
	}
	
@Ignore
	@Test
	public void registerUser() {
		
		User user = new User();
		user.setUserName("SampathVarma");
		user.setPassword("1230813141");
		user.setCustomerName("Sam");
		user.setRole("Admin");
		user.setEmailId("sampathmudunuri@gmail.com");
		user.setAddress("vizag");
		
		assertTrue("Problem in registering a new user",userDao.registerUser(user));
		
	}
	
	@Ignore
	@Test
	public void updateUser() {
		
		User user = userDao.getUser("10");
		user.setCustomerName("Chinnu");
		
		assertTrue("Problem in updating the user",userDao.updateUser(user));
	}

}
