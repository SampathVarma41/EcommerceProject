package com.niit.project.external.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.project.internal.dao.SupplierDao;
import com.niit.project.internal.model.Supplier;

@Controller
public class SupplierController 
{
	@Autowired
	SupplierDao supplierDao;
	
	@RequestMapping("/supplier")
	  public String showCategory(Model m)
	  {
		  List<Supplier> listSuppliers= supplierDao.listSuppliers();
		  m.addAttribute("supplierList",listSuppliers);
		  return "Supplier";
		  
	  }
	
	@RequestMapping(value="/InsertSupplier",method=RequestMethod.POST)
	  public String addSupplier( @RequestParam("supname")String supName ,@RequestParam("supaddr") String supAddr,Model m)
	  {  
		  Supplier supplier=new Supplier();
		  supplier.setSupplierName(supName);
		  supplier.setSupplierAddr(supAddr);
		  
		  supplierDao.addSupplier(supplier);
		  
		  List<Supplier> listSuppliers= supplierDao.listSuppliers();
		  m.addAttribute("supplierList",listSuppliers);
		 
		  return "Supplier";
	  }
	 
	@RequestMapping("/deleteSupplier/{supplierId}")
	  public String deleteSupplier(@PathVariable("supplierId") int supplierId,Model m)
	  {
		  Supplier supplier=(Supplier)supplierDao.getSupplier(supplierId);
		  supplierDao.deleteSupplier(supplier);
		  
		  List<Supplier> listSuppliers= supplierDao.listSuppliers();
		  m.addAttribute("supplierList",listSuppliers);
		 
		 
		  return "Supplier";
	  }

	  @RequestMapping("/editSupplier/{supplierId}")
	  public String editSupplier(@PathVariable("supplierId") int supplierId,Model m)
	  {
		  Supplier supplier=(Supplier)supplierDao.getSupplier(supplierId);
		  
		  List<Supplier> listSuppliers= supplierDao.listSuppliers();
		  m.addAttribute("supplierList",listSuppliers);
		  m.addAttribute("supplierInfo",supplier);
		 
		  return "UpdateSupplier";
	  }
	  
	  @RequestMapping(value="/updateSupplier",method=RequestMethod.POST)
	  public String updateSupplier(@RequestParam("supid")int supplierId, @RequestParam("supname")String supName ,@RequestParam("supaddr") String supAddr,Model m)
	  {
		  Supplier supplier=(Supplier)supplierDao.getSupplier(supplierId);
		  supplier.setSupplierName(supName);
		  supplier.setSupplierAddr(supAddr);
		  
		  supplierDao.updateSupplier(supplier);
		  
		  List<Supplier> listSuppliers= supplierDao.listSuppliers();
		  m.addAttribute("supplierList",listSuppliers);
		  
		  return"Supplier";
		  
	  }
}
