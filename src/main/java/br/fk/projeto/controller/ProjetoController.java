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
import br.fk.projeto.service.DocumentoService;
import br.fk.projeto.service.MensagemService;
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

	@Autowired
	private MensagemService mensagemService;

	@Autowired
	private DocumentoService documentoService;

	@RequestMapping(value = "/projetos")
	public String showProjetos(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Usuario user = usuarioService.findByEmail(principal.getName());
		if (user.getTipoUsuario().equals(1)) {
			model.addAttribute("projetos", projetoService.findAll());
		} else {
			model.addAttribute("projetos", projetoService.findByUsuarios(user));
		}
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));
		return "projetos";
	}

	@RequestMapping(value = "/projeto-detail/{id}", method = RequestMethod.GET)
	public String showProjeto(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Projeto projeto = projetoService.findOne(id);
		Usuario user = usuarioService.findByEmail(principal.getName());
		if (!user.getTipoUsuario().equals(1) && !projeto.getUsuarios().contains(user))
			return "redirect:/projetos.html";

		model.addAttribute("projeto", projeto);
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));
		return "projeto-detail";
	}

	@RequestMapping(value = "/desativar-projeto/{id}")
	public String disableProjeto(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Usuario user = usuarioService.findByEmail(principal.getName());
		if (!user.getTipoUsuario().equals(1))
			return "redirect:/projetos.html";

		Projeto projeto = projetoService.findOne(id);
		projeto.setAtivo(false);

		projetoService.save(projeto);

		return "redirect:/projetos.html";
	}

	@RequestMapping(value = "/ativar-projeto/{id}")
	public String enableProjeto(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Usuario user = usuarioService.findByEmail(principal.getName());
		if (!user.getTipoUsuario().equals(1))
			return "redirect:/projetos.html";

		Projeto projeto = projetoService.findOne(id);
		projeto.setAtivo(true);

		projetoService.save(projeto);

		return "redirect:/projetos.html";
	}

	@RequestMapping(value = "/projeto-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Usuario user = usuarioService.findByEmail(principal.getName());
		if (user.getTipoUsuario().equals(1)) {
			model.addAttribute("Projeto", new Projeto());
			model.addAttribute("semestres", semestreService.findAll());
			model.addAttribute("alunos", usuarioService.findAlunos());
			model.addAttribute("orientadores", usuarioService.findOrientadores());
			model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
			model.addAttribute("user", user);
			model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));
		}
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

		return "redirect:/projetos.html";
	}
}
