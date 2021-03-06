package br.fk.projeto.controller.rest;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.UsuarioService;

@RestController
public class UsuarioRestController {
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/rest/login", produces = MediaType.ALL_VALUE)
	public String login(@RequestParam String email, @RequestParam String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Gson gson = new Gson();
		Usuario user = usuarioService.findByEmail(email);
		if (encoder.matches(senha, user.getSenha()))
			return gson.toJson(user);
		return "";

	}

	@RequestMapping(value = "/rest/usuarios", produces = MediaType.ALL_VALUE)
	public String showUsuarios(@RequestParam String email) {
		Gson gson = new Gson();
		Usuario user = usuarioService.findByEmail(email);
		if (!user.getTipoUsuario().equals(1))
			return gson.toJson(user);
		return gson.toJson(usuarioService.findAll());
	}

	@RequestMapping(value = "/rest/alunos", produces = MediaType.ALL_VALUE)
	public String showAlunos(@RequestParam String email) {
		Gson gson = new Gson();
		Usuario user = usuarioService.findByEmail(email);
		if (!user.getTipoUsuario().equals(1))
			return gson.toJson(user);
		return gson.toJson(usuarioService.findAlunos());
	}

	@RequestMapping(value = "/rest/orientadores", produces = MediaType.ALL_VALUE)
	public String showOrientadores(@RequestParam String email) {
		Gson gson = new Gson();
		Usuario user = usuarioService.findByEmail(email);
		if (!user.getTipoUsuario().equals(1))
			return gson.toJson(user);
		return gson.toJson(usuarioService.findOrientadores());
	}

	@RequestMapping(value = "/rest/usuarios-filtros", produces = MediaType.ALL_VALUE)
	public String showUsuariosFiltros(@RequestParam String email) {
		Gson gson = new Gson();
		Usuario user = usuarioService.findByEmail(email);
		return gson.toJson(usuarioService.findAll(user.getId()));
	}

	@RequestMapping(value = "/rest/usuario-detail/{id}", produces = MediaType.ALL_VALUE)
	public String showUsuario(@RequestParam String email, @PathVariable Integer id) {
		Gson gson = new Gson();
		return gson.toJson(usuarioService.findOne(id));
	}

	@RequestMapping(value = "/rest/usuario-register", produces = MediaType.ALL_VALUE)
	public void doRegister(@RequestParam String mail, @ModelAttribute Usuario usuario) {
		try {
			Usuario user = usuarioService.findByEmail(mail);
			if (!user.getTipoUsuario().equals(1))
				return;
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			usuario.setSenha(encoder.encode(usuario.getSenha()));

			usuario.setDtCadastro(new Date(Calendar.getInstance().getTimeInMillis()));
			usuarioService.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/rest/usuario-update", produces = MediaType.ALL_VALUE)
	public void doUpdate(@RequestParam Integer id, @RequestParam String nome, @RequestParam String email,
			@RequestParam Boolean ativo, @RequestParam Integer tipoUsuario,
			@RequestParam(defaultValue = "") String password) {
		try {
			Usuario usuario = usuarioService.findOne(id);
			if (!password.equals("") && password != null) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				usuario.setSenha(encoder.encode(password));
			} else {
				usuario.setSenha(usuario.getSenha());
			}
			usuario.setNome(nome);
			usuario.setAtivo(ativo);
			usuario.setEmail(email);
			usuario.setTipoUsuario(tipoUsuario);
			usuario.setDtAlteracao(new Date(Calendar.getInstance().getTimeInMillis()));
			usuarioService.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
