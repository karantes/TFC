package br.fk.projeto.controller;

import java.security.Principal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.fk.projeto.entity.Documento;
import br.fk.projeto.service.DocumentoService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class DocumentoController {
	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/documentos", method = RequestMethod.GET)
	public String showDocumentos(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		model.addAttribute("documentos", documentoService.findAll());
		return "documentos";
	}

	@RequestMapping(value = "/documento-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("Documento", new Documento());
		return "documento-register";
	}

	@RequestMapping(value = "/documento-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @ModelAttribute(value = "Documento") Documento documento
	/* @RequestParam List<Integer> destinatariosId */) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		/*
		 * List<Usuario> destinatarios = new ArrayList<>();
		 * destinatariosId.forEach(destinatario -> {
		 * destinatarios.add(usuarioService.findOne(destinatario)); });
		 * documento.setDestinatarios(destinatarios);
		 */
		java.sql.Date dtAtual = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		documento.setDtEnvio(dtAtual);
		documentoService.save(documento);
		model.addAttribute("documentos", documentoService.findAll());
		return "documentos";
	}
}
