package br.fk.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Semestre;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Integer> {

}
