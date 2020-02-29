package com.lughtech.grana.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class GastoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idGrana;
	@NotEmpty(message = "Tipo do gasto não pode ser vazio")
	@Length(max = 20, message = "Nome do gasto não pode ter mais que 20 caracteres")
	private String tipo;
	private Float gasto;
	private Date dataGasto;

	public GastoDTO() {
	}

	public GastoDTO(Integer idGrana, String tipo, Float gasto, Date dataGasto) {
		this.idGrana = idGrana;
		this.tipo = tipo;
		this.gasto = gasto;
		this.dataGasto = dataGasto;
	}

	public Integer getIdGrana() {
		return idGrana;
	}

	public void setGrana(Integer idGrana) {
		this.idGrana = idGrana;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Float getGasto() {
		return gasto;
	}

	public void setGasto(Float gasto) {
		this.gasto = gasto;
	}

	public Date getDataGasto() {
		return dataGasto;
	}

	public void setDataGasto(Date dataGasto) {
		this.dataGasto = dataGasto;
	}

}
