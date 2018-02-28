package br.fk.projeto.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Projeto;
import br.fk.projeto.entity.Usuario;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

	List<Projeto> findByIdIn(Collection<Integer> codProjetos);

	List<Projeto> findByAtivoTrue();

	List<Projeto> findByUsuariosAndAtivoTrue(Usuario usuario);

}
