package br.fk.projeto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Semestre {
	@Id
	@GeneratedValue
	private Integer id;

	private Integer ano;
	private Integer semestre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public String gotSemestre() {
		switch (semestre) {
		case 1:
			return "Primeiro Semestre";
		case 2:
			return "Segundo Semestre";
		default:
			return "Semestre Desconhecido";
		}
	}
}
