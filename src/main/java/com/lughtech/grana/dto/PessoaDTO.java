package com.lughtech.grana.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class PessoaDTO {

	@NotEmpty(message = "Nome da pessoa não pode ser vazio")
	@Length(max = 15, message = "Nome da pessoa não pode ter mais que 15 caracteres")
	private String nome;
	private Integer idUsuarioCriacao;
	@NotEmpty(message = "Email não pode ser vazio")
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
