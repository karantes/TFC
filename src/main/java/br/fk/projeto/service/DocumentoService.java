package br.fk.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fk.projeto.entity.Documento;
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
}
