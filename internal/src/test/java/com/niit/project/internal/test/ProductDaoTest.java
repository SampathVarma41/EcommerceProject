package com.niit.project.internal.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.project.internal.dao.ProductDao;
import com.niit.project.internal.model.Product;

public class ProductDaoTest 
{
static ProductDao productDao;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		productDao=(ProductDao)context.getBean("productDao");
	}

	@Test
	public void addProductTestCase()
	{
		Product product=new Product();
		product.setProductName("");
		product.setProductDesc("");
		product.setPrice(10);
		product.setStock(20);
		product.setCategoryId(1);
		product.setSupplierId(1);
		
		assertTrue("Problem in Adding Product",productDao.addProduct(product));
	}
}
