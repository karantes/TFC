package br.fk.admin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fk.admin.entity.AppConfiguration;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class HomeController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MensagemService mensagemService;

	@RequestMapping("/home")
	public String home(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("appName", AppConfiguration.getAppName());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", usuarioService.findByEmail(principal.getName()));
		return "home";
	}

}
