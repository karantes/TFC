package br.fk.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	List<Usuario> findByAtivoAndTipoUsuario(Boolean ativo, Integer tipo);

	Usuario findByEmail(String email);

	@Query(value = "select u from Usuario u where id != ?1 and ativo = true")
	List<Usuario> findAll(Integer id);

}
