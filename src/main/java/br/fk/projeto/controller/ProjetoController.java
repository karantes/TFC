package br.fk.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.fk.projeto.entity.Projeto;
import br.fk.projeto.service.ProjetoService;

@Controller
public class ProjetoController {
	@Autowired
	private ProjetoService projetoService;

	@RequestMapping("/projetos")
	public String showProjetos(Model model) {
		model.addAttribute("projetos", projetoService.findAll());
		return "projetos";
	}

	@RequestMapping(value = "/projeto-detail", method = RequestMethod.GET)
	public String showProjeto(Model model) {
		return "projeto-detail";
	}

	@RequestMapping(value = "/projeto-detail/{id}", method = RequestMethod.GET)
	public String showProjeto(Model model, @PathVariable Integer id) {
		model.addAttribute("projeto", projetoService.findOne(id));
		return "projeto-detail";
	}

	@RequestMapping(value = "/projeto-register", method = RequestMethod.GET)
	public String showRegister(Model model) {
		model.addAttribute("Projeto", new Projeto());
		return "projeto-register";
	}

	@RequestMapping(value = "/projeto-register", method = RequestMethod.POST)
	public String doRegister(Model model, @ModelAttribute("Projeto") Projeto projeto) {
		projetoService.save(projeto);
		model.addAttribute("projetos", projetoService.findAll());
		return "projetos";
	}
}
