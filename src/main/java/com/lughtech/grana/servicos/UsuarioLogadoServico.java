package com.lughtech.grana.servicos;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lughtech.grana.seguranca.UsuarioSpringSecurity;

public class UsuarioLogadoServico {

	private UsuarioLogadoServico() {
		throw new IllegalStateException("Utility class");
	}

	public static UsuarioSpringSecurity usuarioLogado() {
		return (UsuarioSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
