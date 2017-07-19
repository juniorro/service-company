package com.juniorro.servicecompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.juniorro.servicecompany.model.Services;
import com.juniorro.servicecompany.service.ServicesService;
import com.juniorro.servicecompany.service.SystemUserService;

@Controller
public class HomeController {
	
	@Autowired
	SystemUserService systemUserService;
	
	@Autowired
	ServicesService servicesService;

	@RequestMapping(value = { "/index", "/"})
	public ModelAndView index(Model model) {
		List <Services> allServices = servicesService.allServices();
		model.addAttribute("allServices", allServices);
		return new ModelAndView("home", "service", new Services());
	}

	@RequestMapping(value = "/register")
	public String register() {
		return "create";
	}

	@RequestMapping(value = "/forgotPassword")
	public String reset() {
		return "reset";
	}

	@RequestMapping(value = "/login")
	public String home() {
		return "login";
	}

}
