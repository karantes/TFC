package br.fk.projeto.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.ProjetoService;
import br.fk.projeto.service.SemestreService;
import br.fk.projeto.service.UsuarioService;

@RestController
public class ProjetoRestController {
	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private SemestreService semestreService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/rest/projetos", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showProjetos(@RequestParam String email) {
		Gson gson = new Gson();
		Usuario user = usuarioService.findByEmail(email);
		if (user.getTipoUsuario().equals(1)) {
			return gson.toJson(projetoService.findAll());
		} else {
			return gson.toJson(projetoService.findByUsuarios(user));
		}
	}

	@RequestMapping(value = "/rest/projeto-detail/{id}", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showProjeto(@RequestParam String email, @PathVariable Integer id) {
		Gson gson = new Gson();
		Projeto projeto = projetoService.findOne(id);
		return gson.toJson(projeto);
	}

	@RequestMapping(value = "/rest/desativar-projeto/{id}", produces = MediaType.ALL_VALUE)
	public void disableProjeto(@RequestParam String email, @PathVariable Integer id) {
		Usuario user = usuarioService.findByEmail(email);
		if (!user.getTipoUsuario().equals(1))
			return;

		Projeto projeto = projetoService.findOne(id);
		projeto.setAtivo(false);

		projetoService.save(projeto);
	}

	@RequestMapping(value = "/rest/ativar-projeto/{id}", produces = MediaType.ALL_VALUE)
	public void enableProjeto(@RequestParam String email, @PathVariable Integer id) {

		Usuario user = usuarioService.findByEmail(email);
		if (!user.getTipoUsuario().equals(1))
			return;

		Projeto projeto = projetoService.findOne(id);
		projeto.setAtivo(true);

		projetoService.save(projeto);
	}

	@RequestMapping(value = "/rest/projeto-register", produces = MediaType.ALL_VALUE)
	public void doRegister(@RequestParam String email, @ModelAttribute Projeto projeto,
			@RequestParam Integer idSemestre, @RequestParam List<Integer> idAlunos,
			@RequestParam List<Integer> idOrientadores) {
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
	}
}
