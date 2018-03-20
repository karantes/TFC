package br.fk.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.fk.projeto.entity.Mensagem;
import br.fk.projeto.entity.Usuario;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

	@Modifying
	@Transactional
	@Query(value = "delete from Mensagem where id = ?1")
	void delete(Integer id);
	
	List<Mensagem> findByRemetente(Usuario usuario);

	List<Mensagem> findByDestinatario(Usuario usuario);

	List<Mensagem> findByDestinatarioAndStatus(Usuario usuario, String status);
}
