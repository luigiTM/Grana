package com.lughtech.grana.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.domain.Pessoa;
import com.lughtech.grana.repositories.PessoaRepository;
import com.lughtech.grana.services.exceptions.IntegridadeDeDadosException;
import com.lughtech.grana.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository PessoaRepository;

	public Pessoa buscarPessoaPorId(Integer id) {
		Optional<Pessoa> obj = PessoaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Pessoa.class.getSimpleName()));
	}

	public Pessoa salvarPessoa(Pessoa Pessoa) {
		Pessoa.setIdPessoa(null);
		return PessoaRepository.save(Pessoa);
	}

	public Pessoa atualizarPessoa(Pessoa Pessoa) {
		buscarPessoaPorId(Pessoa.getIdPessoa());
		return PessoaRepository.save(Pessoa);
	}

	public void deletarPessoaPorId(Integer id) {
		buscarPessoaPorId(id);
		try {
			PessoaRepository.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

}
