package br.fk.projeto.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.fk.projeto.entity.Frequencia;
import br.fk.projeto.service.FrequenciaService;

@Controller
public class FrequenciaController {
	@Autowired
	private FrequenciaService frequenciaService;

	@RequestMapping(value = "/frequencias")
	public String showFrequencias(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("frequencias", frequenciaService.findAll());
		return "frequencias";
	}

	@RequestMapping(value = "/frequencia/{id}")
	public String showFrequencia(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("frequencias", frequenciaService.findOne(id));
		return "frequencia-detail";
	}

	@RequestMapping(value = "frequencia-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("Frequencia", new Frequencia());
		return "frequencia-register";
	}

	@RequestMapping(value = "frequencia-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @ModelAttribute("Frequencia") Frequencia frequencia) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		return "frequencia-register";
	}
}
