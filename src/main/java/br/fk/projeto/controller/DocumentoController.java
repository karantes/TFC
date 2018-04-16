package br.fk.projeto.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import br.fk.projeto.service.EventoService;
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

	@Autowired
	private EventoService eventoService;

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
		model.addAttribute("documents", documentoService.findNovosByDestinatario(user));
		model.addAttribute("events", eventoService.findNovosByParticipante(user));
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
		model.addAttribute("documents", documentoService.findNovosByDestinatario(user));
		model.addAttribute("events", eventoService.findNovosByParticipante(user));
		return "documento-register";
	}

	@RequestMapping(value = "/documento-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @RequestParam("file") MultipartFile file,
			@RequestParam String descricao, @RequestParam String tipo, @RequestParam List<Integer> destinatariosId) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		java.sql.Date dtAtual = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		Usuario user = usuarioService.findByEmail(principal.getName());

		String arquivo = criaArquivo(file);

		destinatariosId.forEach(destinatario -> {
			Documento documento = new Documento();
			documento.setRemetente(user);
			documento.setDestinatario(usuarioService.findOne(destinatario));
			documento.setUrl(arquivo);// Salvar no servidor
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

	@RequestMapping(value = "/download-documento/{id}")
	public String downloadDocumento(HttpServletRequest request, HttpServletResponse response, Model model,
			Principal principal, @PathVariable Integer id) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Documento documento = documentoService.findOne(id);

		downloadArquivo(request, response, new File(documento.getUrl()));

		return "redirect:/documentos.html";
	}

	private void downloadArquivo(HttpServletRequest request, HttpServletResponse response, File downloadFile) {
		try {
			FileInputStream inputStream = new FileInputStream(downloadFile);
			OutputStream outStream = response.getOutputStream();

			String mimeType = "application/octet-stream";

			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		} catch (IOException e) {
		}
	}

	public String criaArquivo(MultipartFile file) {
		String caminhoArquivo = "";
		try {
			if (!file.isEmpty()) {
				try {
					String rootPath = "c:/Arquivos tfc/";
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
