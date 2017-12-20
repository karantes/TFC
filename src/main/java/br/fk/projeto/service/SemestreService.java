package br.fk.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Semestre;
import br.fk.projeto.repository.SemestreRepository;

@Service
public class SemestreService {
	@Autowired
	private SemestreRepository semestreRepository;

	public List<Semestre> findAll() {
		return semestreRepository.findAll();
	}

	public void save(Semestre semestre) {
		semestreRepository.save(semestre);
	}

	public Semestre findOne(Integer id) {
		return semestreRepository.findOne(id);
	}

}
