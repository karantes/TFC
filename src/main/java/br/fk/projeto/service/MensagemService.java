package br.fk.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Mensagem;
import br.fk.projeto.repository.MensagemRepository;

@Service
public class MensagemService {

	@Autowired
	private MensagemRepository mensagemRepository;

	public void save(Mensagem mensagem) {
		mensagemRepository.save(mensagem);
	}

	public List<Mensagem> findAll() {
		return mensagemRepository.findAll();
	}

	public Mensagem findOne(Integer id) {
		return mensagemRepository.findOne(id);
	}

}
