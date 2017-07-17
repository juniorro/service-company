package com.juniorro.servicecompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = { "/index", "/", "/login" })
	public String index() {
		return "home";
	}

	@RequestMapping(value = "/register")
	public String register() {
		return "create";
	}

	@RequestMapping(value = "/forgotPassword")
	public String reset() {
		return "reset";
	}

	@RequestMapping(value = "/accounts")
	public String home() {
		return "accounts";
	}

}
