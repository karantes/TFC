package br.fk.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fk.admin.entity.AppConfiguration;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("appName", AppConfiguration.getAppName());
		return "home";
	}

}
