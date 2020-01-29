package com.lughtech.grana.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.domain.Usuario;
import com.lughtech.grana.repositories.UsuarioRepository;
import com.lughtech.grana.services.exceptions.IntegridadeDeDadosException;
import com.lughtech.grana.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscarUsuarioPorId(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Usuario.class.getSimpleName()));
	}

	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setIdUsuario(null);
		usuario.setCriadoEm(new Timestamp(System.currentTimeMillis()));
		return usuarioRepository.save(usuario);
	}

	public Usuario atualizarUsuario(Usuario usuario) {
		buscarUsuarioPorId(usuario.getIdUsuario());
		return usuarioRepository.save(usuario);
	}

	public void deletarUsuarioPorId(Integer id) {
		buscarUsuarioPorId(id);
		try {
			usuarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

	public List<Usuario> buscarUsuarios() {
		return usuarioRepository.findAll();
	}

}
