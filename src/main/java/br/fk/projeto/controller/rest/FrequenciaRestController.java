package br.fk.projeto.controller.rest;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.fk.projeto.entity.Frequencia;
import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.FrequenciaService;
import br.fk.projeto.service.ProjetoService;
import br.fk.projeto.service.UsuarioService;

@RestController
public class FrequenciaRestController {
	@Autowired
	private FrequenciaService frequenciaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProjetoService projetoService;

	@RequestMapping(value = "/rest/frequencias", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showFrequencias(@RequestParam String email, @RequestParam String dtInicial,
			@RequestParam String dtFinal, @RequestParam(required = false) Integer projetoId) {
		Gson gson = new Gson();
		Projeto projeto = projetoService.findOne(projetoId);
		try {
			return gson.toJson(frequenciaService.findByProjetoAndDtFrequenciaBetween(projeto,
					new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dtInicial).getTime()),
					new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dtFinal).getTime())));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping(value = "/rest/frequencia-register", produces = MediaType.ALL_VALUE)
	public void doRegister(@RequestParam String email, @RequestParam String dtProposta, @RequestParam Integer projetoId,
			@RequestParam Integer nrFrequencias) {

		Projeto projeto = projetoService.findOne(projetoId);
		Usuario orientador = null;

		for (Usuario usuario : projeto.getUsuarios()) {
			if (usuario.getTipoUsuario().equals(2)) {
				orientador = usuario;
			}
		}

		Calendar c = Calendar.getInstance();
		try {
			c.setTime(new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dtProposta).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	}

	@RequestMapping(value = "/rest/update-frequencia/{id}", produces = MediaType.ALL_VALUE)
	public @ResponseBody String updateFrequencia(@RequestParam String email, @PathVariable Integer idFrequencia,
			@RequestParam Boolean compareceu, @RequestParam String atividades) {

		try {
			Usuario user = usuarioService.findByEmail(email);
			if (!user.getTipoUsuario().equals(2))
				return "redirect:/frequencias.html";

			Frequencia frequencia = frequenciaService.findOne(idFrequencia);
			frequencia.setCompareceu(compareceu);
			frequencia.setAtividades(atividades);

			frequenciaService.save(frequencia);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "frequencias";
	}

	@RequestMapping(value = "/rest/frequencia/{idFrequencia}", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showFrequencia(@PathVariable Integer idFrequencia) {
		try {
			Gson gson = new Gson();
			return gson.toJson(frequenciaService.findOne(idFrequencia));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "frequencias";
	}
}
