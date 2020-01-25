package com.lughtech.grana.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lughtech.grana.domain.Pessoa;
import com.lughtech.grana.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa buscarPessoa(Integer id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElse(null);
	}

}
