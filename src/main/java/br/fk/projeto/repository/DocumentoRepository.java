package br.fk.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.fk.projeto.entity.Documento;
import br.fk.projeto.entity.Usuario;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

	List<Documento> findByRemetenteOrDestinatario(Usuario remetente, Usuario destinatario);

	@Modifying
	@Transactional
	@Query(value = "delete from Documento where id = ?1")
	void delete(Integer id);

	List<Documento> findByDestinatarioAndStatus(Usuario usuario, String status);

}
