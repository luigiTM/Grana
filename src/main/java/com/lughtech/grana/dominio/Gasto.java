package com.lughtech.grana.dominio;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Gasto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id_grana")
	@JsonBackReference
	private Grana grana;
	@Column(name = "tipo", nullable = false)
	private String tipo;
	@Column(name = "valor", nullable = false)
	private Float valor;
	@Column(name = "data", nullable = false)
	private Date data;

	@JsonIgnore
	@OneToMany(mappedBy = "gasto")
	@JsonManagedReference
	private List<GastoPessoa> gastosPessoas = new ArrayList<GastoPessoa>();

	public Gasto() {
	}

	public Gasto(Grana grana, String tipo, Float valor, Date data) {
		this.grana = grana;
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
	}

	public Integer getIdGasto() {
		return id;
	}

	public void setIdGasto(Integer id) {
		this.id = id;
	}

	public Grana getGrana() {
		return grana;
	}

	public void setGrana(Grana grana) {
		this.grana = grana;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<GastoPessoa> getGastosPessoas() {
		return gastosPessoas;
	}

	public void setGastosPessoas(List<GastoPessoa> gastosPessoas) {
		this.gastosPessoas = gastosPessoas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Gasto other = (Gasto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
