package br.fk.projeto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mensagem {
	@Id
	@GeneratedValue
	private Integer id;
	
	
}
