package br.fk.projeto.controller;

import java.io.File;
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

		model.addAttribute("documentos", documentoService.findAll());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "documentos";
	}

	@RequestMapping(value = "/documento-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";
		model.addAttribute("destinatarios",
				usuarioService.findAll(usuarioService.findByEmail(principal.getName()).getId()));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "documento-register";
	}

	@RequestMapping(value = "/documento-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @RequestParam("file") MultipartFile file,
			@RequestParam String descricao, @RequestParam String tipo, @RequestParam List<Integer> destinatariosId) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		java.sql.Date dtAtual = new java.sql.Date(new java.util.Date().getTime());

		destinatariosId.forEach(destinatario -> {
			Documento documento = new Documento();
			documento.setDestinatario(usuarioService.findOne(destinatario));
			documento.setUrl(criaArquivo(file));
			documento.setDtEnvio(dtAtual);
			documento.setDescricao(descricao);
			documento.setTipo(tipo);
			documentoService.save(documento);
		});

		model.addAttribute("documentos", documentoService.findAll());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "documentos";
	}

	@RequestMapping(value = "/download-documento/{id}")
	public String downloadDocumento(Model model, Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		model.addAttribute("documentos", documentoService.findAll());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		return "documentos";
	}

	public String criaArquivo(MultipartFile file) {
		String caminhoArquivo = "";
		try {
			if (!file.isEmpty()) {
				try {
					String rootPath = "C:/Arquivos";

					File dir = new File(rootPath + File.separator + file.getOriginalFilename());
					if (!dir.exists())
						dir.mkdirs();

					file.transferTo(dir);
					caminhoArquivo = dir.getCanonicalPath();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caminhoArquivo;
	}
}
