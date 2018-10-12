package com.niit.project.internal.dao;

import java.util.List;

import com.niit.project.internal.model.Cart;

public interface CartDao 
{
	public boolean addToCart(Cart cart);
	public boolean deleteFromCart(Cart cart);
	public boolean updateFromCart(Cart cart);
	public Cart getCartItem(int cartItemId);
	public List<Cart> listCartItems(String userName);
}
