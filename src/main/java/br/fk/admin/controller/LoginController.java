package br.fk.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(Model model) {
		return "home";
	}

	@RequestMapping("/error-400")
	public String error400() {
		return "error-400";
	}

	@RequestMapping("/error-403")
	public String error403() {
		return "error-403";
	}

	@RequestMapping("/error-404")
	public String error404() {
		return "error-404";
	}
}
