package br.fk.projeto.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.fk.projeto.entity.Frequencia;
import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.DocumentoService;
import br.fk.projeto.service.EventoService;
import br.fk.projeto.service.FrequenciaService;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.ProjetoService;
import br.fk.projeto.service.UsuarioService;

@Controller
public class FrequenciaController {
	@Autowired
	private FrequenciaService frequenciaService;

	@Autowired
	private MensagemService mensagemService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private EventoService eventoService;

	@RequestMapping(value = "/frequencias", method = RequestMethod.GET)
	public String showFrequencias(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);

		model.addAttribute("dtInicial", new Date(c.getTimeInMillis()));
		model.addAttribute("dtFinal", new Date(Calendar.getInstance().getTimeInMillis()));

		Usuario user = usuarioService.findByEmail(principal.getName());
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findNovosByDestinatario(user));
		model.addAttribute("events", eventoService.findNovosByParticipante(user));

		if (user.getTipoUsuario().equals(1)) {
			model.addAttribute("projetos", projetoService.findAll());
		} else {
			model.addAttribute("projetos", projetoService.findByUsuarios(user));
		}

		return "frequencias";
	}

	@RequestMapping(value = "/frequencias", method = RequestMethod.POST)
	public String showFrequencias(Model model, Principal principal, @RequestParam Date dtInicial,
			@RequestParam Date dtFinal, @RequestParam(required = false) Integer projetoId) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		if (dtInicial == null)
			dtInicial = new Date(Calendar.getInstance().getTimeInMillis());
		if (dtFinal == null)
			dtFinal = new Date(Calendar.getInstance().getTimeInMillis());

		Usuario user = usuarioService.findByEmail(principal.getName());

		Projeto projeto = projetoService.findOne(projetoId);
		model.addAttribute("frequencias",
				frequenciaService.findByProjetoAndDtFrequenciaBetween(projeto, dtInicial, dtFinal));

		model.addAttribute("dtInicial", dtInicial);
		model.addAttribute("dtFinal", dtFinal);
		model.addAttribute("projetoId", projetoId);
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findNovosByDestinatario(user));
		model.addAttribute("events", eventoService.findNovosByParticipante(user));
		if (user.getTipoUsuario().equals(1)) {
			model.addAttribute("projetos", projetoService.findAll());
		} else {
			model.addAttribute("projetos", projetoService.findByUsuarios(user));
		}

		return "frequencias";
	}

	@RequestMapping(value = "/download-frequencias", method = RequestMethod.POST)
	public String downloadFrequencias(Model model, Principal principal, @RequestParam Date dtInicial,
			@RequestParam Date dtFinal, @RequestParam(required = false) Integer projetoId, HttpServletResponse response,
			HttpServletRequest request) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		if (dtInicial == null)
			dtInicial = new Date(Calendar.getInstance().getTimeInMillis());
		if (dtFinal == null)
			dtFinal = new Date(Calendar.getInstance().getTimeInMillis());

		List<Frequencia> frequencias = null;
		Projeto projeto = projetoService.findOne(projetoId);
		frequencias = frequenciaService.findByProjetoAndDtFrequenciaBetween(projeto, dtInicial, dtFinal);

		try {

			String caminho = "Frequencias.pdf";
			File tempFile = new File(caminho);
			tempFile.delete();

			Document document = new Document(PageSize.A4);
			document.addSubject("Frequências");
			document.addAuthor(principal.getName());
			// step 2
			PdfWriter.getInstance(document, new FileOutputStream(caminho));
			// step 3
			document.open();
			// step 4
			document.add(pdfFrequencias(frequencias));
			// step 5
			document.close();

			File file = new File(caminho);
			downloadArquivo(request, response, file);
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return "redirect:/frequencias.html";
	}

	@RequestMapping(value = "frequencia-register", method = RequestMethod.GET)
	public String showRegister(Model model, Principal principal) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Usuario user = usuarioService.findByEmail(principal.getName());
		if (!user.getTipoUsuario().equals(2))
			return "redirect:/frequencias.html";

		model.addAttribute("frequencia", new Frequencia());
		model.addAttribute("projetos", projetoService.findByUsuarios(user));
		model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("documents", documentoService.findNovosByDestinatario(user));
		model.addAttribute("events", eventoService.findNovosByParticipante(user));

		return "frequencia-register";
	}

	@RequestMapping(value = "frequencia-register", method = RequestMethod.POST)
	public String doRegister(Model model, Principal principal, @RequestParam Date dtProposta,
			@RequestParam Integer projetoId, @RequestParam Integer nrFrequencias) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		Projeto projeto = projetoService.findOne(projetoId);
		Usuario orientador = null;

		for (Usuario usuario : projeto.getUsuarios()) {
			if (usuario.getTipoUsuario().equals(2)) {
				orientador = usuario;
			}
		}

		Calendar c = Calendar.getInstance();
		c.setTime(dtProposta);

		for (int i = 0; i < nrFrequencias; i++) {
			Date dataFrequencia = new Date(c.getTimeInMillis());
			for (Usuario usuario : projeto.getUsuarios()) {
				if (usuario.getTipoUsuario().equals(3)) {
					Frequencia frequencia = new Frequencia();
					frequencia.setDtFrequencia(dataFrequencia);
					frequencia.setOrientador(orientador);
					frequencia.setProjeto(projeto);
					frequencia.setAluno(usuario);

					frequenciaService.save(frequencia);
				}
			}
			c.add(Calendar.DAY_OF_MONTH, 7);
		}

		return "redirect:/frequencias.html";
	}

	@RequestMapping(value = "/update-frequencia", method = RequestMethod.POST)
	public String updateFrequencia(Model model, Principal principal, @RequestParam Integer idFrequencia,
			@RequestParam Boolean compareceu, @RequestParam String atividades, @RequestParam Date dtInicial,
			@RequestParam Date dtFinal, @RequestParam Integer projetoId) {
		if (principal == null)
			return "redirect:/login.html?authenticate=false";

		try {
			Usuario user = usuarioService.findByEmail(principal.getName());
			if (!user.getTipoUsuario().equals(2))
				return "redirect:/frequencias.html";

			System.out.println(compareceu);
			Frequencia frequencia = frequenciaService.findOne(idFrequencia);
			frequencia.setCompareceu(compareceu);
			frequencia.setAtividades(atividades);

			frequenciaService.save(frequencia);

			Projeto projeto = projetoService.findOne(projetoId);
			model.addAttribute("frequencias",
					frequenciaService.findByProjetoAndDtFrequenciaBetween(projeto, dtInicial, dtFinal));

			model.addAttribute("dtInicial", dtInicial);
			model.addAttribute("dtFinal", dtFinal);
			model.addAttribute("projetoId", projetoId);
			model.addAttribute("messages", mensagemService.findRecebidasNovas(principal.getName()));
			model.addAttribute("user", user);
			model.addAttribute("documents", documentoService.findNovosByDestinatario(user));
			model.addAttribute("events", eventoService.findNovosByParticipante(user));
			if (user.getTipoUsuario().equals(1)) {
				model.addAttribute("projetos", projetoService.findAll());
			} else {
				model.addAttribute("projetos", projetoService.findByUsuarios(user));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "frequencias";
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

	public PdfPTable pdfFrequencias(List<Frequencia> frequencias) {

		// a table with three columns
		PdfPTable table = new PdfPTable(5);
		try { // the cell object
			PdfPCell cell;

			cell = new PdfPCell(new Phrase("FREQUÊNCIAS"));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell.setColspan(10);
			table.addCell(cell);

			table.addCell("PROJETO");
			table.addCell("ALUNO");
			table.addCell("ORIENTADOR");
			table.addCell("DATA");
			table.addCell("COMPARECEU");

			for (Frequencia frequencia : frequencias) {
				table.addCell(frequencia.getProjeto().getId().toString());
				table.addCell(frequencia.getAluno().getNome());
				table.addCell(frequencia.getOrientador().getNome());
				table.addCell(dateToString(frequencia.getDtFrequencia()));
				table.addCell(frequencia.getCompareceu() != null ? "Sim" : "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	private String dateToString(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}
}
