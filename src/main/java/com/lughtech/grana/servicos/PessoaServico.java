package com.lughtech.grana.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Pessoa;
import com.lughtech.grana.repositorio.PessoaRepositorio;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class PessoaServico {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;

	public Pessoa buscarPessoaPorId(Integer id) {
		Optional<Pessoa> obj = pessoaRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Pessoa.class.getSimpleName()));
	}

	public Pessoa salvarPessoa(Pessoa Pessoa) {
		Pessoa.setIdPessoa(null);
		return pessoaRepositorio.save(Pessoa);
	}

	public Pessoa atualizarPessoa(Pessoa Pessoa) {
		buscarPessoaPorId(Pessoa.getIdPessoa());
		return pessoaRepositorio.save(Pessoa);
	}

	public void deletarPessoaPorId(Integer id) {
		buscarPessoaPorId(id);
		try {
			pessoaRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

}
