package br.fk.projeto.controller;

import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MensagemService mensagemService;

	@RequestMapping("/usuarios")
	public String showUsuarios(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("usuarios", usuarioService.findAll());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "usuarios";
	}

	@RequestMapping(value = "/usuario-detail", method = RequestMethod.GET)
	public String showUsuario(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "usuario-detail";
	}

	@RequestMapping(value = "/usuario-detail/{id}", method = RequestMethod.GET)
	public String showUsuario(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("usuario", usuarioService.findOne(id));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "usuario-detail";
	}

	@RequestMapping(value = "/usuario-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		// if (principal == null)
		// return "redirect:/login.html?authenticate=false";
		model.addAttribute("Usuario", new Usuario());
//		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "usuario-register";
	}

	@RequestMapping(value = "/usuario-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @ModelAttribute("Usuario") Usuario usuario) {
		// if (principal == null)
		// return "redirect:/login.html?authenticate=false";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(usuario.getSenha()));

		usuario.setDtCadastro(new Date(new java.util.Date().getTime()));
		usuarioService.save(usuario);

		model.addAttribute("usuarios", usuarioService.findAll());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "redirect:/usuarios.html";
	}
}
