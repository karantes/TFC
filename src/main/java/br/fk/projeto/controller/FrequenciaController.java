package br.fk.projeto.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.fk.projeto.entity.Frequencia;
import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.DocumentoService;
import br.fk.projeto.service.FrequenciaService;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.ProjetoService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class FrequenciaController {
	@Autowired
	private FrequenciaService frequenciaService;

	@Autowired
	private MensagemService mensagemService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private DocumentoService documentoService;

	@RequestMapping(value = "/frequencias")
	public String showFrequencias(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Usuario user = usuarioService.findByEmail(principal.getName());

		if (user.getTipoUsuario().equals(3)) {
			model.addAttribute("frequencias", frequenciaService.findByAluno(user));
		} else if (user.getTipoUsuario().equals(2)) {
			model.addAttribute("frequencias", frequenciaService.findByOrientador(user));
		} else {
			model.addAttribute("frequencias", frequenciaService.findAll());
		}

		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));

		return "frequencias";
	}

	@RequestMapping(value = "frequencia-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Usuario user = usuarioService.findByEmail(principal.getName());
		if (!user.getTipoUsuario().equals(1))
			return "redirect:/frequencias.html";

		model.addAttribute("frequencia", new Frequencia());
		model.addAttribute("projetos", projetoService.findByAtivoTrue());
		model.addAttribute("alunos", usuarioService.findAlunos());
		model.addAttribute("orientadores", usuarioService.findOrientadores());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));

		return "frequencia-register";
	}

	@RequestMapping(value = "frequencia-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @RequestParam Date dtProposta,
			@RequestParam Integer projetoId, @RequestParam Integer nrFrequencias) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Projeto projeto = projetoService.findOne(projetoId);
		Usuario orientador = null;

		for (Usuario usuario : projeto.getUsuarios()) {
			if (usuario.getTipoUsuario().equals(2)) {
				orientador = usuario;
			}
		}

		Calendar c = Calendar.getInstance();
		c.setTime(dtProposta);

		for (int i = 0; i < nrFrequencias; i++) {
			Date dataFrequencia = new Date(c.getTimeInMillis());
			for (Usuario usuario : projeto.getUsuarios()) {
				if (usuario.getTipoUsuario().equals(3)) {
					Frequencia frequencia = new Frequencia();
					frequencia.setDtProposta(dataFrequencia);
					frequencia.setOrientador(orientador);
					frequencia.setProjeto(projeto);
					frequencia.setAluno(usuario);

					frequenciaService.save(frequencia);
				}
			}
			c.add(Calendar.DAY_OF_MONTH, 7);
		}

		return "redirect:/frequencias.html";
	}

	@RequestMapping(value = "/frequencia/{id}")
	public String updateFrequencia(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Usuario user = usuarioService.findByEmail(principal.getName());
		Frequencia frequencia = frequenciaService.findOne(id);

		model.addAttribute("frequencias", frequencia);
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));

		return "frequencia-detail";
	}

}
