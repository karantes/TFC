package br.fk.projeto.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Mensagem {
	@Id
	@GeneratedValue
	private Integer id;

	private String assunto;
	private String mensagem;
	private String tipo;
	private Date dtEnvio;

	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.ALL)
	private Projeto projeto;
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	private Usuario rementente;
	@OneToMany(targetEntity = Usuario.class, cascade = CascadeType.ALL)
	private List<Usuario> destinatarios;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDtEnvio() {
		return dtEnvio;
	}

	public void setDtEnvio(Date dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Usuario getRementente() {
		return rementente;
	}

	public void setRementente(Usuario rementente) {
		this.rementente = rementente;
	}

	public List<Usuario> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<Usuario> destinatarios) {
		this.destinatarios = destinatarios;
	}

}
