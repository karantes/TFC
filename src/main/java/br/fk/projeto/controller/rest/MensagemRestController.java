package br.fk.projeto.controller.rest;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.fk.projeto.entity.Mensagem;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.service.MensagemService;
import br.fk.projeto.service.UsuarioService;

@RestController
public class MensagemRestController {
	@Autowired
	private MensagemService mensagemService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/rest/mensagens-enviadas", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showMensagensEnviadas(@RequestParam String email) {
		Gson gson = new Gson();
		return gson.toJson(mensagemService.findEnviadas(email));
	}

	@RequestMapping(value = "/rest/mensagens-recebidas", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showMensagensRecebidas(@RequestParam String email) {
		Gson gson = new Gson();
		return gson.toJson(mensagemService.findRecebidas(email));
	}

	@RequestMapping(value = "/rest/mensagem-register")
	public void doRegister(@RequestParam String email, @RequestParam(defaultValue = "") String assunto,
			@RequestParam(defaultValue = "") String mensagem, @RequestParam(defaultValue = "") String tipo,
			@RequestParam List<Integer> destinatariosId) {
		Usuario remetente = usuarioService.findByEmail(email);
		java.sql.Date dtAtual = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		destinatariosId.forEach(destinatario -> {
			Mensagem msg = new Mensagem();

			msg.setDestinatario(usuarioService.findOne(destinatario));
			msg.setRemetente(remetente);
			msg.setDtEnvio(dtAtual);
			msg.setTipo(tipo);
			msg.setMensagem(mensagem.replace("\n", "<br>"));
			msg.setAssunto(assunto);
			msg.setStatus("NOVA");

			mensagemService.save(msg);
		});
	}

	@RequestMapping(value = "/rest/mensagem-detail/{id}", produces = MediaType.ALL_VALUE)
	public @ResponseBody String showMensagem(@RequestParam String email, @PathVariable Integer id) {
		Gson gson = new Gson();
		Mensagem mensagem = mensagemService.findOne(id);
		if (!mensagem.getRemetente().getEmail().equals(email) && !mensagem.getDestinatario().getEmail().equals(email))
			return "redirect:/mensagens-recebidas.html";

		if (mensagem.getStatus().equals("NOVA") && !mensagem.getRemetente().getEmail().equals(email)) {
			mensagem.setStatus("LIDA");
			mensagemService.save(mensagem);
		}
		return gson.toJson(mensagem);
	}

	@RequestMapping(value = "/rest/delete-mensagem/{id}", produces = MediaType.ALL_VALUE)
	public void deleteMensagem(@RequestParam String email, @PathVariable Integer id) {
		mensagemService.delete(id);
	}
}
