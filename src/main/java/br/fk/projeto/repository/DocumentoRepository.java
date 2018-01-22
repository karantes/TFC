package br.fk.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

}
