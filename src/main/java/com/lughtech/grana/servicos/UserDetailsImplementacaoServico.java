package com.lughtech.grana.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.repositorio.UsuarioRepositorio;
import com.lughtech.grana.seguranca.UsuarioSpringSecurity;

@Service
public class UserDetailsImplementacaoServico implements UserDetailsService {

	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if (usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UsuarioSpringSecurity(usuario.getIdUsuario(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
	}

}
