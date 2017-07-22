package com.juniorro.servicecompany.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.juniorro.servicecompany.model.Services;
import com.juniorro.servicecompany.model.SystemUser;
import com.juniorro.servicecompany.model.security.UserRole;
import com.juniorro.servicecompany.service.RoleService;
import com.juniorro.servicecompany.service.ServicesService;
import com.juniorro.servicecompany.service.SystemUserService;

@Controller
public class UserController {
	
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
		model.addAttribute("systemUser", systemUser);
		model.addAttribute("allServices", allServices);
		return new ModelAndView("home", "service", new Services());
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("systemUser", new SystemUser());
		return "register";
	}

	@RequestMapping(value = "/profile")
	public String profile(Model model, Principal principal) {
		SystemUser systemUser = systemUserService.findByUsername(principal.getName());
		model.addAttribute("systemUser", systemUser);
		return "profile";
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
			systemUser.setJoinDate(new Date());
			final SystemUser newcustomer = systemUserService.saveUser(systemUser, userRoles);
			//final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
					//+ request.getContextPath();
			//eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newcustomer, appUrl));
			redirect.addFlashAttribute("success", true);
			return "redirect:/login";
		}
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, Principal principal) {
		/*SystemUser systemUser = systemUserService.findByUsername(principal.getName());*/
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SystemUser systemUser = systemUserService.findByUsername(auth.getPrincipal().getClass().getName());
		System.out.println(systemUser);
		systemUser.setLastSignIn(new Date());	
		if (auth != null) {
			
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}		
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateCustomer(@Valid SystemUser systemUser, BindingResult result, final RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("profile");
		} else {
			systemUserService.saveUpdate(systemUser);
			MultipartFile profilePhoto = systemUser.getProfilePhoto();
			try {
				byte[] bytes = profilePhoto.getBytes();
				String name = systemUser.getId() + ".png";
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/assets/img/user/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			redirect.addFlashAttribute("updatedUser", true);
			return new ModelAndView("redirect:/profile");
		}
	}

}
