package com.lughtech.grana.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lughtech.grana.servicos.validacao.GastoInsercao;

@GastoInsercao
public class GastoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "Tipo do gasto não pode ser vazio")
	@Length(max = 20, message = "Tipo do gasto não pode ter mais que 20 caracteres")
	private String tipo;
	private Float valor;
	private Date dataGasto;

	public GastoDTO() {
	}

	public GastoDTO(Integer id, String tipo, Float valor, Date dataGasto) {
		this.id = id;
		this.tipo = tipo;
		this.valor = valor;
		this.dataGasto = dataGasto;
	}

	public Integer getId() {
		return id;
	}

	public void setGrana(Integer id) {
		this.id = id;
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
