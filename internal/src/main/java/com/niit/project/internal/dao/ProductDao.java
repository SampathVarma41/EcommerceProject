package com.niit.project.internal.dao;

import java.util.List;

import com.niit.project.internal.model.Product;

public interface ProductDao 
{
	public boolean addProduct(Product product);
	public boolean deleteProduct(Product product);
	public boolean updateProduct(Product product);
	public List<Product> getProducts();
	public Product getProduct(int productId);
}
