package com.lughtech.grana.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.dominio.Pessoa;
import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.dto.PessoaDTO;
import com.lughtech.grana.repositorio.GranaRepositorio;
import com.lughtech.grana.repositorio.PessoaRepositorio;
import com.lughtech.grana.repositorio.UsuarioRepositorio;
import com.lughtech.grana.seguranca.UsuarioSpringSecurity;
import com.lughtech.grana.servicos.excecoes.AutorizacaoException;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class PessoaServico {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private GranaRepositorio granaRepositorio;

	public Pessoa buscarPessoaPorId(Integer id) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		Optional<Pessoa> pessoa = pessoaRepositorio.findById(id);
		if (usuarioAtual == null || (pessoa.isPresent() && !pessoa.get().getusuarioCriacao().getId().equals(usuarioAtual.getId()))) {
			throw new AutorizacaoException();
		}
		return pessoa.orElseThrow(() -> new ObjetoNaoEncontradoException(Pessoa.class.getSimpleName()));
	}

	public List<Pessoa> buscarPessoasPorGrana(Integer idGrana) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		Optional<Grana> grana = granaRepositorio.findById(idGrana);
		if (usuarioAtual == null || (grana.isPresent() && !grana.get().getUsuario().getId().equals(usuarioAtual.getId()))) {
			throw new AutorizacaoException();
		}
//		return pessoaRepositorio.findByGrana(idGrana);
		return null;
	}

	public Pessoa salvarPessoa(Pessoa Pessoa) {
		Pessoa.setId(null);
		return pessoaRepositorio.save(Pessoa);
	}

	public Pessoa atualizarPessoa(Pessoa pessoa) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		if (usuarioAtual == null || !pessoa.getusuarioCriacao().getId().equals(usuarioAtual.getId())) {
			throw new AutorizacaoException();
		}
		Pessoa novaPessoa = buscarPessoaPorId(pessoa.getId());
		atualizarInformacoesPessoa(novaPessoa, pessoa);
		return pessoaRepositorio.save(novaPessoa);
	}

	public void deletarPessoaPorId(Integer id) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		Pessoa pessoa = buscarPessoaPorId(id);
		if (usuarioAtual == null || !pessoa.getusuarioCriacao().getId().equals(usuarioAtual.getId())) {
			throw new AutorizacaoException();
		}
		try {
			pessoaRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

	public Page<Pessoa> buscarPessoasPaginado(Integer idUsuarioCriacao, Integer pagina, Integer linhaPagina, String ordenacao, String direcao) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		if (usuarioAtual == null || !idUsuarioCriacao.equals(usuarioAtual.getId())) {
			throw new AutorizacaoException();
		}
		PageRequest requisicaoDePagina = PageRequest.of(pagina, linhaPagina, Direction.valueOf(direcao), ordenacao);
		return pessoaRepositorio.findAll(requisicaoDePagina);
	}

	public Pessoa deUmDTO(PessoaDTO pessoaDTO) {
		Pessoa pessoa;
		if (pessoaDTO.getIdUsuarioCriacao() == null) {
			pessoa = new Pessoa(pessoaDTO.getNome(), null, pessoaDTO.getEmail());
		} else {
			Usuario usuario = usuarioRepositorio.getOne(pessoaDTO.getIdUsuarioCriacao());
			pessoa = new Pessoa(pessoaDTO.getNome(), usuario, pessoaDTO.getEmail());
		}
		return pessoa;
	}

	private void atualizarInformacoesPessoa(Pessoa novaPessoa, Pessoa pessoa) {
		novaPessoa.setEmail(pessoa.getEmail());
	}

}
