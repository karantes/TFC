package br.fk.projeto.controller.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import br.fk.projeto.entity.Documento;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.DocumentoService;
import br.fk.projeto.service.UsuarioService;

@RestController
public class DocumentoRestController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DocumentoService documentoService;

	@RequestMapping(value = "/rest/documentos", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showDocumentos(@RequestParam String email) {
		Usuario user = usuarioService.findByEmail(email);
		List<Documento> documentos = documentoService.findAll();
		Gson gson = new Gson();
		documentos.forEach(documento -> {
			if (documento.getDestinatario().getId() == user.getId() && documento.getStatus().equals("NOVO")) {
				documento.setStatus("LIDO");
			}
		});
		documentoService.saveAll(documentos);
		if (user.getTipoUsuario().equals(1)) {
			return gson.toJson(documentos);
		} else {
			return gson.toJson(documentoService.findByRemetenteOrDestinatario(user));
		}
	}

	@RequestMapping(value = "/rest/documento-register", produces = MediaType.ALL_VALUE)
	public void doRegister(@RequestParam String email, @RequestParam("file") MultipartFile file,
			@RequestParam String descricao, @RequestParam String tipo, @RequestParam Integer destinatarioId) {
		java.sql.Date dtAtual = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		Usuario user = usuarioService.findByEmail(email);

		String arquivo = criaArquivo(file);

		Documento documento = new Documento();
		documento.setRemetente(user);
		documento.setDestinatario(usuarioService.findOne(destinatarioId));
		documento.setUrl(arquivo);// Salvar no servidor
		documento.setDtEnvio(dtAtual);
		documento.setDescricao(descricao);
		documento.setTipo(tipo);
		documento.setStatus("NOVO");
		documentoService.save(documento);
	}

	@RequestMapping(value = "/rest/delete-documento/{id}", produces = MediaType.ALL_VALUE)
	public void deleteDocumento(@PathVariable Integer id, @RequestParam String email) {
		documentoService.delete(id);
	}

	@RequestMapping(value = "/rest/update-documento/{id}", produces = MediaType.ALL_VALUE)
	public void updateDocumento(@RequestParam String email, @PathVariable Integer id) {
		Documento documento = documentoService.findOne(id);
		documento.setStatus("CONFIRMADO");
		documentoService.save(documento);
	}

	@RequestMapping(value = "/rest/download-documento/{id}")
	public @ResponseBody String downloadDocumento(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer id) {
		Documento documento = documentoService.findOne(id);
		downloadArquivo(request, response, new File(documento.getUrl()));
		Gson gson = new Gson();
		return gson.toJson(documento);
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
			e.printStackTrace();
		}
	}
}
