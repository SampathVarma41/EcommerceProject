package com.niit.project.internal.test;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.project.internal.dao.SupplierDao;
import com.niit.project.internal.model.Supplier;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SupplierDaoTest 
{
	static SupplierDao supplierDao;
	
	@BeforeClass
	public static void preExecution() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		
		supplierDao = (SupplierDao)context.getBean("supplierDao");
		
	}
	
	@Ignore
	@Test
	public void addSupplier() {
		
		Supplier supplier = new Supplier();
		supplier.setSupplierName("");
		supplier.setSupplierAddr("");
		
		assertTrue("problem in adding a supplier",supplierDao.addSupplier(supplier));
	}
	
	@Ignore
	@Test
	public void updateSupplier() {
		
		Supplier supplier = supplierDao.getSupplier(8);
		supplier.setSupplierAddr("");
		
		assertTrue("problem in updating the supplier info",supplierDao.updateSupplier(supplier));
	}
	
	@Ignore
	@Test
	public void deleteSupplier() {
		
		Supplier supplier = supplierDao.getSupplier(8);
		
		assertTrue("Problem is deleting the supplier",supplierDao.deleteSupplier(supplier));
	}
	
	@Ignore
	@Test
	public void listSuppliers() {
		
		List<Supplier> listSupplier = supplierDao.listSuppliers();
		
		assertNotNull("Problem to show all suppliers",listSupplier);
	}
}
