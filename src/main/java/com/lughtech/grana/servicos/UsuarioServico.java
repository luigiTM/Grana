package com.lughtech.grana.servicos;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.dominio.enumerados.Perfil;
import com.lughtech.grana.dto.UsuarioDTO;
import com.lughtech.grana.repositorio.UsuarioRepositorio;
import com.lughtech.grana.seguranca.UsuarioSpringSecurity;
import com.lughtech.grana.servicos.excecoes.AutorizacaoException;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private BCryptPasswordEncoder codificadorDeSenha;

	public Usuario buscarUsuarioPorId(Integer id) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		if (usuarioAtual == null || !usuarioAtual.temPermicao(Perfil.ADMIN) && !id.equals(usuarioAtual.getId())) {
			throw new AutorizacaoException();
		}
		Optional<Usuario> usuario = usuarioRepositorio.findById(id);
		return usuario.orElseThrow(() -> new ObjetoNaoEncontradoException(Usuario.class.getSimpleName()));
	}

	public Usuario buscarUsuarioPorEmail(String email) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		if (usuarioAtual == null || !usuarioAtual.temPermicao(Perfil.ADMIN) && !email.equals(usuarioAtual.getUsername())) {
			throw new AutorizacaoException();
		}
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if (usuario == null) {
			throw new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + usuarioAtual.getId() + ", Tipo: " + Usuario.class.getName());
		}
		return usuario;
	}

	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setId(null);
		usuario.setCriadoEm(new Timestamp(System.currentTimeMillis()));
		return usuarioRepositorio.save(usuario);
	}

	public Usuario atualizarUsuario(Usuario usuario) {
		Usuario novoUsuario = buscarUsuarioPorId(usuario.getId());
		atualizaInformacoesUsuario(usuario, novoUsuario);
		return usuarioRepositorio.save(novoUsuario);
	}

	public void deletarUsuarioPorId(Integer id) {
		buscarUsuarioPorId(id);
		try {
			usuarioRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException(Usuario.class.getSimpleName());
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
		Usuario usuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), codificadorDeSenha.encode(usuarioDTO.getSenha()));
		return usuario;
	}

	private void atualizaInformacoesUsuario(Usuario usuario, Usuario novoUsuario) {
		novoUsuario.setNome((usuario.getNome() == null) ? novoUsuario.getNome() : usuario.getNome());
		novoUsuario.setEmail((usuario.getEmail() == null) ? novoUsuario.getEmail() : usuario.getEmail());
		novoUsuario.setSenha((usuario.getSenha() == null) ? novoUsuario.getSenha() : usuario.getSenha());
	}

}
