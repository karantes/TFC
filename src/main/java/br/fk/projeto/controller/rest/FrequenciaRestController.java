package br.fk.projeto.controller.rest;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public @ResponseBody String showFrequencias(@RequestParam String email, @RequestParam Date dtInicial,
			@RequestParam Date dtFinal, @RequestParam(required = false) Integer projetoId) {
		Gson gson = new Gson();
		Projeto projeto = projetoService.findOne(projetoId);
		return gson.toJson(frequenciaService.findByProjetoAndDtFrequenciaBetween(projeto, dtInicial, dtFinal));
	}

	@RequestMapping(value = "/rest/frequencia-register", produces = MediaType.ALL_VALUE)
	public void doRegister(@RequestParam String email, @RequestParam Date dtProposta, @RequestParam Integer projetoId,
			@RequestParam Integer nrFrequencias) {

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
	}

	@RequestMapping(value = "/rest/update-frequencia", produces = MediaType.ALL_VALUE)
	public @ResponseBody String updateFrequencia(@RequestParam String email, @RequestParam Integer idFrequencia,
			@RequestParam Boolean compareceu, @RequestParam String atividades, @RequestParam Date dtInicial,
			@RequestParam Date dtFinal, @RequestParam Integer projetoId) {

		try {
			Gson gson = new Gson();
			Usuario user = usuarioService.findByEmail(email);
			if (!user.getTipoUsuario().equals(2))
				return "redirect:/frequencias.html";

			System.out.println(compareceu);
			Frequencia frequencia = frequenciaService.findOne(idFrequencia);
			frequencia.setCompareceu(compareceu);
			frequencia.setAtividades(atividades);

			frequenciaService.save(frequencia);

			Projeto projeto = projetoService.findOne(projetoId);
			return gson.toJson(frequenciaService.findByProjetoAndDtFrequenciaBetween(projeto, dtInicial, dtFinal));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "frequencias";
	}
}
