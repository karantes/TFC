package br.fk.projeto.controller;

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

import br.fk.projeto.entity.Evento;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.EventoService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/eventos")
	public String showEventos(Model model) {
		model.addAttribute("eventos", eventoService.findAll());
		return "eventos";
	}

	@RequestMapping(value = "/evento-detail", method = RequestMethod.GET)
	public String showEvento(Model model) {
		return "evento-detail";
	}

	@RequestMapping(value = "/evento-detail/{id}", method = RequestMethod.GET)
	public String showEvento(Model model, @PathVariable Integer id) {
		model.addAttribute("Evento", eventoService.findOne(id));
		return "evento-detail";
	}

	@RequestMapping(value = "/evento-register", method = RequestMethod.GET)
	public String showRegister(Model model) {
		model.addAttribute("Evento", new Evento());
		model.addAttribute("participantes", usuarioService.findAll());
		return "evento-register";
	}

	@RequestMapping(value = "/evento-register", method = RequestMethod.POST)
	public String doRegister(Model model, @ModelAttribute("Evento") Evento evento,
			@RequestParam List<Integer> participantesId) {
		List<Usuario> usuarios = new ArrayList<>();

		participantesId.forEach(participante -> {
			usuarios.add(usuarioService.findOne(participante));
		});
		eventoService.save(evento);
		evento.setParticipantes(usuarios);
		eventoService.save(evento);

		model.addAttribute("eventos", eventoService.findAll());

		return "eventos";
	}
}
