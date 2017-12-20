package br.fk.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
