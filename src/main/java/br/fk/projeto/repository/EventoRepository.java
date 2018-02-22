package br.fk.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Evento;
import br.fk.projeto.entity.Usuario;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

	List<Evento> findByParticipante(Usuario usuario);

}
