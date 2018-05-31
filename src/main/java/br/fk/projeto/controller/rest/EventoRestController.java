package br.fk.projeto.controller.rest;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.fk.projeto.entity.Evento;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.EventoService;
import br.fk.projeto.service.UsuarioService;

@RestController
public class EventoRestController {
	@Autowired
	private EventoService eventoService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/rest/eventos", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showEventos(@RequestParam String email) {

		Usuario user = usuarioService.findByEmail(email);
		Gson gson = new Gson();
		if (user.getTipoUsuario().equals(1)) {
			return gson.toJson(eventoService.findAll());
		} else {
			return gson.toJson(eventoService.findByParticipante(usuarioService.findByEmail(email)));
		}
	}

	@RequestMapping(value = "/rest/evento-detail/{id}", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showEvento(@RequestParam String email, @PathVariable Integer id) {
		Evento evento = eventoService.findOne(id);
		Usuario user = usuarioService.findByEmail(email);

		if (evento.getStatus().equals("NOVO") && evento.getParticipante().equals(user)) {
			evento.setStatus("LIDO");
			eventoService.save(evento);
		}
		Gson gson = new Gson();
		return gson.toJson(evento);
	}

	@RequestMapping(value = "/rest/evento-register", produces = MediaType.ALL_VALUE)
	public @ResponseBody String doRegister(@RequestParam String email, @RequestParam(defaultValue = "") String nome,
			@RequestParam(defaultValue = "") String descricao, @RequestParam(defaultValue = "") Date dtEvento,
			@RequestParam(defaultValue = "") String local, @RequestParam Integer participanteId) {

		Evento evento = new Evento();
		evento.setParticipante(usuarioService.findOne(participanteId));
		evento.setDescricao(descricao);
		evento.setDtEvento(dtEvento);
		evento.setLocal(local);
		evento.setNome(nome);
		evento.setStatus("NOVO");

		usuarioService.findByEmail(email);
		eventoService.save(evento);
		Usuario user = usuarioService.findByEmail(email);
		Gson gson = new Gson();
		if (user.getTipoUsuario().equals(1)) {
			return gson.toJson(eventoService.findAll());
		} else {
			return gson.toJson(eventoService.findByParticipante(usuarioService.findByEmail(email)));
		}
	}
}
