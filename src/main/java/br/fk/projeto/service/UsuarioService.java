package br.fk.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Usuario;
import br.fk.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findOne(Integer id) {
		return usuarioRepository.findOne(id);
	}

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public List<Usuario> findAlunos() {
		return usuarioRepository.findByTipoUsuarioAndAtivoTrue(3);
	}

	public List<Usuario> findOrientadores() {
		return usuarioRepository.findByTipoUsuarioAndAtivoTrue(2);
	}
}
