package com.niit.project.internal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

 @Entity
 @Table
public class Order 
{
 @Id
 @GeneratedValue
	
	private int orderId;
	private String userName;
	private Date orderDate;
	private double totalAmountPaid;
	private String pmode;
	
	public int getOrderId() 
	{
		return orderId;
	}
	public void setOrderId(int orderId) 
	{
		this.orderId = orderId;
	}
	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public Date getOrderDate() 
	{
		return orderDate;
	}
	public void setOrderDate(Date orderDate) 
	{
		this.orderDate = orderDate;
	}
	public double getTotalAmountPaid() 
	{
		return totalAmountPaid;
	}
	public void setTotalAmountPaid(double totalAmountPaid) 
	{
		this.totalAmountPaid = totalAmountPaid;
	}
	public String getPmode() 
	{
		return pmode;
	}
	public void setPmode(String pmode) 
	{
		this.pmode = pmode;
	}
	
}
