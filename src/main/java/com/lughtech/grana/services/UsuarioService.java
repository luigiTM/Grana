package com.lughtech.grana.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lughtech.grana.domain.Usuario;
import com.lughtech.grana.repositories.UsuarioRepository;
import com.lughtech.grana.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario buscarPessoaPorId(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto do tipo " + Usuario.class.getSimpleName() + " não encontrado!"));
	}

}
