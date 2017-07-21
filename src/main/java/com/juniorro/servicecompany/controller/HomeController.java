package com.juniorro.servicecompany.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.juniorro.servicecompany.model.Services;
import com.juniorro.servicecompany.model.SystemUser;
import com.juniorro.servicecompany.model.security.UserRole;
import com.juniorro.servicecompany.service.RoleService;
import com.juniorro.servicecompany.service.ServicesService;
import com.juniorro.servicecompany.service.SystemUserService;

@Controller
public class HomeController {
	
	@Autowired
	SystemUserService systemUserService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ServicesService servicesService;

	@RequestMapping(value = { "/index", "/"})
	public ModelAndView index(Model model, Principal principal) {
		SystemUser systemUser = systemUserService.findByUsername(principal.getName());
		List <Services> allServices = systemUser.getServices();
		model.addAttribute("allServices", allServices);
		return new ModelAndView("home", "service", new Services());
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("systemUser", new SystemUser());
		return "register";
	}

	@RequestMapping(value = "/forgotPassword")
	public String reset() {
		return "reset";
	}

	@RequestMapping(value = "/login")
	public String home() {
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@Valid SystemUser systemUser, BindingResult result, Model model,
			final HttpServletRequest request, final RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "/register";
		}
		else {
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(systemUser, roleService.findByName("ROLE_ADMIN")));
			systemUser.setEnabled(true);
			final SystemUser newcustomer = systemUserService.saveUser(systemUser, userRoles);
			//final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
					//+ request.getContextPath();
			//eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newcustomer, appUrl));
			redirect.addFlashAttribute("success", true);
			return "redirect:/login";
		}
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

}
