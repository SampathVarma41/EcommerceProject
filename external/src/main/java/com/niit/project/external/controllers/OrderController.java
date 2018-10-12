package com.niit.project.external.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.project.internal.dao.CartDao;
import com.niit.project.internal.dao.OrderDao;
import com.niit.project.internal.model.Cart;
import com.niit.project.internal.model.Order;

@Controller
public class OrderController 
{
	@Autowired
	CartDao cartDao;
	
	@Autowired
	OrderDao orderDao;
	

	@RequestMapping(value="/checkout")
	public String checkout(Model m,HttpSession session) {
	
		String username=((String)session.getAttribute("username"));
		
		List<Cart> listCartItems = cartDao.listCartItems(username);
		m.addAttribute("cartItems",listCartItems);
		m.addAttribute("totalPayment",this.calcTotalAmount(listCartItems));
		
		return "OrderConfirm";
	}
	@RequestMapping(value="/payment",method=RequestMethod.POST)
	public String processPayment(@RequestParam("pmode")String pmode,HttpSession session, Model m) {
		
		String username=((String)session.getAttribute("username"));

		Order order = new Order();
        List<Cart> listCartItems = cartDao.listCartItems(username);
        m.addAttribute("cartItems",listCartItems);

		order.setPmode(pmode);
		order.setUserName(username);
		order.setTotalAmountPaid(this.calcTotalAmount(listCartItems));
		order.setOrderDate(new java.util.Date());
		
		orderDao.paymentProcess(order);
		
		return "ThankYou";
}
	
	private double calcTotalAmount(List<Cart> cartItems) {
		int item=0;
		double totalAmount=0;
		while(item<cartItems.size()) {
			Cart cart = cartItems.get(item);
			totalAmount = totalAmount+(cart.getPrice()*cart.getQuantity());
			item++;
		}
		return totalAmount;
	}
}
