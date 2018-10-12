package com.niit.project.internal.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.project.internal.dao.CategoryDao;
import com.niit.project.internal.model.Category;

public class CategoryDaoTest 
{
static CategoryDao categoryDao;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		categoryDao=(CategoryDao)context.getBean("categoryDao");
	}
	
	
	@Test
	public void addCategoryTest()
	{
		Category category=new Category();
		
		category.setCategoryName("");
		category.setCategoryDesc("");
		
		assertTrue("Problem in Adding Category:",categoryDao.addCategory(category));
	}
	@Ignore
	@Test
	public void updateCategoryTest()
	{
		Category category=categoryDao.getCategory(1);
		category.setCategoryName("");
		category.setCategoryDesc("");
		
		assertTrue("Problem in Updating the Category",categoryDao.updateCategory(category));
	}
	@Ignore
	@Test 
	public void deleteCategoryTest()
	{
		Category category=categoryDao.getCategory(1);
		assertTrue("Problem in Deleting the Category",categoryDao.deleteCategory(category));
	}
	@Ignore
	@Test
	public void listCategoriesTest()
	{
		List<Category> listCategories=categoryDao.listCategories();
		assertTrue("Problem in Listing Categories",listCategories.size()>0);
		
		for(Category category:listCategories)
		{
			System.out.print(category.getCategoryId()+":::");
			System.out.print(category.getCategoryName()+":::");
			System.out.println(category.getCategoryDesc()+":::");
		}
	}

}
