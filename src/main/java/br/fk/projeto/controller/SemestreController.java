package br.fk.projeto.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.fk.projeto.entity.Semestre;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.DocumentoService;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.SemestreService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class SemestreController {
	@Autowired
	private SemestreService semestreService;

	@Autowired
	private MensagemService mensagemService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DocumentoService documentoService;

	@RequestMapping("/semestres")
	public String showSemestres(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Usuario user = usuarioService.findByEmail(principal.getName());
		if (!user.getTipoUsuario().equals(1))
			return "redirect:/projetos.html";
		model.addAttribute("semestres", semestreService.findAll());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));

		return "semestres";
	}

	@RequestMapping(value = "/semestre-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Usuario user = usuarioService.findByEmail(principal.getName());
		if (!user.getTipoUsuario().equals(1))
			return "redirect:/projetos.html";
		model.addAttribute("Semestre", new Semestre());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));
		return "semestre-register";
	}

	@RequestMapping(value = "/semestre-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @ModelAttribute("Semestre") Semestre semestre) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Usuario user = usuarioService.findByEmail(principal.getName());
		if (!user.getTipoUsuario().equals(1))
			return "redirect:/projetos.html";
		semestreService.save(semestre);
		return "redirect:/semestres.html";
	}
}
