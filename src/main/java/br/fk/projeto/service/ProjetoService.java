package br.fk.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.repository.ProjetoRepository;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;

	public List<Projeto> findAll() {
		return projetoRepository.findAll();
	}

	public void save(Projeto projeto) {
		projetoRepository.save(projeto);
	}

	public Projeto findOne(Integer id) {
		return projetoRepository.findOne(id);
	}

	public List<Projeto> findByIdIn(List<Integer> codProjetos) {
		return projetoRepository.findByIdIn(codProjetos);
	}

	public List<Projeto> findByAtivoTrue() {
		return projetoRepository.findByAtivoTrue();
	}

	public List<Projeto> findByUsuarios(Usuario usuario) {
		return projetoRepository.findByUsuariosAndAtivoTrue(usuario);
	}
}
