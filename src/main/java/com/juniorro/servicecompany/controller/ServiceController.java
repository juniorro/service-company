package com.juniorro.servicecompany.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.juniorro.servicecompany.model.Services;
import com.juniorro.servicecompany.service.ServicesService;
import com.juniorro.servicecompany.service.SystemUserService;

@Controller
public class ServiceController {
	
	@Autowired
	SystemUserService systemUserService;
	
	@Autowired
	ServicesService servicesService;

	@RequestMapping(value = "/saveService", method = RequestMethod.POST) 
	public ModelAndView saveService(@Valid Services service, BindingResult result, Model model, RedirectAttributes redirect) {
		if(service.getServiceDate() == null){
			List <Services> allServices = servicesService.allServices();
			model.addAttribute("allServices", allServices);
			redirect.addFlashAttribute("noDate", true);
			return new ModelAndView("redirect:/", "service", new Services());			
		}
		if(service.getName() == null){
			List <Services> allServices = servicesService.allServices();
			model.addAttribute("allServices", allServices);
			redirect.addFlashAttribute("noName", true);
			return new ModelAndView("redirect:/", "service", new Services());			
		}
		if (result.hasErrors()) {
			List <Services> allServices = servicesService.allServices();
			model.addAttribute("allServices", allServices);
			redirect.addFlashAttribute("error", true);
			return new ModelAndView("redirect:/", "service", new Services());
		}
		service.setRequestDate(new Date());
		service.setStatus("completed");			
		service.setDescription("Sweeping, Mopping Floor.");
		servicesService.saveService(service);
		List <Services> allServices = servicesService.allServices();
		model.addAttribute("allServices", allServices);
		redirect.addFlashAttribute("newService", true);
		return new ModelAndView("redirect:/", "service", new Services());
	}
	
	@RequestMapping(value = "/deleteService", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("id") Long id, Model model, RedirectAttributes redirect) {
		Services service = servicesService.getOne(id);
		if(service.getStatus().contains("completed")){
			List <Services> allServices = servicesService.allServices();
			model.addAttribute("allServices", allServices);
			redirect.addFlashAttribute("cannotDelete", true);
			return new ModelAndView("redirect:/", "service", new Services());
			
		}
		servicesService.delete(id);
		redirect.addFlashAttribute("serviceDeleted", true);
        return new ModelAndView("redirect:/");
    }


}
