package com.lughtech.grana.dto;

import java.io.Serializable;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer idUsuarioCriacao;
	private String email;

	public PessoaDTO() {
	}

	public PessoaDTO(String nome, Integer idUsuarioCriacao, String email) {
		this.nome = nome;
		this.idUsuarioCriacao = idUsuarioCriacao;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdUsuarioCriacao() {
		return idUsuarioCriacao;
	}

	public void setIdUsuarioCriacao(Integer idUsuarioCriacao) {
		this.idUsuarioCriacao = idUsuarioCriacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
