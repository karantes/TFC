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

	@RequestMapping(value = "/rest/usuarios", produces = MediaType.ALL_VALUE)
	public String showUsuarios(@RequestParam String email) {
		Gson gson = new Gson();
		Usuario user = usuarioService.findByEmail(email);
		if (!user.getTipoUsuario().equals(1))
			return "";
		return gson.toJson(usuarioService.findAll());
	}

	@RequestMapping(value = "/rest/usuario-detail/{id}", produces = MediaType.ALL_VALUE)
	public String showUsuario(@RequestParam String email, @PathVariable Integer id) {
		Gson gson = new Gson();
		return gson.toJson(usuarioService.findOne(id));
	}

	@RequestMapping(value = "/rest/usuario-register", produces = MediaType.ALL_VALUE)
	public void doRegister(@RequestParam String email, @ModelAttribute("Usuario") Usuario usuario) {
		try {
			Usuario user = usuarioService.findByEmail(email);
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
	public void doUpdate(@RequestParam String email, @ModelAttribute("usuario") Usuario usuario,
			@RequestParam(defaultValue = "") String password) {
		try {
			if (!password.equals("") && password != null) {
				System.out.println('"' + password + '"');
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				usuario.setSenha(encoder.encode(password));
			} else {
				usuario.setSenha(usuario.getSenha());
			}
			usuario.setDtAlteracao(new Date(Calendar.getInstance().getTimeInMillis()));
			usuarioService.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
