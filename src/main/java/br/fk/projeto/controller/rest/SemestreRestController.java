package br.fk.projeto.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.fk.projeto.entity.Semestre;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.SemestreService;
import br.fk.projeto.service.UsuarioService;

@RestController
public class SemestreRestController {
	@Autowired
	private SemestreService semestreService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/rest/semestres", produces = MediaType.ALL_VALUE)
	public String showSemestres(@RequestParam String email) {
		Gson gson = new Gson();
		Usuario user = usuarioService.findByEmail(email);
		if (!user.getTipoUsuario().equals(1))
			return "";
		return gson.toJson(semestreService.findAll());
	}

	@RequestMapping(value = "/rest/semestre-register", produces = MediaType.ALL_VALUE)
	public void doRegister(@RequestParam String email, @ModelAttribute Semestre semestre) {
		Usuario user = usuarioService.findByEmail(email);
		if (!user.getTipoUsuario().equals(1))
			return;
		semestreService.save(semestre);
	}
}
