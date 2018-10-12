package com.niit.project.internal.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.project.internal.model.Supplier;

@Repository("supplierDao")
@Transactional
public class SupplierImpl implements SupplierDao
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean addSupplier(Supplier supplier) {
		try
		{
			sessionFactory.getCurrentSession().save(supplier);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	@Transactional
	public boolean updateSupplier(Supplier supplier) {

		try
		{
			sessionFactory.getCurrentSession().update(supplier);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	@Transactional
	public boolean deleteSupplier(Supplier supplier) {
		try
		{
			sessionFactory.getCurrentSession().delete(supplier);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	@Transactional
	public Supplier getSupplier(int supplierId) {
		try
		{
			Session session= sessionFactory.getCurrentSession();
			Supplier supplier=(Supplier)session.get(Supplier.class,supplierId);
			return supplier;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public List<Supplier> listSuppliers() {
		try
		{
			Session session= sessionFactory.openSession();
			Query query=session.createQuery("from Supplier");
			List<Supplier> listSuppliers=query.list();
			session.close();
			return listSuppliers;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
