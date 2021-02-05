package com.lughtech.grana.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.lughtech.grana.dominio.Grana;

public class GranaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Integer usuario;
	private String codigoDeAcesso;
	private Timestamp modificadoEm;

	public GranaDto() {

	}

	public GranaDto(Grana grana) {
		this.id = grana.getIdGrana();
		this.nome = grana.getNome();
		this.usuario = grana.getUsuario().getId();
		this.codigoDeAcesso = grana.getCodigoDeAcesso();
	}

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

	public String getCodigoDeAcesso() {
		return codigoDeAcesso;
	}

	public void setCodigoDeAcesso(String codigoDeAcesso) {
		this.codigoDeAcesso = codigoDeAcesso;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Timestamp getModificadoEm() {
		return modificadoEm;
	}

	public void setModificadoEm(Timestamp modificadoEm) {
		this.modificadoEm = modificadoEm;
	}

}
