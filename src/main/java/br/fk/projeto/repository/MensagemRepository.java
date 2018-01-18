package br.fk.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fk.projeto.entity.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

}
