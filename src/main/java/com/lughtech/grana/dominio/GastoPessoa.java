package com.lughtech.grana.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class GastoPessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_gasto")
	@JsonBackReference
	private Gasto gasto;
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	@Column(name = "valor", nullable = false)
	private Float valorGasto;
	@Column(name = "percentual", nullable = false)
	private Float percentualGasto;

	public GastoPessoa() {

	}

	public GastoPessoa(Gasto gasto, Pessoa pessoa, Float valorGasto, Float percentualGasto) {
		this.gasto = gasto;
		this.pessoa = pessoa;
		this.valorGasto = valorGasto;
		this.percentualGasto = percentualGasto;
	}

	public Integer getIdGastoPessoa() {
		return id;
	}

	public void setIdGastoPessoa(Integer id) {
		this.id = id;
	}

	public Gasto getGasto() {
		return gasto;
	}

	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gasto == null) ? 0 : gasto.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		GastoPessoa other = (GastoPessoa) obj;
		if (gasto == null) {
			if (other.gasto != null)
				return false;
		} else if (!gasto.equals(other.gasto))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

}
