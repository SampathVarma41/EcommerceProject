package com.niit.project.external.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.niit.project.internal.dao.CartDao;
import com.niit.project.internal.dao.ProductDao;
import com.niit.project.internal.model.Cart;

@Controller
public class CartController 
{
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CartDao cartDao;
	
	@RequestMapping(value="/cart")
	public String showCart(HttpSession session,Model m)
	{
		String username = (String)session.getAttribute("username");
		List<Cart> listCartItems = cartDao.listCartItems(username);
		m.addAttribute("cartItems",listCartItems);
		m.addAttribute("totalPayment",this.calcTotalAmount(listCartItems));
		
		return"Cart";
	}
	
	
	@RequestMapping(value="addToCart/{productId}")
	public String addToCart(@PathVariable("productId")int productId,@RequestParam("quantity")int quantity,HttpSession session,Model m)
	{
		String username = (String)session.getAttribute("username");
		if ((String)session.getAttribute("username")!=null) {
		Cart cart = new Cart();
		
		cart.setProductId(productId);
		cart.setProductName(productDao.getProduct(productId).getProductName());
		cart.setPrice(productDao.getProduct(productId).getPrice());
		cart.setQuantity(quantity);
		cart.setStatus("NP");
		cart.setUserName((String)session.getAttribute("username"));
		
		cartDao.addToCart(cart);
		
		List<Cart> listCartItems = cartDao.listCartItems(username);
		m.addAttribute("cartItems",listCartItems);
		m.addAttribute("totalPayment",this.calcTotalAmount(listCartItems));
		
		return "Cart";
		}
       else {
	return "redirect:/Login";
	}
}		

	@RequestMapping("updateCartItem/{cartItemId}")
	public String updateCartItem(@PathVariable("cartItemId")int cartItemId,@RequestParam("quantity")int quantity,HttpSession session,Model m)
	{
		String username = (String)session.getAttribute("username");
     Cart cart=cartDao.getCartItem(cartItemId);
     cart.setQuantity(quantity);
     cartDao.updateFromCart(cart);
     
     List<Cart> listCartItems = cartDao.listCartItems(username);
		m.addAttribute("cartItems",listCartItems);
		m.addAttribute("totalPayment",this.calcTotalAmount(listCartItems));
		
	return "Cart";
	}
	@RequestMapping("deleteCartItem/{cartItemId}")
	public String deleteCartItem(@PathVariable("cartItemId")int cartItemId,HttpSession session,Model m)
	{String username = (String)session.getAttribute("username");
	
     Cart cart=cartDao.getCartItem(cartItemId);
     cartDao.deleteFromCart(cart);
     
     List<Cart> listCartItems = cartDao.listCartItems(username);
		m.addAttribute("cartItems",listCartItems);
		m.addAttribute("totalPayment",this.calcTotalAmount(listCartItems));
		
	return "Cart";
	}
	public int calcTotalAmount(List<Cart> cartItems) {
		int var=0,totalAmount=0;
		while(var<cartItems.size()) {
			Cart cart = cartItems.get(var);
			totalAmount = totalAmount+(cart.getPrice()*cart.getQuantity());
			var++;
		}
		return totalAmount;
	}
}
