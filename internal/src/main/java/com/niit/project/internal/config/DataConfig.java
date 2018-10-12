package com.niit.project.internal.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.project.internal.dao.CartDao;
import com.niit.project.internal.dao.CartImpl;
import com.niit.project.internal.dao.CategoryDao;
import com.niit.project.internal.dao.CategoryImpl;
import com.niit.project.internal.dao.OrderDao;
import com.niit.project.internal.dao.OrderImpl;
import com.niit.project.internal.dao.ProductDao;
import com.niit.project.internal.dao.ProductImpl;
import com.niit.project.internal.dao.SupplierDao;
import com.niit.project.internal.dao.SupplierImpl;
import com.niit.project.internal.dao.UserDao;
import com.niit.project.internal.dao.UserImpl;
import com.niit.project.internal.model.Cart;
import com.niit.project.internal.model.Category;
import com.niit.project.internal.model.Order;
import com.niit.project.internal.model.Product;
import com.niit.project.internal.model.Supplier;
import com.niit.project.internal.model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.niit")
public class DataConfig 
{
	@Bean(name="dataSource")
	public DataSource getH2DataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/ecom");
		dataSource.setUsername("sampath");
		dataSource.setPassword("sampath123");
		
		System.out.println("--------Data Source Object Created-------");
		
		return dataSource;
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties properties=new Properties();
		properties.put("hibernate.hbm2ddl.auto","update");
		properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		
		LocalSessionFactoryBuilder sessionFactoryBuilder=new LocalSessionFactoryBuilder(getH2DataSource());
		sessionFactoryBuilder.addProperties(properties);
		
	    sessionFactoryBuilder.addAnnotatedClass(Cart.class);
		sessionFactoryBuilder.addAnnotatedClass(Category.class);
		sessionFactoryBuilder.addAnnotatedClass(Order.class);
		sessionFactoryBuilder.addAnnotatedClass(Product.class);
		sessionFactoryBuilder.addAnnotatedClass(Supplier.class);
		sessionFactoryBuilder.addAnnotatedClass(User.class);
		
		SessionFactory sessionFactory=sessionFactoryBuilder.buildSessionFactory();
		
		System.out.println("--------SessionFactory Object Created------");
		
		return sessionFactory;
	}
	
	
	
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("-----------Hibernate Transaction Object Created-------");
		return new HibernateTransactionManager(sessionFactory);
	}
	
	@Bean(name="categoryDao")
	public CategoryDao getCategoryDao() {
		System.out.println("The Category bean is created");
		return new CategoryImpl();
	}
	
	@Bean(name="productDao")
	public ProductDao getProductDAO() {
		System.out.println("The Product bean is created");
		return new ProductImpl();
	}
	
	@Bean(name="supplierDao")
	public SupplierDao getSupplierDao() {
		System.out.println("The Supplier bean is created");
		return new SupplierImpl();
	}
	@Bean(name="userDao")
	public UserDao getUserDao() {
		System.out.println("The User bean is created");
		return new UserImpl();
	}

	@Bean(name="cartDao")
	public CartDao getCartDao() {
		System.out.println("The Cart bean is created");
		return new CartImpl();
	}
	

	@Bean(name="orderDao")
	public OrderDao getOrderDao() {
		System.out.println("The Order bean is created");
		return new OrderImpl();
	}	

}
