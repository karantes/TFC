package br.fk.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Evento;
import br.fk.projeto.repository.EventoRepository;

@Service
public class EventoService {
	@Autowired
	private EventoRepository eventoRepository;

	public List<Evento> findAll() {
		return eventoRepository.findAll();
	}

	public void save(Evento evento) {
		eventoRepository.save(evento);
	}

	public Evento findOne(Integer id) {
		return eventoRepository.findOne(id);
	}
}
