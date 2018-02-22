package br.fk.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Mensagem;
import br.fk.projeto.entity.Usuario;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

	List<Mensagem> findByRemetente(Usuario usuario);

	List<Mensagem> findByDestinatario(Usuario usuario);

	List<Mensagem> findByDestinatarioAndStatus(Usuario usuario, String status);
}
