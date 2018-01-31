package br.fk.projeto.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Frequencia {
	@Id
	@GeneratedValue
	private Integer id;

	private Date dtProposta;
	private Date dtReal;

	private Boolean compareceu;

	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.ALL)
	private Projeto projeto;

	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	private Usuario orientador;

	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	private Usuario aluno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDtProposta() {
		return dtProposta;
	}

	public void setDtProposta(Date dtProposta) {
		this.dtProposta = dtProposta;
	}

	public Date getDtReal() {
		return dtReal;
	}

	public void setDtReal(Date dtReal) {
		this.dtReal = dtReal;
	}

	public Boolean getCompareceu() {
		return compareceu;
	}

	public void setCompareceu(Boolean compareceu) {
		this.compareceu = compareceu;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Usuario getOrientador() {
		return orientador;
	}

	public void setOrientador(Usuario orientador) {
		this.orientador = orientador;
	}

	public Usuario getAluno() {
		return aluno;
	}

	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}

}
