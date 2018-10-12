package com.niit.project.external.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.project.internal.dao.CategoryDao;
import com.niit.project.internal.dao.ProductDao;
import com.niit.project.internal.dao.SupplierDao;
import com.niit.project.internal.model.Category;
import com.niit.project.internal.model.Product;
import com.niit.project.internal.model.Supplier;

@Controller
public class ProductController
{
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	SupplierDao supplierDao;
	
	@RequestMapping(value="/product")
	public String showProductPage(Model m)
	{
		Product product=new Product();
		m.addAttribute(product);
		
		List<Product> listProducts=productDao.getProducts();
		m.addAttribute("listProducts",listProducts);
		
		m.addAttribute("categoryList",this.getCategories());
		m.addAttribute("supplierList", this.getSuppliers());
		return"Product";
	}
	
	@RequestMapping(value="/InsertProduct",method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product")Product product, Model m, @RequestParam("pimage")MultipartFile filedet)
	{
		productDao.addProduct(product);
		Product product1=new Product();
		m.addAttribute(product1);
		
		List<Product> listProducts=productDao.getProducts();
		m.addAttribute("listProducts",listProducts);
		m.addAttribute("categoryList",this.getCategories());
		m.addAttribute("supplierList", this.getSuppliers());
		//adding the image
		String path="E:\\Devops\\Rajesh\\ecomfront\\src\\main\\webapp\\resources\\images\\";
		path=path+String.valueOf(product.getProductId())+".jpg";
		File file=new File(path);
		if(!filedet.isEmpty())
		{
			try {
				byte[] buffer=filedet.getBytes();
				FileOutputStream fos=new FileOutputStream(file);
				BufferedOutputStream bs=new BufferedOutputStream(fos);
				bs.write(buffer);
				bs.close();
			}
			catch(Exception e){
				m.addAttribute("errorInfo","Exception Arised:"+e.getMessage());
			}
		}
		else {
			m.addAttribute("errorInfo","There is system problem or Image not selected i.e why no image inserted");
		}
		
		//completed
		
		return "Product";
	}
	
	@RequestMapping(value="/editProduct/{productId}",method=RequestMethod.GET)
	public String editProduct(@PathVariable("productId")int productId,Model m)
	{
		Product product=productDao.getProduct(productId);
        m.addAttribute(product);
		
		List<Product> listProducts=productDao.getProducts();
		m.addAttribute("listProducts",listProducts);
		m.addAttribute("categoryList",this.getCategories());
		m.addAttribute("supplierList", this.getSuppliers());
		return "Product";
    }
	
	@RequestMapping(value="/deleteProduct/{productId}",method=RequestMethod.GET)
	public String deleteProduct(@PathVariable("productId")int productId,Model m)
	{
		Product product=productDao.getProduct(productId);
        productDao.deleteProduct(product);
        
        Product product1=new Product();
        m.addAttribute(product1);
		
		List<Product> listProducts=productDao.getProducts();
		m.addAttribute("listProducts",listProducts);
		m.addAttribute("categoryList",this.getCategories());
		m.addAttribute("supplierList", this.getSuppliers());
		return"Product";
    }
	
public LinkedHashMap<Integer,String> getCategories()
{
		
		List<Category> listCategories = categoryDao.listCategories();
		
		LinkedHashMap<Integer,String> categoryList = new LinkedHashMap<Integer,String>();
		
		for(Category category:listCategories)
		{	
			categoryList.put(category.getCategoryId(),category.getCategoryName());
		}
		return categoryList;
       }
public LinkedHashMap<Integer,String> getSuppliers(){
	
	List<Supplier> listSuppliers = supplierDao.listSuppliers();
	
	LinkedHashMap<Integer,String> supplierList = new LinkedHashMap<Integer,String>();
	
	for (Supplier supplier:listSuppliers) {	
		supplierList.put(supplier.getSupplierId(),supplier.getSupplierName());
	}
	return supplierList;
}

@RequestMapping(value="/productDesc/{productId}")
public String showProductDesc(@PathVariable("productId")int productId,Model m) {
	Product product =productDao.getProduct(productId);
	m.addAttribute("product",product);
    return "ProductDesc";
}

@RequestMapping(value= {"/" , "/home" , "/index"})
	
	public String showHomePage(Model m) {
	List<Product> listProducts=productDao.getProducts();
	m.addAttribute("listProducts",listProducts);
	    return "index";
	}
}
