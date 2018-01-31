package br.fk.projeto.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.ProjetoService;
import br.fk.projeto.service.SemestreService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class ProjetoController {
	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private SemestreService semestreService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/projetos")
	public String showProjetos(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("projetos", projetoService.findAll());
		return "projetos";
	}

	@RequestMapping(value = "/projeto-detail/{id}", method = RequestMethod.GET)
	public String showProjeto(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("projeto", projetoService.findOne(id));
		return "projeto-detail";
	}

	@RequestMapping(value = "/projeto-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("Projeto", new Projeto());
		model.addAttribute("semestres", semestreService.findAll());
		model.addAttribute("alunos", usuarioService.findAlunos());
		model.addAttribute("orientadores", usuarioService.findOrientadores());
		return "projeto-register";
	}

	@RequestMapping(value = "/projeto-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @ModelAttribute("Projeto") Projeto projeto,
			@RequestParam Integer idSemestre, @RequestParam List<Integer> idAlunos,
			@RequestParam List<Integer> idOrientadores) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		List<Usuario> usuarios = new ArrayList<>();

		projeto.setSemestre(semestreService.findOne(idSemestre));

		idAlunos.forEach(a -> {
			Usuario aluno = usuarioService.findOne(a);
			usuarios.add(aluno);
		});

		idOrientadores.forEach(o -> {
			Usuario orientador = usuarioService.findOne(o);
			usuarios.add(orientador);
		});
		projeto.setUsuarios(usuarios);
		projetoService.save(projeto);

		model.addAttribute("projetos", projetoService.findByAtivoTrue());
		return "projetos";
	}
}
