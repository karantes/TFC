package br.fk.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Frequencia;
import br.fk.projeto.repository.FrequenciaRepository;

@Service
public class FrequenciaService {
	@Autowired
	private FrequenciaRepository frequenciaRepository;

	public List<Frequencia> findAll() {
		return frequenciaRepository.findAll();
	}

	public void save(Frequencia frequencia) {
		frequenciaRepository.save(frequencia);
	}

	public Frequencia findOne(Integer id) {
		return frequenciaRepository.findOne(id);
	}
}
