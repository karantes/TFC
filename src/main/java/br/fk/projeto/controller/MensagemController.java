package br.fk.projeto.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.fk.projeto.entity.Mensagem;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class MensagemController {

	@Autowired
	private MensagemService mensagemService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/mensagens")
	public String showMensagens(Model model) {
		model.addAttribute("mensagens", mensagemService.findAll());
		return "mensagens";
	}

	@RequestMapping(value = "/mensagem-register", method = RequestMethod.GET)
	public String showRegister(Model model) {
		model.addAttribute("Mensagem", new Mensagem());
		return "mensagem-register";
	}

	@RequestMapping(value = "/mensagem-register", method = RequestMethod.POST)
	public String doRegister(Model model, @ModelAttribute("Mensagem") Mensagem mensagem/*,
			@RequestParam Integer remetenteId, @RequestParam List<Integer> destinatariosId*/) {

//		Usuario remetente = usuarioService.findOne(remetenteId);

//		mensagem.setRementente(remetente);
//		List<Usuario> destinatarios = new ArrayList<>();
//		destinatariosId.forEach(destinatario -> {
//			destinatarios.add(usuarioService.findOne(destinatario));
//		});
//		mensagem.setDestinatarios(destinatarios);
		java.sql.Date dtAtual = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		mensagem.setDtEnvio(dtAtual);
		mensagemService.save(mensagem);
		model.addAttribute("mensagens", mensagemService.findAll());
		return "mensagens";
	}

	@RequestMapping(value = "/mensagem-detail/{id}")
	public String showMensagem(Model model, @PathVariable Integer id) {
		model.addAttribute("mensagem", mensagemService.findOne(id));
		return "mensagem-detail";
	}
}
