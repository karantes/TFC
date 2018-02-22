package br.fk.projeto.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.fk.projeto.entity.Evento;
import br.fk.projeto.service.EventoService;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MensagemService mensagemService;

	@RequestMapping("/eventos")
	public String showEventos(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("eventos",
				eventoService.findByParticipante(usuarioService.findByEmail(principal.getName())));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "eventos";
	}

	@RequestMapping(value = "/evento-detail", method = RequestMethod.GET)
	public String showEvento(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "evento-detail";
	}

	@RequestMapping(value = "/evento-detail/{id}", method = RequestMethod.GET)
	public String showEvento(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("Evento", eventoService.findOne(id));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "evento-detail";
	}

	@RequestMapping(value = "/evento-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		model.addAttribute("participantes",
				usuarioService.findAll(usuarioService.findByEmail(principal.getName()).getId()));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "evento-register";
	}

	@RequestMapping(value = "/evento-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @RequestParam(defaultValue = "") String nome,
			@RequestParam(defaultValue = "") String descricao, @RequestParam(defaultValue = "") Date dtEvento,
			@RequestParam(defaultValue = "") String local, @RequestParam(defaultValue = "") Boolean ativo,
			@RequestParam List<Integer> participantesId) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		participantesId.forEach(participante -> {
			Evento evento = new Evento();

			evento.setParticipante(usuarioService.findOne(participante));
			evento.setAtivo(ativo);
			evento.setDescricao(descricao);
			evento.setDtEvento(dtEvento);
			evento.setLocal(local);
			evento.setNome(nome);

			usuarioService.findByEmail(principal.getName());
			eventoService.save(evento);
		});

		model.addAttribute("eventos", eventoService.findAll());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "eventos";
	}
}
