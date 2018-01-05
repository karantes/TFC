package br.fk.projeto.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mensagem {
	@Id
	@GeneratedValue
	private Integer id;

	private String assunto;
	private String mensagem;
	private String tipo;
	private Date dtEnvio;

}
