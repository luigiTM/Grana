package com.lughtech.grana.dominio;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lughtech.grana.dominio.enumerados.Perfil;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@JsonIgnore
	@Column(name = "senha", nullable = false)
	private String senha;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "criado_em", nullable = false)
	private Timestamp criadoEm;
	@Column(name = "ultimo_acesso", nullable = true)
	private Timestamp ultimoAcesso;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfil", joinColumns = @JoinColumn(name = "id_usuario"))
	@Column(name = "id_perfil")
	private Set<Integer> perfis = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Grana> granas = new ArrayList<>();

	public Usuario() {
		adicionarPerfil(Perfil.USUARIO);
	}

	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.criadoEm = null;
		this.ultimoAcesso = null;
		adicionarPerfil(Perfil.USUARIO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Timestamp getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Timestamp criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Timestamp getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Timestamp ultimoLogin) {
		this.ultimoAcesso = ultimoLogin;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(Perfil::paraEnumerado).collect(Collectors.toSet());
	}

	public void adicionarPerfil(Perfil perfil) {
		perfis.add(perfil.getCodigo());

	}

	public List<Grana> getGranas() {
		return granas;
	}

	public void setGranas(List<Grana> granas) {
		this.granas = granas;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
