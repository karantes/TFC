package br.fk.projeto.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Frequencia;
import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;
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

	public List<Frequencia> findByOrientadorAndDtFrequenciaBetween(Usuario user, Date dtFrequenciaInicial,
			Date dtFrequenciaFinal) {
		return frequenciaRepository.findByOrientadorAndDtFrequenciaBetween(user, dtFrequenciaInicial,
				dtFrequenciaFinal);
	}

	public List<Frequencia> findByAlunoAndDtFrequenciaBetween(Usuario user, Date dtFrequenciaInicial,
			Date dtFrequenciaFinal) {
		return frequenciaRepository.findByAlunoAndDtFrequenciaBetween(user, dtFrequenciaInicial, dtFrequenciaFinal);
	}

	public List<Frequencia> findByProjetoAndDtFrequenciaBetween(Projeto projeto, Date dtFrequenciaInicial,
			Date dtFrequenciaFinal) {
		return frequenciaRepository.findByProjetoAndDtFrequenciaBetween(projeto, dtFrequenciaInicial,
				dtFrequenciaFinal);
	}
}
