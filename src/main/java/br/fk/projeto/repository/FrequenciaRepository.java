package br.fk.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Frequencia;
import br.fk.projeto.entity.Usuario;

@Repository
public interface FrequenciaRepository extends JpaRepository<Frequencia, Integer> {

	List<Frequencia> findByOrientador(Usuario user);

	List<Frequencia> findByAluno(Usuario user);

}
