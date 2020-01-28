package com.lughtech.grana.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPessoa;
	private String nome;
	private Float valorGasto;
	private Float percentualGasto;
	private Integer idUsuarioCriacao;

	@ManyToMany(mappedBy = "pessoas")
	private List<Gasto> gastos = new ArrayList<Gasto>();

	public Pessoa() {
	}

	public Pessoa(Integer idPessoa, String nome, Float valorGasto, Float percentualGasto, Integer idUsuarioCriacao) {
		this.nome = nome;
		this.valorGasto = valorGasto;
		this.percentualGasto = percentualGasto;
		this.idUsuarioCriacao = idUsuarioCriacao;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(Float valorGasto) {
		this.valorGasto = valorGasto;
	}

	public Float getPercentualGasto() {
		return percentualGasto;
	}

	public void setPercentualGasto(Float percentualGasto) {
		this.percentualGasto = percentualGasto;
	}

	public Integer getIdUsuarioCriacao() {
		return idUsuarioCriacao;
	}

	public void setIdUsuarioCriacao(Integer idUsuarioCriacao) {
		this.idUsuarioCriacao = idUsuarioCriacao;
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuarioCriacao == null) ? 0 : idUsuarioCriacao.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (idUsuarioCriacao == null) {
			if (other.idUsuarioCriacao != null)
				return false;
		} else if (!idUsuarioCriacao.equals(other.idUsuarioCriacao))
			return false;
		return true;
	}
}
