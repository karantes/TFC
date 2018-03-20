package br.fk.projeto.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Frequencia;
import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;

@Repository
public interface FrequenciaRepository extends JpaRepository<Frequencia, Integer> {

	List<Frequencia> findByOrientadorAndDtFrequenciaBetween(Usuario user, Date dtPropostaInicial, Date dtPropostaFinal);

	List<Frequencia> findByAlunoAndDtFrequenciaBetween(Usuario user, Date dtPropostaInicial, Date dtPropostaFinal);

	List<Frequencia> findByProjetoAndDtFrequenciaBetween(Projeto projeto, Date dtPropostaInicial, Date dtPropostaFinal);

}
