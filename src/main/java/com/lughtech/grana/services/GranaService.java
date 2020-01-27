package com.lughtech.grana.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lughtech.grana.domain.Grana;
import com.lughtech.grana.repositories.GranaRepository;
import com.lughtech.grana.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class GranaService {

	@Autowired
	private GranaRepository granaRepository;

	public Grana buscarGranaPorId(Integer id) {
		Optional<Grana> obj = granaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto do tipo " + Grana.class.getSimpleName() + " não encontrado!"));
	}

}
