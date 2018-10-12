package com.niit.project.external.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.project.internal.dao.CategoryDao;
import com.niit.project.internal.model.Category;

@Controller
public class CategoryController 
{
	@Autowired
	CategoryDao categoryDao;
  @RequestMapping("/category")
  public String showCategory(Model m)
  {
	  List<Category> listCategories= categoryDao.listCategories();
	  m.addAttribute("categoryList",listCategories);
	  return "Category";
	  
  }
  @RequestMapping(value="/InsertCategory",method=RequestMethod.POST)
  public String addCategory( @RequestParam("catname")String catName ,@RequestParam("catdesc") String catDesc,Model m)
  {  
	  Category category=new Category();
	  category.setCategoryName(catName);
	  category.setCategoryDesc(catDesc);
	  
	  categoryDao.addCategory(category);
	  
	  List<Category> listCategories= categoryDao.listCategories();
	  m.addAttribute("categoryList",listCategories);
	 
	  return "Category";
  }
 
  @RequestMapping("/deleteCategory/{categoryId}")
  public String deleteCategory(@PathVariable("categoryId") int categoryId,Model m)
  {
	  Category category=(Category)categoryDao.getCategory(categoryId);
	  categoryDao.deleteCategory(category);
	  
	  List<Category> listCategories= categoryDao.listCategories();
	  m.addAttribute("categoryList",listCategories);
	 
	 
	  return "Category";
  }

  @RequestMapping("/editCategory/{categoryId}")
  public String editCategory(@PathVariable("categoryId") int categoryId,Model m)
  {
	  Category category=(Category)categoryDao.getCategory(categoryId);
	  
	  List<Category> listCategories= categoryDao.listCategories();
	  m.addAttribute("categoryList",listCategories);
	  m.addAttribute("categoryInfo",category);
	 
	  return "UpdateCategory";
  }
  
  @RequestMapping(value="/updateCategory",method=RequestMethod.POST)
  public String updateCategory(@RequestParam("catid")int categoryId, @RequestParam("catname")String catName ,@RequestParam("catdesc") String catDesc,Model m)
  {
	  Category category=(Category)categoryDao.getCategory(categoryId);
	  category.setCategoryName(catName);
	  category.setCategoryDesc(catDesc);
	  
	  categoryDao.updateCategory(category);
	  
	  List<Category> listCategories= categoryDao.listCategories();
	  m.addAttribute("categoryList",listCategories);
	  
	  return"Category";
	  
  }
}
