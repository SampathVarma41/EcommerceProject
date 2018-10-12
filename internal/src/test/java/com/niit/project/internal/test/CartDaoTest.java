package com.niit.project.internal.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.niit.project.internal.dao.CartDao;
import com.niit.project.internal.model.Cart;

public class CartDaoTest 
{
static CartDao cartDao;
	
	@BeforeClass
	public static void preExecution() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		
		cartDao = (CartDao)context.getBean("cartDao");
		
	}
	
	@Ignore
	@Test
	public void addcartTest() {
		
		Cart cart = new Cart();
		cart.setPrice(10);
		cart.setProductId(5);
		cart.setProductName("");
		cart.setQuantity(1);
		cart.setStatus("NP");
		cart.setUserName("");
		
		assertTrue("problem in adding cart",cartDao.addToCart(cart));
		
	}
	@Ignore
	@Test
	public void updateCategoryTest() {
		
		Cart cart = cartDao.getCartItem(168);
		cart.setQuantity(4);
		
		assertTrue("problem in adding cart",cartDao.updateFromCart(cart));	
	}
	
	@Ignore
	@Test
	public void deleteCategoryTest() {
		Cart cart = cartDao.getCartItem(174);
		assertTrue("problem in adding category",cartDao.deleteFromCart(cart));
		}
}
