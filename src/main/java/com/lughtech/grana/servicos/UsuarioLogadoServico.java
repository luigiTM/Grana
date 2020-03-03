package com.lughtech.grana.servicos;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lughtech.grana.seguranca.UsuarioSpringSecurity;

public class UsuarioLogadoServico {

	public static UsuarioSpringSecurity usuarioLogado() {
		return (UsuarioSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
