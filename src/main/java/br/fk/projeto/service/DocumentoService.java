package br.fk.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Documento;
import br.fk.projeto.entity.Usuario;
import br.fk.projeto.repository.DocumentoRepository;

@Service
public class DocumentoService {
	@Autowired
	private DocumentoRepository documentoRepository;

	public List<Documento> findAll() {
		return documentoRepository.findAll();
	}

	public void save(Documento documento) {
		documentoRepository.save(documento);
	}

	public void saveAll(List<Documento> documentos) {
		documentoRepository.save(documentos);
	}

	public List<Documento> findByRemetenteOrDestinatario(Usuario usuario) {
		return documentoRepository.findByRemetenteOrDestinatario(usuario, usuario);
	}

	public List<Documento> findNovosByDestinatario(Usuario usuario) {
		return documentoRepository.findByDestinatarioAndStatus(usuario, "NOVO");
	}

	public Documento findOne(Integer id) {
		return documentoRepository.findOne(id);
	}

	public void delete(Integer id) {
		documentoRepository.delete(id);
	}
}
