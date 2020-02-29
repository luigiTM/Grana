package com.lughtech.grana.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Pessoa;
import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.dto.PessoaDTO;
import com.lughtech.grana.repositorio.PessoaRepositorio;
import com.lughtech.grana.repositorio.UsuarioRepositorio;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class PessoaServico {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public Pessoa buscarPessoaPorId(Integer id) {
		Optional<Pessoa> obj = pessoaRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Pessoa.class.getSimpleName()));
	}

	public Pessoa salvarPessoa(Pessoa Pessoa) {
		Pessoa.setIdPessoa(null);
		return pessoaRepositorio.save(Pessoa);
	}

	public Pessoa atualizarPessoa(Pessoa pessoa) {
		Pessoa novaPessoa = buscarPessoaPorId(pessoa.getIdPessoa());
		atualizarInformacoesPessoa(novaPessoa, pessoa);
		return pessoaRepositorio.save(novaPessoa);
	}

	public void deletarPessoaPorId(Integer id) {
		buscarPessoaPorId(id);
		try {
			pessoaRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

	public Page<Pessoa> buscarPessoasPaginado(Integer pagina, Integer linhaPagina, String ordenacao, String direcao) {
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
