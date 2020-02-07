package com.lughtech.grana.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lughtech.grana.dominio.Usuario;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 5, max = 50, message = "Nome deve ter entre 5 e 50 caracteres")
	private String nome;
	@NotEmpty(message = "Senha não pode ser vazia")
	@Length(min = 8, message = "Senha deve ter no mínimo 8 caraceres")
	private String senha;
	@NotEmpty(message = "Email não pode ser vazio")
	private String email;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.email = usuario.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
