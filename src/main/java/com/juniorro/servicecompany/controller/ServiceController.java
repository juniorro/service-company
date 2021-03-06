package com.juniorro.servicecompany.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.juniorro.servicecompany.model.Services;
import com.juniorro.servicecompany.model.SystemUser;
import com.juniorro.servicecompany.newservicelistener.OnNewServiceRequest;
import com.juniorro.servicecompany.service.ServicesService;
import com.juniorro.servicecompany.service.SystemUserService;

@Controller
public class ServiceController {
	
	@Autowired
	SystemUserService systemUserService;
	
	@Autowired
	ServicesService servicesService;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@RequestMapping(value = "/saveService", method = RequestMethod.POST) 
	public ModelAndView saveService(@Valid Services service, BindingResult result, Principal principal, Authentication authentication, Model model, RedirectAttributes redirect) {
		if(service.getServiceDate() == null){
			redirect.addFlashAttribute("noDate", true);
			return new ModelAndView("redirect:/", "service", new Services());			
		}		
		if(service.getServiceDate().before(new Date())){
			redirect.addFlashAttribute("greaterDate", true);
			return new ModelAndView("redirect:/", "service", new Services());			
		}
		if(service.getName() == null){
			redirect.addFlashAttribute("noName", true);
			return new ModelAndView("redirect:/", "service", new Services());			
		}
		if (result.hasErrors()) {
			redirect.addFlashAttribute("error", true);
			return new ModelAndView("redirect:/", "service", new Services());
		}
		
		SystemUser systemUser = systemUserService.findByUsername(principal.getName());
		service.setSystemUser(systemUser);
		service.setRequestDate(new Date());
		service.setStatus("pending");			
		service.setDescription("Sweeping, Mopping Floor.");
		servicesService.saveService(service);
		eventPublisher.publishEvent(new OnNewServiceRequest(systemUser, service));
		redirect.addFlashAttribute("newService", true);
		return new ModelAndView("redirect:/", "service", new Services());
	}
	
	@RequestMapping(value = "/updateService", method = RequestMethod.POST) 
	public ModelAndView updateService(@Valid Services service, Principal principal, Authentication authentication,  BindingResult result, Model model, RedirectAttributes redirect) {
		if(service.getServiceDate() == null){
			redirect.addFlashAttribute("noDate", true);
			return new ModelAndView("redirect:/serviceInfo?id="+service.getId(), "service", new Services());			
		}		
		if(service.getServiceDate().before(new Date())){
			redirect.addFlashAttribute("greaterDate", true);
			return new ModelAndView("redirect:/serviceInfo?id="+service.getId(), "service", new Services());			
		}
		if(service.getName() == null){
			redirect.addFlashAttribute("noName", true);
			return new ModelAndView("redirect:/serviceInfo?id="+service.getId(), "service", new Services());			
		}
		if (result.hasErrors()) {
			redirect.addFlashAttribute("error", true);
			return new ModelAndView("redirect:/serviceInfo?id="+service.getId(), "service", new Services());
		}
		SystemUser systemUser = systemUserService.findByUsername(principal.getName());
		service.setSystemUser(systemUser);
		service.setRequestDate(new Date());
		service.setStatus("pending");			
		service.setDescription("Sweeping, Mopping Floor.");
		servicesService.saveService(service);
		redirect.addFlashAttribute("updateService", true);
		return new ModelAndView("redirect:/serviceInfo?id="+service.getId(), "service", new Services());
	}
	
	@RequestMapping(value = "/deleteService", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("id") Long id, Principal principal, Model model, RedirectAttributes redirect) {
		Services service = servicesService.getOne(id);
		if(service.getStatus().contains("completed")){
			redirect.addFlashAttribute("cannotDelete", true);
			return new ModelAndView("redirect:/", "service", new Services());
		}
		servicesService.delete(service);
		redirect.addFlashAttribute("serviceDeleted", true);
        return new ModelAndView("redirect:/");
    }
	
	
	@RequestMapping(value = "/serviceInfo", method = RequestMethod.GET)
	public ModelAndView serviceInfo(@RequestParam("id") Long id, Model model, Principal principal) {
		SystemUser systemUser = systemUserService.findByUsername(principal.getName());
		model.addAttribute("systemUser", systemUser);
		Services service = servicesService.getOne(id);
		return new ModelAndView("serviceInfo", "service", service);
	}


}
