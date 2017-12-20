package br.fk.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.fk.projeto.entity.Semestre;
import br.fk.projeto.service.SemestreService;

@Controller
public class SemestreController {
	@Autowired
	private SemestreService semestreService;

	@RequestMapping("/semestres")
	public String showSemestres(Model model) {
		model.addAttribute("semestres", semestreService.findAll());
		return "semestres";
	}

	@RequestMapping(value = "/semestre-detail", method = RequestMethod.GET)
	public String showSemestre(Model model) {
		return "semestre-detail";
	}

	@RequestMapping(value = "/semestre-detail/{id}", method = RequestMethod.GET)
	public String showSemestre(Model model, @PathVariable Integer id) {
		model.addAttribute("semestre", semestreService.findOne(id));
		return "semestre-detail";
	}

	@RequestMapping(value = "/semestre-register", method = RequestMethod.GET)
	public String showRegister(Model model) {
		model.addAttribute("Semestre", new Semestre());
		return "semestre-register";
	}

	@RequestMapping(value = "/semestre-register", method = RequestMethod.POST)
	public String doRegister(Model model, @ModelAttribute("Semestre") Semestre semestre) {
		semestreService.save(semestre);
		model.addAttribute("semestres", semestreService.findAll());
		return "semestres";
	}
}
