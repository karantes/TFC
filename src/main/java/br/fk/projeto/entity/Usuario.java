package br.fk.projeto.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Integer id;

	private String nome;
	@Column(nullable = false)
	private String senha;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private Integer tipoUsuario;
	@Column(nullable = false)
	private Date dtCadastro;
	private Date dtAlteracao;

	@Column(nullable = false)
	private Boolean ativo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return 1 = COORDENADOR DE TFC; 2 = ORIENTADOR; 3 = ALUNO
	 */
	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String gotTipo() {
		switch (tipoUsuario) {
		case 1:
			return "Coordenador de TFC";
		case 2:
			return "Orientador";
		case 3:
			return "Aluno";
		default:
			return "?";
		}
	}
}
