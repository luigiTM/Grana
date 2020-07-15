package com.lughtech.grana.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lughtech.grana.servicos.validacao.GastoInsercao;

@GastoInsercao
public class GastoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idGrana;
	@NotEmpty(message = "Tipo do gasto não pode ser vazio")
	@Length(max = 20, message = "Tipo do gasto não pode ter mais que 20 caracteres")
	private String tipo;
	private Float valor;
	private Date dataGasto;

	public GastoDTO() {
	}

	public GastoDTO(Integer idGrana, String tipo, Float valor, Date dataGasto) {
		this.idGrana = idGrana;
		this.tipo = tipo;
		this.valor = valor;
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

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Date getDataGasto() {
		return dataGasto;
	}

	public void setDataGasto(Date dataGasto) {
		this.dataGasto = dataGasto;
	}

}
