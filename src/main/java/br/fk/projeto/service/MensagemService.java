package br.fk.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Mensagem;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.repository.MensagemRepository;

@Service
public class MensagemService {

	@Autowired
	private MensagemRepository mensagemRepository;

	@Autowired
	private UsuarioService usuarioService;

	public void save(Mensagem mensagem) {
		mensagemRepository.save(mensagem);
	}

	public List<Mensagem> findAll() {
		return mensagemRepository.findAll();
	}

	public List<Mensagem> findRecebidas(String email) {
		Usuario usuario = usuarioService.findByEmail(email);
		return mensagemRepository.findByDestinatario(usuario);
	}

	public List<Mensagem> findRecebidasNovas(String email) {
		Usuario usuario = usuarioService.findByEmail(email);
		return mensagemRepository.findByDestinatarioAndStatus(usuario, "NOVA");
	}

	public List<Mensagem> findEnviadas(String email) {
		Usuario usuario = usuarioService.findByEmail(email);
		return mensagemRepository.findByRemetente(usuario);
	}

	public Mensagem findOne(Integer id) {
		return mensagemRepository.findOne(id);
	}

	public void delete(Integer id) {
		mensagemRepository.delete(id);
	}

}
