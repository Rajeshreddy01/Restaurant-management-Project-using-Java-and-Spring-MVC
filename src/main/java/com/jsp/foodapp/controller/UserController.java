package com.jsp.foodapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.foodapp.dao.UserDao;
import com.jsp.foodapp.dto.User;

@Controller
public class UserController {
	@Autowired
	UserDao dao;
	
	@RequestMapping("/adduser")
	public ModelAndView addUser() {
		ModelAndView mav = new ModelAndView("adduserform");
		User u = new User();
		mav.addObject("user" , u);
		return mav;
	}
	
	@RequestMapping("/saveuser")
	public ModelAndView saveUser(@ModelAttribute("user") User u) {
		ModelAndView mav = new ModelAndView("adminoptions");
		dao.saveUser(u);
		mav.addObject("message","user saved successfully");
		return mav;
		
	}
	@RequestMapping("/validateUser")
	public ModelAndView login(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User admin = dao.fetchUserByEmailAndPassword(email, password);
		if(admin!=null&&email.equalsIgnoreCase(admin.getEmail())&&password.equalsIgnoreCase(admin.getPassword())) {
			ModelAndView mav = new ModelAndView("index");
			return mav;
			}
		else {
				ModelAndView mav = new ModelAndView("loginuser");
				mav.addObject("msg","invalid credentials");
				return mav;
			}
	}

}
