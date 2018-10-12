package com.niit.project.internal.dao;

import java.util.List;

import com.niit.project.internal.model.Supplier;

public interface SupplierDao 
{
	public boolean addSupplier(Supplier supplier);
	public boolean updateSupplier(Supplier supplier);
	public boolean deleteSupplier(Supplier supplier);
	
	public Supplier getSupplier(int supplierId);
	public List<Supplier> listSuppliers();
}
