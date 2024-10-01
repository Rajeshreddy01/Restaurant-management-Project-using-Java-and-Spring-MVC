package com.jsp.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.foodapp.dao.ProductDao;
import com.jsp.foodapp.dto.Products;
@Controller
public class ProductController {
	@Autowired
	ProductDao dao;
	
	@RequestMapping("/addproduct")
	public ModelAndView addProduct() {
		ModelAndView mav = new ModelAndView("/addproduct");
		Products product = new Products();
		mav.addObject("product", product);
		return mav;
	}
	@RequestMapping("/saveproduct")
	public ModelAndView saveproduct(@ModelAttribute("product") Products p) {
		ModelAndView mav = new ModelAndView("adminoptions");
		dao.saveProduct(p);
		return mav;
	}
	
	@RequestMapping("/viewallproducts")
	public ModelAndView m3(@ModelAttribute("product")Products p) {
		ModelAndView mav = new ModelAndView("allproducts");
		List<Products> products=dao.fetchAllProducts();
		mav.addObject("productList",products);
		return mav;
	}
	@RequestMapping("/update")
	public ModelAndView m3(@RequestParam("id")int id) {
		ModelAndView mav = new ModelAndView("updateProduct");
        Products p= dao.findProductById(id);
		mav.addObject("productInfo",p);
		return mav;
	}
	@RequestMapping("/updateproduct")
	public ModelAndView m5(@ModelAttribute("productInfo2")Products p) {
		ModelAndView mav = new ModelAndView("AllProducts");
		dao.updateProduct(p);
		return mav;
	}
	@RequestMapping("/delete")
	public ModelAndView deleteProduct(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/viewallproducts") ;
	
		dao.deleteProductById(id) ;
		return mav ;
	}
	
	@RequestMapping("/viewallproduct")
	public ModelAndView viewAllProduct() {
		ModelAndView mav = new ModelAndView("displayallproducts") ;
		List<Products> products = dao.fetchAllProducts() ;
		mav.addObject("productList", products) ;
		return mav ;
	}
}
