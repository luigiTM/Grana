package com.lughtech.grana.dominio.enumerados;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), USUARIO(2, "ROLE_USUARIO");

	private Integer codigo;
	private String descricao;

	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil paraEnumerado(Integer codigo) {

		if (codigo == null) {
			return null;
		}

		for (Perfil perfil : Perfil.values()) {
			if (codigo.equals(perfil.getCodigo())) {
				return perfil;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + codigo);
	}

}