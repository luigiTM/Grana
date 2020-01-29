package com.lughtech.grana.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Grana implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGrana;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	private Timestamp criadoEm;
	private Timestamp modificadoEm;
	private String codigoDeAcesso;

	public Grana() {

	}

	public Grana(String nome, Usuario usuario, Integer mes, Integer ano) {
		super();
		this.nome = nome;
		this.usuario = usuario;
		this.criadoEm = new Timestamp(System.currentTimeMillis());
		this.modificadoEm = null;
	}

	public Integer getIdGrana() {
		return idGrana;
	}

	public void setIdGrana(Integer idGrana) {
		this.idGrana = idGrana;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Timestamp getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Timestamp criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Timestamp getModificadoEm() {
		return modificadoEm;
	}

	public void setModificadoEm(Timestamp modificadoEm) {
		this.modificadoEm = modificadoEm;
	}

	public String getCodigoDeAcesso() {
		return codigoDeAcesso;
	}

	public void setCodigoDeAcesso(String codigoDeAcesso) {
		this.codigoDeAcesso = codigoDeAcesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGrana == null) ? 0 : idGrana.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grana other = (Grana) obj;
		if (idGrana == null) {
			if (other.idGrana != null)
				return false;
		} else if (!idGrana.equals(other.idGrana))
			return false;
		return true;
	}
}
