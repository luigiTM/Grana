package com.lughtech.grana.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lughtech.grana.domain.Usuario;
import com.lughtech.grana.dto.UsuarioDTO;
import com.lughtech.grana.repositories.UsuarioRepository;
import com.lughtech.grana.services.exceptions.IntegridadeDeDadosException;
import com.lughtech.grana.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepositorio;

	public Usuario buscarUsuarioPorId(Integer id) {
		Optional<Usuario> obj = usuarioRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Usuario.class.getSimpleName()));
	}

	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setIdUsuario(null);
		usuario.setCriadoEm(new Timestamp(System.currentTimeMillis()));
		return usuarioRepositorio.save(usuario);
	}

	public Usuario atualizarUsuario(Usuario usuario) {
		buscarUsuarioPorId(usuario.getIdUsuario());
		return usuarioRepositorio.save(usuario);
	}

	public void deletarUsuarioPorId(Integer id) {
		buscarUsuarioPorId(id);
		try {
			usuarioRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

	public List<Usuario> buscarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	public Page<Usuario> buscarUsuariosPaginado(Integer pagina, Integer linhaPagina, String ordenacao, String direcao) {
		PageRequest requisicaoDePagina = PageRequest.of(pagina, linhaPagina, Direction.valueOf(direcao), ordenacao);
		return usuarioRepositorio.findAll(requisicaoDePagina);
	}

	public Usuario deUmDTO(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());
		return usuario;
	}

}
