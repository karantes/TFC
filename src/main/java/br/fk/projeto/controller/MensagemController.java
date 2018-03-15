package br.fk.projeto.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.fk.projeto.entity.Mensagem;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.DocumentoService;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class MensagemController {

	@Autowired
	private MensagemService mensagemService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DocumentoService documentoService;

	@RequestMapping(value = "/mensagens-enviadas")
	public String showMensagensEnviadas(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Usuario user = usuarioService.findByEmail(principal.getName());

		model.addAttribute("mensagens", mensagemService.findEnviadas(principal.getName()));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));

		return "mensagens";
	}

	@RequestMapping(value = "/mensagens-recebidas")
	public String showMensagensRecebidas(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Usuario user = usuarioService.findByEmail(principal.getName());
		model.addAttribute("mensagens", mensagemService.findRecebidas(principal.getName()));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));
		return "mensagens";
	}

	@RequestMapping(value = "/mensagem-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Usuario user = usuarioService.findByEmail(principal.getName());
		model.addAttribute("destinatarios",
				usuarioService.findAll(usuarioService.findByEmail(principal.getName()).getId()));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", usuarioService.findByEmail(principal.getName()));
		model.addAttribute("documents", user);
		return "mensagem-register";
	}

	@RequestMapping(value = "/mensagem-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @RequestParam(defaultValue = "") String assunto,
			@RequestParam(defaultValue = "") String mensagem, @RequestParam(defaultValue = "") String tipo,
			@RequestParam List<Integer> destinatariosId) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Usuario remetente = usuarioService.findByEmail(principal.getName());

		destinatariosId.forEach(destinatario -> {
			Mensagem msg = new Mensagem();

			msg.setDestinatario(usuarioService.findOne(destinatario));
			msg.setRemetente(remetente);
			msg.setDtEnvio(new java.sql.Date(new java.util.Date().getTime()));
			msg.setTipo(tipo);
			msg.setMensagem(mensagem.replace("\n", "<br>"));
			msg.setAssunto(assunto);
			msg.setStatus("NOVA");

			mensagemService.save(msg);
		});

		return "redirect:/mensagens-recebidas.html";
	}

	@RequestMapping(value = "/mensagem-detail/{id}")
	public String showMensagem(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Mensagem mensagem = mensagemService.findOne(id);
		Usuario user = usuarioService.findByEmail(principal.getName());
		if (!mensagem.getRemetente().getEmail().equals(principal.getName())
				&& !mensagem.getDestinatario().getEmail().equals(principal.getName()))
			return "redirect:/mensagens-recebidas.html";

		if (mensagem.getStatus().equals("NOVA") && !mensagem.getRemetente().getEmail().equals(principal.getName())) {
			mensagem.setStatus("LIDA");
			mensagemService.save(mensagem);
		}
		model.addAttribute("mensagem", mensagem);
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));
		return "mensagem-detail";
	}
}
