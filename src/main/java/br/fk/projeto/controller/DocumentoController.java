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
import org.springframework.web.multipart.MultipartFile;

import br.fk.projeto.entity.Documento;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.DocumentoService;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class DocumentoController {
	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private MensagemService mensagemService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/documentos", method = RequestMethod.GET)
	public String showDocumentos(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Usuario user = usuarioService.findByEmail(principal.getName());

		List<Documento> documentos = documentoService.findAll();

		documentos.forEach(documento -> {
			if (documento.getDestinatario().getId() == user.getId() && documento.getStatus().equals("NOVO")) {
				documento.setStatus("LIDO");
			}
		});

		documentoService.saveAll(documentos);

		if (user.getTipoUsuario().equals(1)) {
			model.addAttribute("documentos", documentos);
		} else {
			model.addAttribute("documentos", documentoService.findByRemetenteOrDestinatario(user));
		}
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));
		model.addAttribute("user", user);
		return "documentos";
	}

	@RequestMapping(value = "/documento-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Usuario user = usuarioService.findByEmail(principal.getName());
		model.addAttribute("destinatarios",
				usuarioService.findAll(usuarioService.findByEmail(principal.getName()).getId()));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findByDestinatarioAndStatus(user));
		return "documento-register";
	}

	@RequestMapping(value = "/documento-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @RequestParam("file") MultipartFile file,
			@RequestParam String descricao, @RequestParam String tipo, @RequestParam List<Integer> destinatariosId) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		java.sql.Date dtAtual = new java.sql.Date(new java.util.Date().getTime());
		Usuario user = usuarioService.findByEmail(principal.getName());
		destinatariosId.forEach(destinatario -> {
			Documento documento = new Documento();
			documento.setRemetente(user);
			documento.setDestinatario(usuarioService.findOne(destinatario));
			documento.setUrl(file.getOriginalFilename());// Salvar no servidor
			documento.setDtEnvio(dtAtual);
			documento.setDescricao(descricao);
			documento.setTipo(tipo);
			documento.setStatus("NOVO");
			documentoService.save(documento);
		});

		return "redirect:/documentos.html";
	}

	@RequestMapping(value = "/delete-documento/{id}")
	public String deleteDocumento(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		documentoService.delete(id);
		return "redirect:/documentos.html";
	}

	@RequestMapping(value = "/update-documento/{id}")
	public String updateDocumento(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		Documento documento = documentoService.findOne(id);
		documento.setStatus("CONFIRMADO");
		documentoService.save(documento);
		return "redirect:/documentos.html";
	}
}
