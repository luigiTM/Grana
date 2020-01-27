package com.lughtech.grana.domain;

import java.sql.Timestamp;

public class Grana {

	private Integer idGrana;
	private String nome;
	private Integer usuario;
	private Integer mes;
	private Integer ano;
	private Timestamp criadoEm;
	private Timestamp modificadoEm;

	public Grana() {

	}

	public Grana(String nome, Integer usuario, Integer mes, Integer ano) {
		super();
		this.nome = nome;
		this.usuario = usuario;
		this.mes = mes;
		this.ano = ano;
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

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
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
