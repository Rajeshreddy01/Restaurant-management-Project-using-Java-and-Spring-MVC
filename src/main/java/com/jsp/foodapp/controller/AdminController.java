package com.jsp.foodapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.foodapp.dao.AdminDao;
import com.jsp.foodapp.dto.Admin;

@Controller
public class AdminController {
	@Autowired
	AdminDao adminDao;
	@RequestMapping("/addadmin")
	public ModelAndView m1() {
		ModelAndView mav = new ModelAndView("AdminLoginForm");
		Admin a = new Admin();
		a.setEmail("qwerty");
		a.setPassword("admin");
		
		adminDao.saveAdmin(a);
		return mav;
	}
	@RequestMapping("/validate")
	public ModelAndView login(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Admin admin = adminDao.fetchAdminByEmailAndPassword(email, password);
		if(admin!=null) {
			ModelAndView mav = new ModelAndView("adminoptions");
			return mav;
			}else {
				ModelAndView mav = new ModelAndView("AdminLoginForm");
				mav.addObject("msg","invalid credentials");
				return mav;
			}
	}
}
