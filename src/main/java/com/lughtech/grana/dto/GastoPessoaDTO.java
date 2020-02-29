package com.lughtech.grana.dto;

import java.io.Serializable;

import com.lughtech.grana.dominio.GastoPessoa;
import com.lughtech.grana.servicos.validacao.GastoPessoaInsercao;

@GastoPessoaInsercao
public class GastoPessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer IdGastoPessoa;
	private Integer gasto;
	private Integer pessoa;
	private Float valorGasto;
	private Float percentualGasto;

	public GastoPessoaDTO() {

	}

	public GastoPessoaDTO(GastoPessoa gastoPessoa) {
		this.gasto = gastoPessoa.getGasto().getIdGasto();
		this.pessoa = gastoPessoa.getPessoa().getIdPessoa();
		this.valorGasto = gastoPessoa.getValorGasto();
		this.percentualGasto = gastoPessoa.getPercentualGasto();
	}

	public Integer getIdGastoPessoa() {
		return IdGastoPessoa;
	}

	public void setIdGastoPessoa(Integer idGastoPessoa) {
		IdGastoPessoa = idGastoPessoa;
	}

	public Integer getGasto() {
		return gasto;
	}

	public void setGasto(Integer gasto) {
		this.gasto = gasto;
	}

	public Integer getPessoa() {
		return pessoa;
	}

	public void setPessoa(Integer pessoa) {
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

}
