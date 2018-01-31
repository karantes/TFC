package br.fk.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Frequencia;

@Repository
public interface FrequenciaRepository extends JpaRepository<Frequencia, Integer> {

}
