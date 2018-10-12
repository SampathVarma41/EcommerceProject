package com.niit.project.internal.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.project.internal.dao.CategoryDao;
import com.niit.project.internal.model.Category;

public class DataConfigTest 
{
	public static void main(String arg[])
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
		CategoryDao categoryDao=(CategoryDao)context.getBean("categoryDao");
		
		Category category=new Category();
		category.setCategoryName("Mobile");
		category.setCategoryDesc("Samsung Mobiles");
		
		categoryDao.addCategory(category);
		
		System.out.println("Category Object Saved");
		
	}
}
