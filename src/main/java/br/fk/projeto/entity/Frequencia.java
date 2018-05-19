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
	private String atividades;
	private Date dtFrequencia;
	private Boolean compareceu;

	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.ALL)
	private Projeto projeto;

	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	private Usuario aluno;

	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	private Usuario orientador;

	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	private Usuario coorientador;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	public Date getDtFrequencia() {
		return dtFrequencia;
	}

	public void setDtFrequencia(Date dtFrequencia) {
		this.dtFrequencia = dtFrequencia;
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

	public Usuario getAluno() {
		return aluno;
	}

	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}

	public Usuario getOrientador() {
		return orientador;
	}

	public void setOrientador(Usuario orientador) {
		this.orientador = orientador;
	}

	public Usuario getCoorientador() {
		return coorientador;
	}

	public void setCoorientador(Usuario coorientador) {
		this.coorientador = coorientador;
	}

}
