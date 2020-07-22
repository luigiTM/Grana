package com.lughtech.grana.servicos;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.dto.GranaDTO;
import com.lughtech.grana.repositorio.GranaRepositorio;
import com.lughtech.grana.repositorio.UsuarioRepositorio;
import com.lughtech.grana.seguranca.UsuarioSpringSecurity;
import com.lughtech.grana.servicos.excecoes.AutorizacaoException;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class GranaServico {

	@Autowired
	private GranaRepositorio granaRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public Grana buscarGranaPorId(Integer id) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		Optional<Grana> grana = granaRepositorio.findById(id);
		if (!grana.get().equals(null)) {
			if (usuarioAtual == null || !grana.get().getUsuario().getId().equals(usuarioAtual.getId())) {
				throw new AutorizacaoException();
			}
		}
		return grana.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto do tipo " + Grana.class.getSimpleName() + " não encontrado!"));
	}

	public Grana buscarGranaPorCodigoDeAcesso(String codigodeAcesso) {
		Optional<Grana> grana = granaRepositorio.findByCodigoDeACesso(codigodeAcesso);
		return grana.orElseThrow(() -> new ObjetoNaoEncontradoException("Código de acesso inválido"));
	}

	public Grana salvarGrana(Grana grana) {
		grana.setIdGrana(null);
		grana.setCriadoEm(new Timestamp(System.currentTimeMillis()));
		grana.setCodigoDeAcesso(DigestUtils.md5DigestAsHex(grana.getCriadoEm().toString().getBytes()));
		grana.setModificadoEm(new Timestamp(System.currentTimeMillis()));
		return granaRepositorio.save(grana);
	}

	public Grana atualizarGrana(Grana grana) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		if (usuarioAtual == null || !grana.getUsuario().getId().equals(usuarioAtual.getId())) {
			throw new AutorizacaoException();
		}
		Grana novoGrana = buscarGranaPorId(grana.getIdGrana());
		atualizarInformacoesGrana(grana, novoGrana);
		return granaRepositorio.save(novoGrana);
	}

	public void deletarGranaPorId(Integer id) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		Grana grana = buscarGranaPorId(id);
		if (usuarioAtual == null || !grana.getUsuario().getId().equals(usuarioAtual.getId())) {
			throw new AutorizacaoException();
		}
		try {
			granaRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

	public Page<Grana> buscarGranasPaginado(Integer idUsuario, Integer pagina, Integer linhaPagina, String ordenacao, String direcao) {
		UsuarioSpringSecurity usuarioLogado = UsuarioLogadoServico.usuarioLogado();
		if (usuarioLogado == null || !idUsuario.equals(usuarioLogado.getId())) {
			throw new AutorizacaoException();
		}
		PageRequest requisicaoPaginada = PageRequest.of(pagina, linhaPagina, Direction.valueOf(direcao), ordenacao);
		Usuario usuario = usuarioRepositorio.findById(idUsuario).get();
		return granaRepositorio.findByUsuario(usuario, requisicaoPaginada);
	}

	public Grana deUmDTO(GranaDTO granaDTO) {
		Grana grana;
		if (granaDTO.getUsuario() == null) {
			grana = new Grana(granaDTO.getNome(), null);
		} else {
			Usuario usuario = usuarioRepositorio.findById(granaDTO.getUsuario()).get();
			grana = new Grana(granaDTO.getNome(), usuario);
		}
		return grana;
	}

	private void atualizarInformacoesGrana(Grana grana, Grana novoGrana) {
		novoGrana.setNome(grana.getNome());
		novoGrana.setModificadoEm(new Timestamp(System.currentTimeMillis()));
	}

}
